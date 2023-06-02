package ru.effective.mobile.social.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения поста")
public class UpdatePostDto {

    @NotBlank
    @Length(min = 1, max = 50)
    @Schema(description = "Заголовок")
    private String title;

    @NotBlank
    @Length(max = 3000)
    @Schema(description = "Описание")
    private String description;
}
