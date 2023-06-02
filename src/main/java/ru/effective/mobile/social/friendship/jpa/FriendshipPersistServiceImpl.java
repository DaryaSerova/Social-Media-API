package ru.effective.mobile.social.friendship.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.friendship.model.Friendship;
import ru.effective.mobile.social.friendship.repository.FriendshipRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipPersistServiceImpl implements FriendshipPersistService {

    private final FriendshipRepository friendshipRepository;

    @Override
    @Transactional
    public Friendship addFriend(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public Optional<Friendship> findFriendship(String firstUser, String secondUser) {
        return friendshipRepository.findFriendshipByFirstUserAndSecondUser(firstUser, secondUser);
    }

    @Override
    public boolean existsByFirstUserAndSecondUser(String firstUser, String secondUser) {
        return friendshipRepository.existsByFirstUserAndSecondUser(firstUser, secondUser);
    }

    @Override
    @Transactional
    public void deleteFriend(Friendship friendship) {
        friendshipRepository.delete(friendship);
    }

}
