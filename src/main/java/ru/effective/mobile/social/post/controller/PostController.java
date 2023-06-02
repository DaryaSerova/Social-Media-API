package ru.effective.mobile.social.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.effective.mobile.social.post.dto.NewPostDto;
import ru.effective.mobile.social.post.dto.PostDto;
import ru.effective.mobile.social.post.dto.UpdatePostDto;
import ru.effective.mobile.social.post.service.PostService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/posts")
@Tag(name = "Post-controller", description = "Управляет постами авторизованных пользователей")
public class PostController {

    private final PostService postService;

    @PostMapping()
    @Operation(summary = "Создание поста",
            description = "Позволяет создать пост авторизованного пользователя и сохранить его в БД")
    public ResponseEntity<PostDto> createPost(@RequestBody NewPostDto newPostDto) {
        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Запрос на создание поста.");
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(newPostDto, userLogin));
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Получение поста по его идентификатору",
               description = "Позволяет получить пост из БД по его идентификатору")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Запрос на получение поста с id = " + postId);
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId, userLogin));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "Удаление поста по его идентификатору",
               description = "Позволяет удалить пост из БД по его идентификатору")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Запрос на удаление поста с id = " + postId);
        postService.deletePost(postId, userLogin);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Сообщение успешно удалено");
    }

    @PatchMapping("/{postId}")
    @Operation(summary = "Обновление поста по его идентификатору",
               description = "Позволяет обновить пост по его идентификатору и сохранить в БД")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("postId") Long postId,
                                                  @RequestBody @Valid UpdatePostDto updatePostDto) {
        var userLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Запрос на обновление поста с id = " + postId);
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postId, updatePostDto, userLogin));
    }

}