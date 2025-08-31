package com.sahu.springboot.basics.service;

import com.sahu.springboot.basics.dto.UserRequest;
import com.sahu.springboot.basics.dto.UserResponse;

public interface UserService {
    UserResponse findByEmail(String email);

    UserResponse add(UserRequest userRequest);

    UserResponse update(UserRequest userRequest);
}
