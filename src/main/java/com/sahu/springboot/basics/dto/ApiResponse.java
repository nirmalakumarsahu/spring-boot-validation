package com.sahu.springboot.basics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sahu.springboot.basics.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private Integer code;
    private String status;
    private String message;
    private T result;
    private Object error;
    private String path;

    public static <T> ApiResponse<T> success(HttpStatus code, String message, T result, String path) {
        return new ApiResponse<>(code.value(), AppConstants.STATUS_SUCCESS, message, result, null, path);
    }

    public static <T> ApiResponse<T> failure(HttpStatus code, String message, Object error, String path) {
        return new ApiResponse<>(code.value(), AppConstants.STATUS_FAILURE, message, null, error, path);
    }

    public static <T> ApiResponse<T> error(HttpStatus code, String message, Object error, String path) {
        return new ApiResponse<>(code.value(), AppConstants.STATUS_ERROR, message, null, error, path);
    }
}