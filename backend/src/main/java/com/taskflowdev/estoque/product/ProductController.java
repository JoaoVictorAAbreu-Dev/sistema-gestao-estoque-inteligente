package com.taskflowdev.estoque.product;

import com.taskflowdev.estoque.product.dto.ProductRequest;
import com.taskflowdev.estoque.product.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }
    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return ProductMapper.toResponse(service.create(request));
    }
    @GetMapping
    public List<ProductResponse> list() {
        return service.list().stream().map(ProductMapper::toResponse).toList();
    }
}
