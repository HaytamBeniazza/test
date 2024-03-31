package com.youcode.petPlanet.dto.dtoRequest;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long OrderId;
    private Long clientId;
    private List<OrderLineRequest> orderLines;
    private String status;
    private double total;
}