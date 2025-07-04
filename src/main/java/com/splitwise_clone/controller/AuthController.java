package com.splitwise_clone.controller;

import com.splitwise_clone.Dtos.AuthResponse;
import com.splitwise_clone.Dtos.LoginRequest;
import com.splitwise_clone.Dtos.RegisterRequest;
import com.splitwise_clone.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @GetMapping("/api/hello")
  public String hello() {
    return "Hello, authenticated user!";
  }
}
