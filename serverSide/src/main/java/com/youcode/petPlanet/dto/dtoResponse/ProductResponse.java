package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.entity.Category;
import com.youcode.petPlanet.entity.Files;
import com.youcode.petPlanet.entity.OrderLine;
import com.youcode.petPlanet.entity.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String quantity;
    private Files image;
    private String price;
}
