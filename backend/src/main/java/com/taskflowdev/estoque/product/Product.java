package com.taskflowdev.estoque.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String sku;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal purchasePrice;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal salePrice;
    @Column(nullable = false)
    private int stockQuantity;
    @Column(nullable = false)
    private int lowStockThreshold;
    protected Product() {}
    public Product(String sku, String name, BigDecimal purchasePrice, BigDecimal salePrice, int stockQuantity, int lowStockThreshold) {
        this.sku = sku; this.name = name; this.purchasePrice = purchasePrice; this.salePrice = salePrice; this.stockQuantity = stockQuantity; this.lowStockThreshold = lowStockThreshold;
    }
    public UUID getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getSalePrice() { return salePrice; }
    public int getStockQuantity() { return stockQuantity; }
    public int getLowStockThreshold() { return lowStockThreshold; }
    public void addStock(int quantity) { stockQuantity += quantity; }
    public void removeStock(int quantity) {
        if (stockQuantity < quantity) throw new IllegalArgumentException("Insufficient stock");
        stockQuantity -= quantity;
    }
}
