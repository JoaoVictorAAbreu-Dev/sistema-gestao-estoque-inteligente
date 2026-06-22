package com.taskflowdev.estoque.product;

import com.taskflowdev.estoque.product.dto.ProductResponse;

public final class ProductMapper {
    private ProductMapper() {}
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getStockQuantity(),
                product.getLowStockThreshold(),
                product.getStockQuantity() <= product.getLowStockThreshold()
        );
    }
}
