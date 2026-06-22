import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles.css';

function App() {
  return (
    <main className="app-shell">
      <section className="hero">
        <p className="eyebrow">Sistema de Gestão de Estoque Inteligente</p>
        <h1>Controle de estoque com alertas e dashboard.</h1>
        <p className="subtitle">
          Cadastro de produtos, entrada e saída, relatório PDF e visão clara do estoque baixo.
        </p>
      </section>
      <section className="panel">
        <div className="card">
          <h2>Produtos</h2>
          <p>Cadastro e listagem de itens.</p>
        </div>
        <div className="card">
          <h2>Movimentações</h2>
          <p>Entrada e saída com regras de saldo de estoque.</p>
        </div>
        <div className="card">
          <h2>Dashboard</h2>
          <p>Baixo estoque, contagem de produtos e valor do inventário.</p>
        </div>
      </section>
    </main>
  );
}

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
