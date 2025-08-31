package com.sahu.springboot.basics.controller.rest;

import com.sahu.springboot.basics.dto.*;
import com.sahu.springboot.basics.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getByEmail(
            @PathVariable  @NotBlank(message = "Email is required") String email) {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "User Found Successfully!",
                userService.findByEmail(email)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> add(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED, "User Created",
                userService.add(userRequest))
        );
    }


}
