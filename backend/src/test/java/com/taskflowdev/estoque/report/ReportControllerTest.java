package com.taskflowdev.estoque.report;

import com.taskflowdev.estoque.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportControllerTest {
    @Test
    void pdfReturnsExpectedHeadersAndBytes() {
        ProductRepository repository = mock(ProductRepository.class);
        when(repository.findAll()).thenReturn(List.of());

        var response = new ReportController(repository, new ReportService()).pdf();

        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
        assertEquals("attachment; filename=relatorio-estoque.pdf", response.getHeaders().getFirst("Content-Disposition"));
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }
}
