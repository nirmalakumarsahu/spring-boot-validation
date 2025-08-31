package com.sahu.springboot.basics.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        LocalDate dateOfBirth
) {
}
