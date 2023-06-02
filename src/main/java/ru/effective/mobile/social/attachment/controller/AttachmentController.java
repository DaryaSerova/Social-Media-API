package ru.effective.mobile.social.attachment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.effective.mobile.social.attachment.message.ResponseAttach;
import ru.effective.mobile.social.attachment.message.ResponseMessage;
import ru.effective.mobile.social.attachment.model.Attachment;
import ru.effective.mobile.social.attachment.service.AttachmentService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/v1/attachment")
@Tag(name = "Attachment-controller", description = "Управляет вложениями к посту авторизованных пользователей")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(path = "/{postId}", headers = "Content-Type= multipart/form-data")
    @Operation(summary = "Загружает вложение",
               description = "Позволяет загрузить изображение к определенному посту и сохранить его в БД")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("attachment") MultipartFile file,
                                                  @PathVariable("postId") Long postId) {

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String message = "";

        try {
            Attachment attachment = attachmentService.store(file, postId);
            message = "Файл успешно загружен: " + file.getOriginalFilename() + " " + attachment.getId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "Не удалось загрузить файл: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Получает вложения",
              description = "Позволяет получить изображения к определенному посту")
    public ResponseEntity<List<ResponseAttach>> getAttachments(@PathVariable Long postId) {

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(attachmentService.getAllAttachments(postId));
    }

}
