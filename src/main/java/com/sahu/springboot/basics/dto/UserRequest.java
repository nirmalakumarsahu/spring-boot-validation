package com.sahu.springboot.basics.dto;

import com.sahu.springboot.basics.validation.StrongPassword;
import com.sahu.springboot.basics.validation.group.CreateGroup;
import com.sahu.springboot.basics.validation.group.UpdateGroup;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "Name cannot be blank", groups = {CreateGroup.class, UpdateGroup.class})
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters", groups = {CreateGroup.class, UpdateGroup.class})
        String name,

        @NotBlank(message = "Email cannot be blank", groups = {CreateGroup.class, UpdateGroup.class})
        @Email(message = "Invalid email format", groups = {CreateGroup.class, UpdateGroup.class})
        String email,

        @NotNull(message = "Date of birth cannot be null", groups = {CreateGroup.class, UpdateGroup.class})
        @Past(message = "Date of birth must be in the past", groups = {CreateGroup.class, UpdateGroup.class})
        LocalDate dateOfBirth,

        @NotBlank(message = "Password cannot be blank", groups = CreateGroup.class)
        @StrongPassword(groups = CreateGroup.class)
        String password,

        @NotNull(message = "Terms and conditions must be accepted", groups = CreateGroup.class)
        @AssertTrue(message = "You must accept the terms and conditions", groups = CreateGroup.class)
        Boolean teamAndCondition
)
{
}
