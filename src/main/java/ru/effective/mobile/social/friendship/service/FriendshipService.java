package ru.effective.mobile.social.friendship.service;

import ru.effective.mobile.social.friendship.dto.FriendshipDto;

public interface FriendshipService {

    FriendshipDto addFriend(String firstUser, String secondUser);

    void deleteFriend(String userLogin, String friendId);

    boolean isFriends(String firstUser, String secondUser);

    boolean isFollowing(String firstUser, String secondUser);
}
