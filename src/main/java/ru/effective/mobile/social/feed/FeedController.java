package ru.effective.mobile.social.feed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.effective.mobile.social.post.dto.PostDto;
import ru.effective.mobile.social.post.service.PostService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/feed")
@Tag(name = "Feed-controller", description = "Управляет лентой подписок")
public class FeedController {

    private final PostService postService;

    @GetMapping()
    @Operation(summary = "Лента активности пользователя",
               description = "Отображает последние посты от пользователей, на которых подписан текущий пользователь")
    ResponseEntity<List<PostDto>> getPostsByFriend(@RequestParam("friendId") String friendId,
                                                   @RequestParam(required = false, value = "from", defaultValue = "0")
                                                   @PositiveOrZero int from,
                                                   @RequestParam(required = false, value = "size", defaultValue = "10")
                                                   @Positive int size) {

        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info(String.format("Запрос на получение сообщений friendId = %s.", friendId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPostsByFriend(from, size, userLogin, friendId).getContent());
    }
}