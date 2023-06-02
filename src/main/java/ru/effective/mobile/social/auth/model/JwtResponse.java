package ru.effective.mobile.social.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@Schema(description = "Класс, который содержит access и refresh токены в ответ на отправку JwtRequest")
public class JwtResponse {

    private final String type = "Bearer";

    @Schema(description = "Токен авторизации")
    private String accessToken;

    @Schema(description = "Токен для обновления токена авторизации")
    private String refreshToken;

}
