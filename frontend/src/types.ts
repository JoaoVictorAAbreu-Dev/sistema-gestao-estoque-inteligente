export type AuthResponse = {
  token: string;
};

export type Product = {
  id: string;
  sku: string;
  name: string;
  purchasePrice: number;
  salePrice: number;
  stockQuantity: number;
  lowStockThreshold: number;
  lowStock: boolean;
};

export type DashboardSummary = {
  productsCount: number;
  lowStockCount: number;
  inventoryValue: number;
};

export type StockMovement = {
  id: string;
  sku: string;
  type: 'ENTRY' | 'EXIT';
  quantity: number;
  createdAt: string;
};
