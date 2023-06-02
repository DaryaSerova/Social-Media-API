package ru.effective.mobile.social.friendship.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewFriendshipDto {

    @NotBlank
    @Schema(description = "Имя текущего пользователя")
    private String user;

    @NotBlank
    @Schema(description = "Имя друга")
    private String friend;
}
