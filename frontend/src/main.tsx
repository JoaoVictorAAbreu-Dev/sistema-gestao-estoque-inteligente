import React from 'react';
import ReactDOM from 'react-dom/client';
import { api } from './api/client';
import type { DashboardSummary, Product } from './types';
import './styles.css';

const emptyProduct = {
  sku: '',
  name: '',
  purchasePrice: 10,
  salePrice: 15,
  stockQuantity: 5,
  lowStockThreshold: 2,
};

function App() {
  const [token, setToken] = React.useState('');
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [products, setProducts] = React.useState<Product[]>([]);
  const [dashboard, setDashboard] = React.useState<DashboardSummary>({ productsCount: 0, lowStockCount: 0, inventoryValue: 0 });
  const [productForm, setProductForm] = React.useState(emptyProduct);
  const [movementSku, setMovementSku] = React.useState('');
  const [movementQuantity, setMovementQuantity] = React.useState(1);
  const [message, setMessage] = React.useState('Entre ou crie uma conta para gerenciar o estoque.');
  const [loading, setLoading] = React.useState(false);

  function formatCurrency(value: number) {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value);
  }

  async function refresh(currentToken = token) {
    const [nextProducts, nextDashboard] = await Promise.all([
      api.products(currentToken),
      api.dashboard(currentToken),
    ]);
    setProducts(nextProducts);
    setDashboard(nextDashboard);
  }

  async function authenticate(mode: 'login' | 'register') {
    if (!email.includes('@') || password.length < 8) {
      setMessage('Informe email valido e senha com pelo menos 8 caracteres.');
      return;
    }
    setLoading(true);
    try {
      const result = mode === 'login'
        ? await api.login(email, password)
        : await api.register(email, password);
      setToken(result.token);
      await refresh(result.token);
      setMessage(mode === 'login' ? 'Estoque carregado.' : 'Conta criada e estoque carregado.');
    } catch (error) {
      setMessage(error instanceof Error ? error.message : 'Erro ao autenticar.');
    } finally {
      setLoading(false);
    }
  }

  async function createProduct(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!token || !productForm.sku || !productForm.name) {
      setMessage('Informe SKU, nome e esteja autenticado.');
      return;
    }
    await api.createProduct(token, productForm);
    setProductForm(emptyProduct);
    await refresh();
    setMessage('Produto cadastrado.');
  }

  async function registerMovement(type: 'entry' | 'exit') {
    if (!token || !movementSku || movementQuantity <= 0) {
      setMessage('Informe SKU, quantidade e esteja autenticado.');
      return;
    }
    if (type === 'entry') {
      await api.entry(token, movementSku, movementQuantity);
      setMessage('Entrada registrada.');
    } else {
      await api.exit(token, movementSku, movementQuantity);
      setMessage('Saida registrada.');
    }
    await refresh();
  }

  return (
    <main className="app-shell">
      <aside className="sidebar">
        <p className="eyebrow">TaskFlow Dev</p>
        <h1>Estoque Inteligente</h1>
        <form className="stack" onSubmit={(event) => { event.preventDefault(); authenticate('login'); }}>
          <input value={email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" />
          <input value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Senha" type="password" />
          <button type="submit" disabled={loading}>{loading ? 'Carregando...' : 'Entrar'}</button>
          <button type="button" disabled={loading} onClick={() => authenticate('register')}>Criar conta</button>
        </form>
        <p className="message">{message}</p>
      </aside>

      <section className="workspace">
        <div className="metrics">
          <article>
            <span>Produtos</span>
            <strong>{dashboard.productsCount}</strong>
          </article>
          <article>
            <span>Baixo estoque</span>
            <strong>{dashboard.lowStockCount}</strong>
          </article>
          <article>
            <span>Valor inventario</span>
            <strong>{formatCurrency(dashboard.inventoryValue)}</strong>
          </article>
        </div>

        <section className="operations">
          <form className="surface stack" onSubmit={createProduct}>
            <h2>Novo produto</h2>
            <input value={productForm.sku} onChange={(event) => setProductForm({ ...productForm, sku: event.target.value })} placeholder="SKU" />
            <input value={productForm.name} onChange={(event) => setProductForm({ ...productForm, name: event.target.value })} placeholder="Nome" />
            <input type="number" value={productForm.purchasePrice} onChange={(event) => setProductForm({ ...productForm, purchasePrice: Number(event.target.value) })} placeholder="Custo" />
            <input type="number" value={productForm.salePrice} onChange={(event) => setProductForm({ ...productForm, salePrice: Number(event.target.value) })} placeholder="Venda" />
            <input type="number" value={productForm.stockQuantity} onChange={(event) => setProductForm({ ...productForm, stockQuantity: Number(event.target.value) })} placeholder="Estoque" />
            <input type="number" value={productForm.lowStockThreshold} onChange={(event) => setProductForm({ ...productForm, lowStockThreshold: Number(event.target.value) })} placeholder="Minimo" />
            <button type="submit" disabled={!token}>Cadastrar</button>
          </form>

          <div className="surface stack">
            <h2>Movimentacao</h2>
            <input value={movementSku} onChange={(event) => setMovementSku(event.target.value)} placeholder="SKU" />
            <input type="number" value={movementQuantity} onChange={(event) => setMovementQuantity(Number(event.target.value))} placeholder="Quantidade" />
            <div className="button-row">
              <button type="button" disabled={!token} onClick={() => registerMovement('entry')}>Entrada</button>
              <button type="button" disabled={!token} onClick={() => registerMovement('exit')}>Saida</button>
            </div>
          </div>
        </section>

        <section className="inventory">
          <h2>Produtos cadastrados</h2>
          <div className="table">
            {products.length === 0 ? (
              <p>Nenhum produto carregado.</p>
            ) : products.map((product) => (
              <article key={product.id} className={product.lowStock ? 'row low-stock' : 'row'}>
                <strong>{product.sku}</strong>
                <span>{product.name}</span>
                <span>{product.stockQuantity} un.</span>
                <span>{formatCurrency(product.salePrice)}</span>
                <span>{product.lowStock ? 'Alerta' : 'OK'}</span>
              </article>
            ))}
          </div>
        </section>
      </section>
    </main>
  );
}

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
