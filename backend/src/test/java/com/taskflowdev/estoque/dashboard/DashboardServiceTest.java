package com.taskflowdev.estoque.dashboard;

import com.taskflowdev.estoque.product.Product;
import com.taskflowdev.estoque.product.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DashboardServiceTest {
    @Test
    void summaryCalculatesInventoryValueAndLowStock() {
        ProductRepository repository = mock(ProductRepository.class);
        when(repository.findAll()).thenReturn(List.of(
                new Product("A", "Item A", BigDecimal.TEN, BigDecimal.valueOf(20), 2, 3),
                new Product("B", "Item B", BigDecimal.valueOf(5), BigDecimal.valueOf(12), 10, 3)
        ));

        DashboardSummary summary = new DashboardService(repository).summary();

        assertEquals(2, summary.productsCount());
        assertEquals(1, summary.lowStockCount());
        assertEquals(0, BigDecimal.valueOf(70).compareTo(summary.inventoryValue()));
    }
}
