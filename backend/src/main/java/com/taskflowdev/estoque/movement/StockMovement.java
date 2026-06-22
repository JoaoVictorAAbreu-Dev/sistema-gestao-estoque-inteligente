package com.taskflowdev.estoque.movement;

import com.taskflowdev.estoque.product.Product;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "stock_movements")
public class StockMovement {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Instant createdAt;
    protected StockMovement() {}
    public StockMovement(Product product, MovementType type, int quantity, Instant createdAt) {
        this.product = product; this.type = type; this.quantity = quantity; this.createdAt = createdAt;
    }
    public UUID getId() { return id; }
    public Product getProduct() { return product; }
    public MovementType getType() { return type; }
    public int getQuantity() { return quantity; }
    public Instant getCreatedAt() { return createdAt; }
}
