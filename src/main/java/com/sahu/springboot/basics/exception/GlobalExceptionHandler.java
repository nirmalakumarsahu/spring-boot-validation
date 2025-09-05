package com.sahu.springboot.basics.exception;

import com.sahu.springboot.basics.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleProductNotFoundException(ProductNotFoundException productNotFoundException)
    {
        return buildErrorResponse(HttpStatus.NOT_FOUND, productNotFoundException.getMessage(), null);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleProductAlreadyExistException(ProductAlreadyExistException productAlreadyExistException)
    {
        return buildErrorResponse(HttpStatus.CONFLICT, productAlreadyExistException.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException userNotFoundException)
    {
        return buildErrorResponse(HttpStatus.NOT_FOUND, userNotFoundException.getMessage(), null);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExistException(UserAlreadyExistException userAlreadyExistException)
    {
        return buildErrorResponse(HttpStatus.CONFLICT, userAlreadyExistException.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String, String> fieldErrors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                                .orElse("Invalid Values")
                ));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception exception) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), null);
    }

    private ResponseEntity<ApiResponse<String>> buildErrorResponse(HttpStatus httpStatus, String message, Object error) {
        return  ApiResponse.error(
                httpStatus,
                message,
                error
        );
    }

}
