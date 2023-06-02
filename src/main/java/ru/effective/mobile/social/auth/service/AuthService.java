package ru.effective.mobile.social.auth.service;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.auth.model.JwtRequest;
import ru.effective.mobile.social.auth.model.JwtResponse;
import ru.effective.mobile.social.exception.AuthException;
import ru.effective.mobile.social.exception.RegistrationException;
import ru.effective.mobile.social.user.dto.AuthUserDto;
import ru.effective.mobile.social.user.dto.NewUserDto;
import ru.effective.mobile.social.user.jpa.UserPersistService;
import ru.effective.mobile.social.user.model.User;
import ru.effective.mobile.social.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final UserPersistService userPersistService;

    private final JwtProvider jwtProvider;

    private final Map<String, String> refreshStorage = new HashMap<>();

    public void register(@NonNull NewUserDto newUserDto) {

        AuthUserDto user = userService.getAuthUser(newUserDto.getLogin());

        if (user != null && user.getLogin().equals(newUserDto.getLogin())) {
            throw new RegistrationException("Пользователь с таким логином уже существует.");
        }
        if (user != null && user.getEmail().equals(newUserDto.getEmail())) {
            throw new RegistrationException("Пользователь с таким email уже существует.");
        }

        String hash = BCrypt.hashpw(newUserDto.getPassword(), BCrypt.gensalt());

        userPersistService.register(
                new User(newUserDto.getLogin(), hash, newUserDto.getUsername(), newUserDto.getEmail()));

    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {

        AuthUserDto user = userService.getAuthUser(authRequest.getLogin());

        String hash = BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt());

        if (user.getHash().equals(hash)) {

            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);

        } else {

            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AuthUserDto user = userService.getAuthUser(login);
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AuthUserDto user = userService.getAuthUser(login);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
