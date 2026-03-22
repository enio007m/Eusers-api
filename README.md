# Users API (Java + Spring Boot)

## 📌 Descrição

Este projeto fornece uma **API de usuários** utilizando **Java com Spring Boot**, organizada segundo **boas práticas de arquitetura em camadas**.

O objetivo é disponibilizar uma base padronizada para desenvolvimento contínuo, facilitando futuras implementações de funcionalidades e integrações.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot
- Maven
- MySQL
- Docker & Docker Compose

---

## 📁 Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

```text
src/main/java/jalau/usersapi
├── core
│   ├── application        # Serviços de aplicação (casos de uso)
│   └── domain
│       ├── entities       # Entidades de domínio
│       ├── repositories   # Interfaces de repositório
│       └── services       # Interfaces de serviço
├── infrastructure
│   └── mysql              # Implementação de persistência (MySQL)
└── presentation
    ├── controllers        # Controllers REST
    └── dtos               # Objetos de transferência de dados (DTOs)
```

---

## ⚙️ Configuração

Arquivo: `application.properties`

```properties
server.port=8001
spring.datasource.url=jdbc:mysql://mysql:3306/sd3db
spring.datasource.username=root
spring.datasource.password=root
```

### 🐳 Docker

O projeto utiliza **Docker** para facilitar o setup:

- **Build stage:** compila o projeto com Maven
- **Run stage:** executa o `.jar` com Java

Primeira execução (build da imagem):

```bash
docker compose up --build
```

Execuções seguintes:

```bash
docker compose up
```

A aplicação ficará disponível em: [http://localhost:8001](http://localhost:8001/)

---

## 🌐 Endpoints

O projeto inclui endpoint de **health check** disponível:

- `GET /api/v1/health` — verifica se a aplicação está rodando corretamente (retorna `200 OK`)

> Novos endpoints CRUD serão adicionados progressivamente conforme as user stories forem implementadas.

### 🧪 Testando a API

Como esta é uma API REST, os endpoints não possuem interface visual no navegador.

Você pode testar utilizando:

#### 🔹 cURL (terminal)

```bash
curl http://localhost:8001/api/v1/health
```

Resposta esperada: HTTP/1.1 200

#### 🔹 Postman
1. Baixe o Postman: https://www.postman.com/downloads/
2. Crie uma requisição `GET`
3. Use a URL: 
    ```text
    http://localhost:8001/api/v1/health
    ```
4. Clique em **Send**
> A resposta esperada é 200 OK.

---

## 🗄️ Banco de Dados

- Banco: MySQL 8
- Database: `sd3db`
- Porta: `3306`
- Host (Docker): `mysql`
- Usuário: `root`
- Senha: `root`

O banco é criado automaticamente ao subir os containers via Docker Compose.

---

## 📌 Status Geral

- Estrutura do projeto definida
- Arquitetura em camadas aplicada
- Setup com Docker configurado
- Endpoints de teste disponíveis

---

## 🔜 Próximos Passos

- Implementação dos endpoints CRUD
- Integração com JPA/Hibernate
- Adição de regras de negócio e validações
- Testes unitários e de integração