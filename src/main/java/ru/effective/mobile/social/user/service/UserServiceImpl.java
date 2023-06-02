package ru.effective.mobile.social.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.exception.NotFoundException;
import ru.effective.mobile.social.user.dto.AuthUserDto;
import ru.effective.mobile.social.user.dto.UserDto;
import ru.effective.mobile.social.user.jpa.UserPersistService;
import ru.effective.mobile.social.user.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistService userPersistService;

    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String userId) {

        var user = userPersistService.findUser(userId).orElseThrow(() ->
                new NotFoundException("Требуемый объект не был найден.",
                        String.format("Пользователь = %s не найден.", userId)));

        return userMapper.toUserDto(user);
    }

    @Override
    public AuthUserDto getAuthUser(String userId) {

        var user = userPersistService.findUser(userId).orElseThrow(() ->
                new NotFoundException("Требуемый объект не был найден.",
                        String.format("Пользователь = %s не найден.", userId)));

        return userMapper.toAuthUserDto(user);

    }

}
