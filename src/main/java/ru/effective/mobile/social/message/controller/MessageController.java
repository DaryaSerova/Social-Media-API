package ru.effective.mobile.social.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.effective.mobile.social.message.dto.MessageDto;
import ru.effective.mobile.social.message.dto.NewMessageDto;
import ru.effective.mobile.social.message.model.Message;
import ru.effective.mobile.social.message.service.MessageService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/message")
@Tag(name = "Message-controller", description = "Управляет отправкой сообщений друзьям")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{friendId}/message")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Отправка сообщения",
            description = "Позволяет отправлять сообщения друзьям")
    public ResponseEntity<?> sendMessage(@RequestParam String friendId,
                                         @RequestBody @Valid NewMessageDto newMessageDto) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Запрос на отправку сообщения.");
        messageService.sendMessage(userLogin, friendId, newMessageDto);
        return ResponseEntity.status(HttpStatus.OK).body("Сообщение отправлено успешно");
    }

    @GetMapping("/{friendId}/message")
    @Operation(summary = "Получение переписки",
            description = "Позволяет получить переписку с другом")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam String friendId,
                                                        @RequestParam(required = false,
                                                                      value = "from", defaultValue = "0")
                                                        @PositiveOrZero int from,
                                                        @RequestParam(required = false,
                                                                      value = "size", defaultValue = "10")
                                                        @Positive int size) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Запрос на получение переписки.");
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.getMessages(from, size, userLogin, friendId).getContent());
    }

}
