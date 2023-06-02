package ru.effective.mobile.social.attachment.message;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность ответа с результатом выполнения")
public class ResponseMessage {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
