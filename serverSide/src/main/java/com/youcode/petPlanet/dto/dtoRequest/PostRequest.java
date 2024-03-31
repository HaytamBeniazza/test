package com.youcode.petPlanet.dto.dtoRequest;


import com.youcode.petPlanet.entity.BlogAuthor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @NotBlank(message = "title must not be empty")
    private String title;
    @NotBlank(message = "image must not be empty")
    private String image;
    @NotBlank(message = "content must not be empty")
    private String content;
    @NotNull
    private Long authorId;
}
