package com.youcode.petPlanet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "petProduct_id")
    private PetProduct petProduct;
}

