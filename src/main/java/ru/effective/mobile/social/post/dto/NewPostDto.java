package ru.effective.mobile.social.post.dto;

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
@Schema(description = "Новый пост")
public class NewPostDto {

    @NotBlank
    @Length(min = 1, max = 50)
    @Schema(description = "Заголовок")
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @Length(max = 3000)
    @Schema(description = "Описание")
    private String description;

}
