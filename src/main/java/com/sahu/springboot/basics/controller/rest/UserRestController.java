package com.sahu.springboot.basics.controller.rest;

import com.sahu.springboot.basics.dto.ApiResponse;
import com.sahu.springboot.basics.dto.ProductResponse;
import com.sahu.springboot.basics.dto.UserResponse;
import com.sahu.springboot.basics.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
