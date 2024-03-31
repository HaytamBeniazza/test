package com.youcode.petPlanet.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.petPlanet.auth.user.Role;
import com.youcode.petPlanet.auth.user.UserRepository;
import com.youcode.petPlanet.config.JwtService;
import com.youcode.petPlanet.auth.token.Token;
import com.youcode.petPlanet.auth.token.TokenRepository;
import com.youcode.petPlanet.auth.token.TokenType;
import com.youcode.petPlanet.auth.user.User;
import com.youcode.petPlanet.dto.dtoResponse.AdminResponse;
import com.youcode.petPlanet.dto.dtoResponse.ClientResponse;
import com.youcode.petPlanet.entity.Admin;
import com.youcode.petPlanet.entity.BlogAuthor;
import com.youcode.petPlanet.entity.Client;
import com.youcode.petPlanet.exception.RegisterUserException;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.AdminRepository;
import com.youcode.petPlanet.repository.BlogAuthorRepository;
import com.youcode.petPlanet.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final AdminRepository adminRepository;
  private final BlogAuthorRepository blogAuthorRepository;
  private final ClientRepository clientRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ModelMapper modelMapper;


  public AuthenticationResponse register(RegisterRequest request) {
    System.out.println(request);
    Optional<User> checkedMail = userRepository.findFirstByEmail(request.getEmail());

    if(checkedMail.isEmpty()) {
      var user = User.builder()
              .fullName(request.getFullName())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(request.getRole())
              .build();
      var savedUser = userRepository.save(user);
      var jwtToken = jwtService.generateToken(savedUser,savedUser);
      var refreshToken = jwtService.generateRefreshToken(savedUser);
      saveUserToken(savedUser, jwtToken);
      if(request.getRole() == null){
        Client client = new Client(0L, request.getFullName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getPhone(), request.getAddress());
        client.setRole(Role.CLIENT);
        clientRepository.save(client);
      }else{
        switch (request.getRole()) {
          case ADMIN:
            Admin admin = new Admin(0L, request.getFullName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
            break;
          case CLIENT:
            Client client = new Client(0L, request.getFullName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getPhone(), request.getAddress());
            client.setRole(Role.CLIENT);
            clientRepository.save(client);
            break;
          case BLOGAUTHOR:
            BlogAuthor blogAuthor = new BlogAuthor(0L, request.getFullName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            blogAuthor.setRole(Role.BLOGAUTHOR);
            blogAuthorRepository.save(blogAuthor);
            break;
        }
      }

      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .build();
    }else {
      throw new RegisterUserException("User is already registered");
    }
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = userRepository.findFirstByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user,user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.userRepository.findFirstByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user,user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
