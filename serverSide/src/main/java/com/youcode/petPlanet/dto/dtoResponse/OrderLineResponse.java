package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.entity.Order;
import com.youcode.petPlanet.entity.PetProduct;
import com.youcode.petPlanet.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineResponse {

    private Long id;
    private int quantity;
    private Order order;
    private PetProduct petProduct;
    private LocalDateTime dateTime;
}
