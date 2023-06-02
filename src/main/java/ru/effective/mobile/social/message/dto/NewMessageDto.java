package ru.effective.mobile.social.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Новое сообщение")
public class NewMessageDto {

    @NotBlank
    @Length(max = 3000)
    @Schema(description = "Текст сообщения")
    @Size(min = 1, max = 3000)
    private String content;

}
