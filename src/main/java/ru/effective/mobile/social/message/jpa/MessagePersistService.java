package ru.effective.mobile.social.message.jpa;

import org.springframework.data.domain.Page;
import ru.effective.mobile.social.message.dto.MessageDto;
import ru.effective.mobile.social.message.model.Message;

public interface MessagePersistService {

    void sendMessage(Message message);

    Page<Message> getMessages(int from, int size, String userLogin, String friendId);
}
