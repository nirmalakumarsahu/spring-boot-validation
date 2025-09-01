package com.sahu.springboot.basics.service.impl;

import com.sahu.springboot.basics.dto.UserRequest;
import com.sahu.springboot.basics.dto.UserResponse;
import com.sahu.springboot.basics.exception.UserAlreadyExistException;
import com.sahu.springboot.basics.exception.UserNotFoundException;
import com.sahu.springboot.basics.model.User;
import com.sahu.springboot.basics.repository.UserRepository;
import com.sahu.springboot.basics.service.UserService;
import com.sahu.springboot.basics.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserUtil::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserResponse add(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new UserAlreadyExistException("User is already exist with email: " + userRequest.email());
        }

        User user = userRepository.save(UserUtil.toUser(userRequest));
        return UserUtil.toUserResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return userRepository.findById(id).map(user -> {
                    user.setName(userRequest.name());
                    user.setEmail(userRequest.email());
                    user.setDateOfBirth(userRequest.dateOfBirth());
                    return UserUtil.toUserResponse(userRepository.save(user));
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

}
