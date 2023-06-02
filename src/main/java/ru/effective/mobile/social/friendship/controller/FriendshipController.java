package ru.effective.mobile.social.friendship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.effective.mobile.social.friendship.dto.FriendshipDto;
import ru.effective.mobile.social.friendship.service.FriendshipService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/friends")
@Tag(name = "Friendship-controller", description = "Управляет взаимодействиями между пользователями")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/{friendId}")
    @Operation(summary = "Добавление друга",
               description = "Позволяет отправить запрос дружбы другому пользователю и " +
                             "автоматически на него подписаться")
    public ResponseEntity<FriendshipDto> addFriend(@RequestParam String friendId) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Запрос на добавление в друзья.");
        return ResponseEntity.status(HttpStatus.CREATED).body(friendshipService.addFriend(userLogin, friendId));

    }

    @DeleteMapping("/{friendId}")
    @Operation(summary = "Удаление друга",
               description = "Позволяет удалить другого пользователя из друзей и отписаться от него")
    public ResponseEntity<?> deleteFriend(@RequestParam String friendId) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Запрос на удаление друга.");
        friendshipService.deleteFriend(userLogin, friendId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Друг успешно удален.");
    }


}
