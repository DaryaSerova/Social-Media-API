package ru.effective.mobile.social.friendship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.effective.mobile.social.friendship.model.Friendship;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(nativeQuery = true,
           value = "FROM friendships f WHERE f.first_user = :firstUser and f.second_user = :secondUser")
    Optional<Friendship> findFriendshipByFirstUserAndSecondUser(@Param("firstUser") String firstUser,
                                                                @Param("secondUser") String secondUser);

    boolean existsByFirstUserAndSecondUser(String firstUser, String secondUser);

}
