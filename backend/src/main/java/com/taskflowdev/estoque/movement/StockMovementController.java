package com.taskflowdev.estoque.movement;

import com.taskflowdev.estoque.movement.dto.StockMovementRequest;
import com.taskflowdev.estoque.movement.dto.StockMovementResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class StockMovementController {
    private final StockMovementService service;
    public StockMovementController(StockMovementService service) { this.service = service; }
    @PostMapping("/entry")
    public StockMovementResponse entry(@Valid @RequestBody StockMovementRequest request) {
        return StockMovementMapper.toResponse(service.registerEntry(request.sku(), request.quantity()));
    }
    @PostMapping("/exit")
    public StockMovementResponse exit(@Valid @RequestBody StockMovementRequest request) {
        return StockMovementMapper.toResponse(service.registerExit(request.sku(), request.quantity()));
    }
    @GetMapping("/{sku}")
    public List<StockMovementResponse> list(@PathVariable String sku) {
        return service.listBySku(sku).stream().map(StockMovementMapper::toResponse).toList();
    }
}
