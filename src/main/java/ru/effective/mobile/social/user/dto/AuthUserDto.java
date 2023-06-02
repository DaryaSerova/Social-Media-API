package ru.effective.mobile.social.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {

    @Schema(description = "Логин пользователя")
    private String login;

    @Schema(description = "Хэшированный пароль пользователя")
    private String hash;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Электронная почта пользователя")
    private String email;

}
