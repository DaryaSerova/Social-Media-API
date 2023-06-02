package ru.effective.mobile.social.user.service;

import ru.effective.mobile.social.user.dto.AuthUserDto;
import ru.effective.mobile.social.user.dto.UserDto;

public interface UserService {

    UserDto getUser(String userId);

    AuthUserDto getAuthUser(String userId);

}
