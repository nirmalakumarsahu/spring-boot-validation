package com.sahu.springboot.basics.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @Size(max = 255, message = "Description must be less than 255 characters")
        String description,
        @DecimalMin(value = "1.0", inclusive = true, message = "Price must be greater than or equal to 1")
        @DecimalMax(value = "200000.0", inclusive = true, message = "Price must be less than or equal to 200000")
        BigDecimal price,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be greater than 0")
        Integer quantity
)
{
}
