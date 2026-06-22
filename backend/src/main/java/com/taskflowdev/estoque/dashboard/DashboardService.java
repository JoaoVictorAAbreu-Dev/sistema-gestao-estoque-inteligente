package com.taskflowdev.estoque.dashboard;

import com.taskflowdev.estoque.product.Product;
import com.taskflowdev.estoque.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {
    private final ProductRepository repository;

    public DashboardService(ProductRepository repository) {
        this.repository = repository;
    }

    public DashboardSummary summary() {
        List<Product> products = repository.findAll();
        long lowStock = products.stream().filter(p -> p.getStockQuantity() <= p.getLowStockThreshold()).count();
        BigDecimal inventoryValue = products.stream()
                .map(p -> p.getPurchasePrice().multiply(BigDecimal.valueOf(p.getStockQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new DashboardSummary(products.size(), lowStock, inventoryValue);
    }
}
