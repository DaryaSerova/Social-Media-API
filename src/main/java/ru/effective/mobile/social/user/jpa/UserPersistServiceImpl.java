package ru.effective.mobile.social.user.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.user.model.User;
import ru.effective.mobile.social.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPersistServiceImpl implements UserPersistService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String user) {
        return userRepository.findById(user);
    }
}
