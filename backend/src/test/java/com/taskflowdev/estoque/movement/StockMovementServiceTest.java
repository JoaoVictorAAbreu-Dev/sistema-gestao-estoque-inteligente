package com.taskflowdev.estoque.movement;

import com.taskflowdev.estoque.product.Product;
import com.taskflowdev.estoque.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {
    @Mock
    private StockMovementRepository movementRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    void entryIncreasesStock() {
        Product product = product();
        when(productRepository.findBySku("SKU-1")).thenReturn(Optional.of(product));
        when(movementRepository.save(any(StockMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StockMovement movement = new StockMovementService(movementRepository, productRepository).registerEntry("SKU-1", 5);

        assertEquals(MovementType.ENTRY, movement.getType());
        assertEquals(15, product.getStockQuantity());
    }

    @Test
    void exitDecreasesStock() {
        Product product = product();
        when(productRepository.findBySku("SKU-1")).thenReturn(Optional.of(product));
        when(movementRepository.save(any(StockMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StockMovement movement = new StockMovementService(movementRepository, productRepository).registerExit("SKU-1", 4);

        assertEquals(MovementType.EXIT, movement.getType());
        assertEquals(6, product.getStockQuantity());
    }

    @Test
    void exitRejectsInsufficientStock() {
        Product product = product();
        when(productRepository.findBySku("SKU-1")).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () -> new StockMovementService(movementRepository, productRepository).registerExit("SKU-1", 99));
    }

    private Product product() {
        return new Product("SKU-1", "Notebook", BigDecimal.valueOf(2500), BigDecimal.valueOf(3200), 10, 3);
    }
}
