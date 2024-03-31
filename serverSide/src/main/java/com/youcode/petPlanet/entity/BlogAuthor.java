package com.youcode.petPlanet.entity;

import com.youcode.petPlanet.auth.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class BlogAuthor extends User {

    @OneToMany
    private List<Post> posts;
    public BlogAuthor(long id, String fullName, String email, String password) {
        super(id,fullName,email,password);
    }

    public BlogAuthor() {
    }
}
