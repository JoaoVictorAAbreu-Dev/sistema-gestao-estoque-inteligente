package com.taskflowdev.estoque.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String sku,
        @NotBlank String name,
        @DecimalMin("0.01") BigDecimal purchasePrice,
        @DecimalMin("0.01") BigDecimal salePrice,
        @Min(0) int stockQuantity,
        @Min(1) int lowStockThreshold
) {}
