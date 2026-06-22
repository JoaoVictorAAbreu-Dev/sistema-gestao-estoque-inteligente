package com.taskflowdev.estoque.report;

import com.taskflowdev.estoque.product.ProductRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ProductRepository repository;
    private final ReportService service;
    public ReportController(ProductRepository repository, ReportService service) { this.repository = repository; this.service = service; }
    @GetMapping(value = "/stock.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> pdf() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-estoque.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(service.createPdf(repository.findAll()));
    }
}
