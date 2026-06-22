<h1>Sistema de Gestao de Estoque Inteligente</h1>

<p>
  <img src="https://github.com/JoaoVictorAAbreu-Dev/sistema-gestao-estoque-inteligente/actions/workflows/ci.yml/badge.svg" alt="CI" />
  <img src="https://img.shields.io/badge/Java-21-red" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.x-brightgreen" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/React-TypeScript-61DAFB" alt="React TypeScript" />
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue" alt="PostgreSQL" />
</p>

<p>
  Fullstack inventory management platform with product registration, stock entries and exits, low-stock alerts, operational dashboard, reports and API documentation.
</p>

<h2>Features</h2>
<ul>
  <li>User registration and login with JWT.</li>
  <li>Product registration and listing.</li>
  <li>Stock entry and stock exit operations.</li>
  <li>Low-stock alerting.</li>
  <li>Dashboard with product count, low-stock count and inventory value.</li>
  <li>React interface connected to the API.</li>
</ul>

<h2>Architecture</h2>
<ul>
  <li><code>backend</code>: Spring Boot API with layered business services.</li>
  <li><code>frontend</code>: React, TypeScript and Vite.</li>
  <li><code>docker-compose.yml</code>: PostgreSQL infrastructure.</li>
</ul>

<h2>Run Locally</h2>
<pre><code>docker compose up -d
.\mvnw.cmd -f backend\pom.xml spring-boot:run
cd frontend
npm install
npm run dev</code></pre>

<h2>Tests</h2>
<pre><code>.\mvnw.cmd -f backend\pom.xml test
cd frontend
npm run build</code></pre>

<h2>QA Checklist</h2>
<ul>
  <li>Backend business-rule tests included.</li>
  <li>Frontend production build verified.</li>
  <li>GitHub Actions CI configured.</li>
  <li>Swagger available at <code>http://localhost:8080/swagger-ui/index.html</code>.</li>
  <li>Docker Compose configured for PostgreSQL.</li>
  <li>Execution screenshot included.</li>
</ul>

<h2>Preview</h2>
<p>
  <img src="assets/home.png" alt="Inventory UI preview" style="max-width: 100%;" />
</p>
