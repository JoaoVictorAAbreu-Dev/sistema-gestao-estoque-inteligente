package com.taskflowdev.estoque.movement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record StockMovementRequest(
        @NotBlank String sku,
        @Min(1) int quantity
) {}
