package ru.effective.mobile.social.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Refresh токен")
public class RefreshJwtRequest {

    @Schema(description = "Токен для обновления токена авторизации")
    public String refreshToken;

}
