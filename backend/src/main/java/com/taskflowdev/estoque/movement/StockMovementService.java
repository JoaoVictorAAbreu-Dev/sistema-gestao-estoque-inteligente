package com.taskflowdev.estoque.movement;

import com.taskflowdev.estoque.product.Product;
import com.taskflowdev.estoque.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class StockMovementService {
    private final StockMovementRepository movementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository movementRepository, ProductRepository productRepository) {
        this.movementRepository = movementRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public StockMovement registerEntry(String sku, int quantity) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.addStock(quantity);
        productRepository.save(product);
        return movementRepository.save(new StockMovement(product, MovementType.ENTRY, quantity, Instant.now()));
    }

    @Transactional
    public StockMovement registerExit(String sku, int quantity) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.removeStock(quantity);
        productRepository.save(product);
        return movementRepository.save(new StockMovement(product, MovementType.EXIT, quantity, Instant.now()));
    }

    @Transactional(readOnly = true)
    public List<StockMovement> listBySku(String sku) {
        return movementRepository.findByProduct_SkuOrderByCreatedAtDesc(sku);
    }
}
