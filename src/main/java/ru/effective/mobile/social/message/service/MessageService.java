package ru.effective.mobile.social.message.service;

import org.springframework.data.domain.Page;
import ru.effective.mobile.social.message.dto.MessageDto;
import ru.effective.mobile.social.message.dto.NewMessageDto;

import java.util.List;

public interface MessageService {

    void sendMessage(String firstUser, String secondUser, NewMessageDto newMessageDto);

    Page<MessageDto> getMessages(int from, int size, String userLogin, String friendId);
}
