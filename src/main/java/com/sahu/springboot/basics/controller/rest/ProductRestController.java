package com.sahu.springboot.basics.controller.rest;

import com.sahu.springboot.basics.dto.ApiResponse;
import com.sahu.springboot.basics.dto.ProductRequest;
import com.sahu.springboot.basics.dto.ProductResponse;
import com.sahu.springboot.basics.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(
            @PathVariable @Min(value = 1, message = "Product ID must be greater than 0") Long id)
    {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Product Found Successfully!",
                productService.findById(id))
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<ProductResponse>> getByName(
            @PathVariable @NotBlank(message = "Name is required") String name)
    {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Product Found Successfully!",
                productService.findByName(name))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> all() {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Products Found",
                productService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> add(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED, "Product Created",
                productService.add(productRequest))
        );
    }

}
