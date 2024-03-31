package com.youcode.petPlanet.auth;

import com.youcode.petPlanet.auth.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
public class RegisterRequest {

  // Admin & BlogAuthor
  public RegisterRequest(String fullName, String email, String password, Role role) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  // Client
  public RegisterRequest(String fullName, String email, String password, Role role, String address, String phone) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.address = address;
    this.phone = phone;
  }

  @NotBlank(message = "Name must not be empty")
  private String fullName;
  @Email(message = "Please enter a correct email form")
  private String email;
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;
  private Role role;
  private String address;
  private String phone;
}
