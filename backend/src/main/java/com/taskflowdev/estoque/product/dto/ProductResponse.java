package com.taskflowdev.estoque.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String sku,
        String name,
        BigDecimal purchasePrice,
        BigDecimal salePrice,
        int stockQuantity,
        int lowStockThreshold,
        boolean lowStock
) {}
