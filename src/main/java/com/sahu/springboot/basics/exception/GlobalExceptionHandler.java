package com.sahu.springboot.basics.exception;

import com.sahu.springboot.basics.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ApiResponse<String>> handleProductNotFoundException(ProductNotFoundException productNotFoundException,
                                                                              HttpServletRequest httpServletRequest)
    {
        return buildErrorResponse(HttpStatus.NOT_FOUND, productNotFoundException.getMessage(), null, httpServletRequest);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleProductAlreadyExistException(ProductAlreadyExistException productAlreadyExistException,
                                                                                  HttpServletRequest httpServletRequest)
    {
        return buildErrorResponse(HttpStatus.CONFLICT, productAlreadyExistException.getMessage(), null, httpServletRequest);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                          HttpServletRequest httpServletRequest)
    {
        Map<String, String> fieldErrors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                                .orElse("Invalid Values")
                ));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors, httpServletRequest);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception exception, HttpServletRequest httpServletRequest) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), null, httpServletRequest);
    }

    private ResponseEntity<ApiResponse<String>> buildErrorResponse(HttpStatus httpStatus, String message, Object error, HttpServletRequest httpServletRequest) {
        ApiResponse<String> response = ApiResponse.error(
                httpStatus,
                message,
                error,
                httpServletRequest.getRequestURI()
        );

        return ResponseEntity.status(httpStatus).body(response);
    }

}
