package ru.effective.mobile.social.friendship.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.effective.mobile.social.friendship.dto.FriendshipDto;
import ru.effective.mobile.social.friendship.model.Friendship;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FriendshipMapper {


    @Mapping(target = "firstUser", source = "userLogin")
    @Mapping(target = "secondUser", source = "friendId")
    Friendship toFriendship(String userLogin, String friendId);

    @Mapping(target = "id", source = "friendship.id")
    @Mapping(target = "user", source = "friendship.firstUser")
    @Mapping(target = "friend", source = "friendship.secondUser")
    FriendshipDto toFriendshipDto(Friendship friendship);
}
