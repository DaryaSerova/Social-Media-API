package ru.effective.mobile.social.friendship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.exception.BadRequestException;
import ru.effective.mobile.social.friendship.dto.FriendshipDto;
import ru.effective.mobile.social.friendship.jpa.FriendshipPersistService;
import ru.effective.mobile.social.friendship.mapper.FriendshipMapper;
import ru.effective.mobile.social.message.jpa.MessagePersistService;
import ru.effective.mobile.social.message.mapper.MessageMapper;
import ru.effective.mobile.social.user.service.UserService;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

    private final UserService userService;

    private final FriendshipMapper friendshipMapper;

    private final FriendshipPersistService friendshipPersistService;

    private final MessageMapper messageMapper;

    private final MessagePersistService messagePersistService;


    @Override
    public FriendshipDto addFriend(String firstUser, String secondUser) {

        userService.getUser(secondUser);

        if (isFollowing(firstUser, secondUser)) {
            throw new BadRequestException("Неверный запрос", "Заявка уже была отправлена.");
        }

        var friendship =
                friendshipPersistService.addFriend(friendshipMapper.toFriendship(firstUser, secondUser));

        return friendshipMapper.toFriendshipDto(friendship);
    }

    @Override
    public void deleteFriend(String firstUser, String secondUser) {

        userService.getUser(secondUser);

        if (isFriends(firstUser, secondUser)) {
            throw new BadRequestException("Неверный запрос", "Пользователь уже удален из друзей.");
        }

        friendshipPersistService.deleteFriend(friendshipMapper.toFriendship(firstUser, secondUser));
    }


    @Override
    public boolean isFriends(String firstUser, String secondUser) {

        var following = friendshipPersistService.existsByFirstUserAndSecondUser(firstUser, secondUser);
        var follower = friendshipPersistService.existsByFirstUserAndSecondUser(secondUser, firstUser);

        return following && follower;
    }

    @Override
    public boolean isFollowing(String firstUser, String secondUser) {

        return friendshipPersistService.existsByFirstUserAndSecondUser(firstUser, secondUser);
    }
}
