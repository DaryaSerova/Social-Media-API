package ru.effective.mobile.social.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective.mobile.social.auth.model.JwtRequest;
import ru.effective.mobile.social.auth.model.JwtResponse;
import ru.effective.mobile.social.auth.model.RefreshJwtRequest;
import ru.effective.mobile.social.auth.service.AuthService;
import ru.effective.mobile.social.user.dto.NewUserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth-controller", description = "Управляет регистраций и авторизацией пользователей")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя",
               description = "Позволяет зарегистрировать пользователя, сохранить в БД")
    public ResponseEntity<String> register(@RequestBody @Valid NewUserDto newUserDto) {

        authService.register(newUserDto);
        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя",
              description = "Позволяет авторизовать пользователю")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid JwtRequest authRequest) {

        JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    @Operation(summary = "Обновление access токена",
               description = "Позволяет получить новый access токен")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {

        JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    @Operation(summary = "Обновление access и refresh токенов",
               description = "Позволяет получить новые access и refresh токены")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {

        JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
