package com.taskflowdev.estoque.dashboard;

import java.math.BigDecimal;

public record DashboardSummary(long productsCount, long lowStockCount, BigDecimal inventoryValue) {}
