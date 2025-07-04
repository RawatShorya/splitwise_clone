package com.splitwise_clone.services;

import com.splitwise_clone.Constants.ENUMS.Role;
import com.splitwise_clone.Dtos.AuthResponse;
import com.splitwise_clone.Dtos.LoginRequest;
import com.splitwise_clone.Dtos.RegisterRequest;
import com.splitwise_clone.Entities.User;
import com.splitwise_clone.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authManager;

  public AuthResponse register(RegisterRequest request) {
    var user =
        User.builder()
            .role(Role.USER)
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

    userRepository.save(user);
    String jwt = jwtService.generateToken(user);
    return new AuthResponse(jwt);
  }

  public AuthResponse login(LoginRequest request) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    String jwt = jwtService.generateToken(user);
    return new AuthResponse(jwt);
  }
}
