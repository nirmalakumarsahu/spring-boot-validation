package com.sahu.springboot.basics.controller.rest;

import com.sahu.springboot.basics.dto.*;
import com.sahu.springboot.basics.service.UserService;
import com.sahu.springboot.basics.validation.group.CreateGroup;
import com.sahu.springboot.basics.validation.group.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getByEmail(
            @PathVariable @NotBlank(message = "Email is required") String email)
    {
        return ApiResponse.success(HttpStatus.OK, "User Found Successfully!",
                userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> add(@Validated(CreateGroup.class) @RequestBody UserRequest userRequest) {
        return ApiResponse.success(HttpStatus.CREATED, "User Created",
                userService.add(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable @NotNull(message = "User id is required") Long id,
                                                            @Validated(UpdateGroup.class) @RequestBody UserRequest userRequest)
    {
        return ApiResponse.success(HttpStatus.OK, "User Updated",
                userService.update(id, userRequest));
    }

}
