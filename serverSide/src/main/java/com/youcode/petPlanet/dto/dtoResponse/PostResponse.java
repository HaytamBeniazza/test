package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.entity.BlogAuthor;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String image;
    private String content;
    private BlogAuthor author;
}
