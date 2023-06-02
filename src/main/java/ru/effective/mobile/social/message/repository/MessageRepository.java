package ru.effective.mobile.social.message.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective.mobile.social.message.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findMessageBySenderIdAndRecipientId(String userLogin, String friendId, PageRequest of);
}
