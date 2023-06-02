package ru.effective.mobile.social.friendship.jpa;

import ru.effective.mobile.social.friendship.model.Friendship;

import java.util.Optional;

public interface FriendshipPersistService {

    Friendship addFriend(Friendship friendship);

    Optional<Friendship> findFriendship(String firstUser, String secondUser);

    boolean existsByFirstUserAndSecondUser(String firstUser, String secondUser);

    void deleteFriend(Friendship friendship);

}
