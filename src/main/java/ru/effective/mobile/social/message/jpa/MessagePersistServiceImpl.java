package ru.effective.mobile.social.message.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.message.model.Message;
import ru.effective.mobile.social.message.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessagePersistServiceImpl implements MessagePersistService {

    private final MessageRepository messageRepository;

    @Override
    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Page<Message> getMessages(int from, int size, String userLogin, String friendId) {
        return messageRepository.findMessageBySenderIdAndRecipientId(
                                 userLogin, friendId, PageRequest.of(from, size, Sort.by("date", "DESC")));
    }
}
