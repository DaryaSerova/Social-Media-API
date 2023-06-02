package ru.effective.mobile.social.message.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.exception.BadRequestException;
import ru.effective.mobile.social.exception.NotFoundException;
import ru.effective.mobile.social.friendship.service.FriendshipService;
import ru.effective.mobile.social.message.dto.MessageDto;
import ru.effective.mobile.social.message.dto.NewMessageDto;
import ru.effective.mobile.social.message.jpa.MessagePersistService;
import ru.effective.mobile.social.message.mapper.MessageMapper;
import ru.effective.mobile.social.message.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final FriendshipService friendshipService;

    private final MessageMapper messageMapper;

    private final MessagePersistService messagePersistService;

    @Override
    public void sendMessage(String firstUser, String secondUser, NewMessageDto newMessageDto) {

        if (!friendshipService.isFriends(firstUser, secondUser)) {
            throw new BadRequestException("Плохой запрос",
                    "Пользователь не является другом, вы не можете отправить ему сообщение.");
        }

        Message message = messageMapper.toMessage(firstUser, secondUser, newMessageDto);

        messagePersistService.sendMessage(message);

    }

    @Override
    public Page<MessageDto> getMessages(int from, int size, String userLogin, String friendId) {

        var messages = messagePersistService.getMessages(from, size, userLogin, friendId);

        if (messages.isEmpty()) {
            throw new NotFoundException("Требуемый объект не был найден.",
                    String.format("У вас нет переписки с пользователем = %s.", friendId));
        }

        return messages.map(message -> messageMapper.toMessageDto(message));
    }
}
