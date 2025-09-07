package com.sahu.springboot.basics.service;

import com.sahu.springboot.basics.dto.UserRequest;
import com.sahu.springboot.basics.dto.UserResponse;

public interface UserService {
    UserResponse getByEmail(String email);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);
}
