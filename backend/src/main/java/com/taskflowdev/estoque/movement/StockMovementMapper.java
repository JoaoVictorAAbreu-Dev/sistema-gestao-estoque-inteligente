package com.taskflowdev.estoque.movement;

import com.taskflowdev.estoque.movement.dto.StockMovementResponse;

public final class StockMovementMapper {
    private StockMovementMapper() {}
    public static StockMovementResponse toResponse(StockMovement movement) {
        return new StockMovementResponse(
                movement.getId(),
                movement.getProduct().getSku(),
                movement.getType(),
                movement.getQuantity(),
                movement.getCreatedAt()
        );
    }
}
