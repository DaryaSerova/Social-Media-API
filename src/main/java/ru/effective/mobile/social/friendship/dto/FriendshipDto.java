package ru.effective.mobile.social.friendship.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Взаимоотношение между пользователя")
public class FriendshipDto {

    @Schema(description = "Идентификатор текущего пользователя")
    private Long id;

    @Schema(description = "Имя пользователя")
    private String user;

    @Schema(description = "Имя друга")
    private String friend;

}
