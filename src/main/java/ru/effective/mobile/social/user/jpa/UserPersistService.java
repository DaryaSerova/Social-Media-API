package ru.effective.mobile.social.user.jpa;

import ru.effective.mobile.social.user.model.User;

import java.util.Optional;

public interface UserPersistService {

    void register(User user);

    Optional<User> findUser(String user);
}
