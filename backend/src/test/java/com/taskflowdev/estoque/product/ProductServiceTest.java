package com.taskflowdev.estoque.product;

import com.taskflowdev.estoque.product.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Test
    void createPersistsProductWhenSkuIsAvailable() {
        ProductRequest request = new ProductRequest("SKU-1", "Notebook", BigDecimal.valueOf(2500), BigDecimal.valueOf(3200), 8, 3);
        when(repository.existsBySku("SKU-1")).thenReturn(false);
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product product = new ProductService(repository).create(request);

        assertEquals("SKU-1", product.getSku());
        assertEquals(8, product.getStockQuantity());
    }

    @Test
    void createRejectsDuplicatedSku() {
        ProductRequest request = new ProductRequest("SKU-1", "Notebook", BigDecimal.valueOf(2500), BigDecimal.valueOf(3200), 8, 3);
        when(repository.existsBySku("SKU-1")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> new ProductService(repository).create(request));
    }
}
