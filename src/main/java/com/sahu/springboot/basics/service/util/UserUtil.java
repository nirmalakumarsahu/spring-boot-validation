package com.sahu.springboot.basics.service.util;

import com.sahu.springboot.basics.dto.UserRequest;
import com.sahu.springboot.basics.dto.UserResponse;
import com.sahu.springboot.basics.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public User toUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .dateOfBirth(userRequest.dateOfBirth())
                .teamAndCondition(userRequest.teamAndCondition())
                .build();
    }

}
