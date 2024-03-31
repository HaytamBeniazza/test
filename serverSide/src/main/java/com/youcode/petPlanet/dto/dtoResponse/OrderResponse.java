package com.youcode.petPlanet.dto.dtoResponse;

import com.youcode.petPlanet.entity.Client;
import com.youcode.petPlanet.entity.Order;
import com.youcode.petPlanet.entity.OrderLine;
import com.youcode.petPlanet.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private double total;
    private String status;
    private Client client;
    private List<OrderLine> orderLines;
}