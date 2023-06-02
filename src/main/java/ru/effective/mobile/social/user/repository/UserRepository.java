package ru.effective.mobile.social.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective.mobile.social.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
