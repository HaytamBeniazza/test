package com.youcode.petPlanet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PetProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "petProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines;

    @OneToMany(mappedBy = "petProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}

