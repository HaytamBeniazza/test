package com.youcode.petPlanet.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;
    private String comment;

    @ManyToOne
    private Client client;

    @ManyToOne
    private PetProduct petProduct;
}
