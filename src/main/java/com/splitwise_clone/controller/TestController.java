package com.splitwise_clone.controller;

import com.splitwise_clone.Dtos.UserDTO;
import com.splitwise_clone.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello, authenticated user!";
  }

  @GetMapping("/users/me")
  public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    return ResponseEntity.ok(dto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/api/admin/stats")
  public String adminStats() {
    return "Only admins can see this!";
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/api/user/stats")
  public String userStats() {
    return "Only user can see this!";
  }
}
