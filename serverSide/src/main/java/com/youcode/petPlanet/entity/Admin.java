package com.youcode.petPlanet.entity;

import com.youcode.petPlanet.auth.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Admin extends User {
    public Admin(long id, String fullName, String email, String password) {
        super(id, fullName, email, password);
    }

    public Admin() {

    }
}
