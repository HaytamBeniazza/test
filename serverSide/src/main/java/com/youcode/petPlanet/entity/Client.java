package com.youcode.petPlanet.entity;

import com.youcode.petPlanet.auth.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "userId")
public class Client extends User {
    private String phone;
    private String address;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Review> reviews;


    public Client(long id, String fullName, String email, String password, String phone, String address) {
        super(id, fullName, email, password);
        this.phone = phone;
        this.address = address;
    }
}
