package ru.effective.mobile.social.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.effective.mobile.social.message.dto.MessageDto;
import ru.effective.mobile.social.message.dto.NewMessageDto;
import ru.effective.mobile.social.message.model.Message;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper {

    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "recipientId", source = "recipientId")
    @Mapping(target = "content", source = "newMessageDto.content")
    Message toMessage(String senderId, String recipientId, NewMessageDto newMessageDto);

    @Mapping(target = "senderId", source = "message.senderId")
    @Mapping(target = "recipientId", source = "message.recipientId")
    @Mapping(target = "content", source = "message.content")
    @Mapping(target = "date", source = "message.date")
    MessageDto toMessageDto(Message message);

}
