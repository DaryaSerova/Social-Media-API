package ru.effective.mobile.social.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.effective.mobile.social.user.dto.AuthUserDto;
import ru.effective.mobile.social.user.dto.UserDto;
import ru.effective.mobile.social.user.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "username", source = "user.username")
    UserDto toUserDto(User user);

    @Mapping(target = "username", source = "userDto.username")
    User toUser(UserDto userDto);

    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "hash", source = "user.hash")
    AuthUserDto toAuthUserDto(User user);
}
