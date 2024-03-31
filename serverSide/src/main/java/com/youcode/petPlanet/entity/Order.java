package com.youcode.petPlanet.entity;

import com.youcode.petPlanet.auth.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    public Order(String status, User client, List<OrderLine> orderLines) {
        this.status = status;
        this.client = client;
        this.orderLines = orderLines;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;
    private String status;

    @ManyToOne
    private User client;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

}
