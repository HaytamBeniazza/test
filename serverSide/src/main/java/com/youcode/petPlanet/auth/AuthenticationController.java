package com.youcode.petPlanet.auth;

import com.youcode.petPlanet.auth.user.User;
import com.youcode.petPlanet.dto.dtoResponse.AdminResponse;
import com.youcode.petPlanet.dto.dtoResponse.ClientResponse;
import com.youcode.petPlanet.entity.Admin;
import com.youcode.petPlanet.entity.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping( "/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody @Valid RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

}
