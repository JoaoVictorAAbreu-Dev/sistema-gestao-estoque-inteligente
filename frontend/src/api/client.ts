import type { AuthResponse, DashboardSummary, Product, StockMovement } from '../types';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080';

async function request<T>(path: string, token: string | null, init?: RequestInit): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...init,
    headers: {
      'Content-Type': 'application/json',
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...(init?.headers ?? {}),
    },
  });

  if (!response.ok) {
    throw new Error(`Request failed with status ${response.status}`);
  }

  return response.json() as Promise<T>;
}

export const api = {
  login(email: string, password: string) {
    return request<AuthResponse>('/api/auth/login', null, {
      method: 'POST',
      body: JSON.stringify({ email, password }),
    });
  },
  register(email: string, password: string) {
    return request<AuthResponse>('/api/auth/register', null, {
      method: 'POST',
      body: JSON.stringify({ email, password }),
    });
  },
  dashboard(token: string) {
    return request<DashboardSummary>('/api/dashboard', token);
  },
  products(token: string) {
    return request<Product[]>('/api/products', token);
  },
  createProduct(token: string, payload: Omit<Product, 'id' | 'lowStock'>) {
    return request<Product>('/api/products', token, {
      method: 'POST',
      body: JSON.stringify(payload),
    });
  },
  entry(token: string, sku: string, quantity: number) {
    return request<StockMovement>('/api/movements/entry', token, {
      method: 'POST',
      body: JSON.stringify({ sku, quantity }),
    });
  },
  exit(token: string, sku: string, quantity: number) {
    return request<StockMovement>('/api/movements/exit', token, {
      method: 'POST',
      body: JSON.stringify({ sku, quantity }),
    });
  },
};
