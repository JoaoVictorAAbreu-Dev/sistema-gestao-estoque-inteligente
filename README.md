# Sistema de Gestão de Estoque Inteligente

![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![React](https://img.shields.io/badge/React-TypeScript-61DAFB)

Plataforma de controle de estoque desenvolvida para portfólio, com backend em Spring Boot e interface web em React. O projeto demonstra organização em camadas, persistência em PostgreSQL, autenticação com JWT e uma visão operacional do estoque por meio de dashboard e relatórios.

## Visão Geral

O sistema foi desenhado para simular um fluxo real de gestão de estoque em uma operação pequena ou média. Ele permite cadastrar produtos, registrar entradas e saídas, monitorar níveis mínimos e consultar informações consolidadas para apoio à decisão.

## Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Docker
- React
- TypeScript
- Vite
- Swagger/OpenAPI
- JUnit
- Mockito

## Funcionalidades

- Cadastro de produtos
- Controle de entrada e saída de itens
- Alerta de estoque baixo
- Dashboard operacional
- Relatórios de apoio à análise
- API documentada com Swagger

## Arquitetura

O backend está dividido em módulos com responsabilidades claras:

- `auth/` autenticação de usuários
- `product/` cadastro e consulta de produtos
- `movement/` movimentações de estoque
- `dashboard/` indicadores consolidados
- `report/` geração de relatórios
- `security/` JWT e filtros de segurança
- `config/` configuração da aplicação
- `exception/` tratamento centralizado de erros

A interface web consome a API por meio de chamadas isoladas, mantendo o frontend simples e de fácil manutenção.

## Estrutura do Projeto

- `backend/` aplicação Spring Boot
- `frontend/` interface React
- `docker-compose.yml` serviços de infraestrutura
- `.env.example` variáveis de ambiente esperadas

## Como Executar Localmente

1. Copie `.env.example` para `.env`.
2. Suba a infraestrutura:
   ```powershell
   docker compose up -d
   ```
3. Execute o backend:
   ```powershell
   .\mvnw.cmd -f backend\pom.xml spring-boot:run
   ```
4. Execute o frontend:
   ```powershell
   cd frontend
   npm install
   npm run dev
   ```

## Documentação da API

Com a aplicação ativa, a interface do Swagger está disponível em:

- `http://localhost:8080/swagger-ui/index.html`

## Testes

Backend:

```powershell
.\mvnw.cmd -f backend\pom.xml test
```

Frontend:

```powershell
cd frontend
npm run build
```

## Capturas de Execução

![Tela inicial do Sistema de Gestão de Estoque Inteligente](C:/Users/jv921/Documents/Codex/2026-06-21/que/outputs/projeto3-home.png)

## Decisões de Projeto

- PostgreSQL é usado como armazenamento principal.
- JWT protege os recursos sensíveis da API.
- O frontend foi mantido objetivo para refletir um produto funcional, não um protótipo visual.
- As regras de negócio ficam nos serviços para facilitar evolução e testes.

## Melhorias Futuras

- adicionar filtros por categoria e nível de estoque;
- implementar exportação de relatórios em PDF;
- criar páginas de auditoria de movimentações;
- incluir testes de integração adicionais para o fluxo de estoque.
