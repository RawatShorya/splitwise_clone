package com.splitwise_clone.Dtos;

import com.splitwise_clone.Constants.ENUMS.Role;

public record UserDTO(Long id, String name, String email, Role role) {}
