package com.youcode.petPlanet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Image_id", referencedColumnName = "id")
    private Files image;

    private BigDecimal price;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<PetProduct> petsProducts;

}
