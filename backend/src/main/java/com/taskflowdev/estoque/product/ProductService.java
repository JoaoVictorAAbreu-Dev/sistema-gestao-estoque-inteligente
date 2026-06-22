package com.taskflowdev.estoque.product;

import com.taskflowdev.estoque.product.dto.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Product create(ProductRequest request) {
        if (repository.existsBySku(request.sku())) {
            throw new IllegalArgumentException("SKU already exists");
        }
        return repository.save(new Product(
                request.sku(),
                request.name(),
                request.purchasePrice(),
                request.salePrice(),
                request.stockQuantity(),
                request.lowStockThreshold()
        ));
    }

    @Transactional(readOnly = true)
    public List<Product> list() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findBySku(String sku) {
        return repository.findBySku(sku).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
