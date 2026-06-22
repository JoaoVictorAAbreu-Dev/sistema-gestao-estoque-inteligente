package com.taskflowdev.estoque.movement.dto;

import com.taskflowdev.estoque.movement.MovementType;

import java.time.Instant;
import java.util.UUID;

public record StockMovementResponse(
        UUID id,
        String sku,
        MovementType type,
        int quantity,
        Instant createdAt
) {}
