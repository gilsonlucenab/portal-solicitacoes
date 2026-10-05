# Portal de Solicitações Internas

Sistema web desenvolvido para gerenciamento de solicitações internas, permitindo que usuários autenticados cadastrem, consultem, editem, acompanhem e concluam solicitações.

O projeto foi desenvolvido utilizando uma arquitetura dividida entre frontend, backend e banco de dados, proporcionando separação de responsabilidades e organização entre as diferentes camadas da aplicação.

---

# 1. Sobre o projeto

O Portal de Solicitações Internas tem como objetivo centralizar o registro e o acompanhamento de demandas internas de uma organização.

A aplicação permite que um usuário autenticado:

- Acesse o sistema através de login;
- Cadastre novas solicitações;
- Consulte solicitações cadastradas;
- Filtre solicitações;
- Edite solicitações;
- Altere o status das solicitações;
- Exclua solicitações;
- Acompanhe a data de criação;
- Visualize o solicitante responsável pela demanda;
- Encerre uma solicitação quando o atendimento for concluído.

O sistema possui autenticação baseada em sessão e utiliza proteção contra ataques CSRF nas operações que alteram dados.

---

# 2. Objetivo

O objetivo principal do projeto é disponibilizar uma solução simples, organizada e segura para gerenciamento de solicitações internas.

A aplicação foi desenvolvida considerando um cenário corporativo no qual colaboradores precisam registrar demandas e acompanhar seu andamento.

O sistema busca proporcionar:

- Organização das demandas;
- Centralização das solicitações;
- Controle de status;
- Facilidade de consulta;
- Rastreabilidade;
- Controle de acesso;
- Interface simples e intuitiva.

---

# 3. Funcionalidades

## 3.1 Autenticação

O sistema possui autenticação de usuários através de:

- Usuário;
- Senha;
- Sessão autenticada;
- Logout;
- Proteção de rotas no frontend.

O login é realizado através do endpoint `/login`, utilizando autenticação baseada em formulário do Spring Security.

Após o login, o usuário é direcionado para a página inicial do sistema.

## 3.2 Cadastro de usuários

O backend disponibiliza um endpoint para cadastro de usuários.

Durante o cadastro são informados:

- Nome;
- Usuário;
- Senha.

As senhas são armazenadas utilizando criptografia através do `BCryptPasswordEncoder`.

Endpoint:

```text
POST /api/auth/cadastro
```

## 3.3 Cadastro de solicitações

Usuários autenticados podem criar novas solicitações.

Cada solicitação possui:

- Título;
- Descrição;
- Categoria;
- Status;
- Data de criação;
- Solicitante.

Ao criar uma solicitação, seu status inicial é:

```text
ABERTO
```

## 3.4 Consulta de solicitações

O sistema permite consultar todas as solicitações cadastradas.

A listagem apresenta:

- ID;
- Título;
- Categoria;
- Solicitante;
- Status;
- Data de criação;
- Ações disponíveis.

Endpoint:

```text
GET /api/solicitacoes
```

## 3.5 Consulta de solicitação por ID

É possível consultar uma solicitação específica utilizando seu identificador.

Endpoint:

```text
GET /api/solicitacoes/{id}
```

Exemplo:

```text
GET /api/solicitacoes/1
```

## 3.6 Filtros

A tela de solicitações possui filtros para facilitar a localização de registros.

Os filtros disponíveis são:

### Título

Permite pesquisar pelo título da solicitação.

### Categoria

Permite filtrar por:

- TI;
- RH;
- Financeiro;
- Administrativo.

### Status

Permite filtrar por:

- Aberto;
- Em atendimento;
- Concluído.

Também existe a opção:

```text
Limpar filtros
```

que remove todos os filtros aplicados.

## 3.7 Edição de solicitações

Solicitações podem ser editadas enquanto não estiverem concluídas.

Podem ser alterados:

- Título;
- Descrição;
- Categoria.

Solicitações com status:

```text
CONCLUIDO
```

não podem mais ser editadas.

Essa regra é aplicada tanto na interface frontend quanto no backend.

## 3.8 Alteração de status

O sistema permite alterar o status das solicitações.

Fluxo implementado:

```text
ABERTO
   ↓
EM_ATENDIMENTO
   ↓
CONCLUIDO
```

Na interface:

```text
Aberto
Em atendimento
Concluído
```

Quando uma solicitação está `ABERTO`, é disponibilizada a ação:

```text
Atender
```

Quando está `EM_ATENDIMENTO`, é disponibilizada a ação:

```text
Concluir
```

Quando está `CONCLUIDO`, não é mais permitido editar a solicitação.

## 3.9 Exclusão

Solicitações podem ser excluídas através da interface.

Antes da exclusão, o sistema apresenta uma confirmação ao usuário.

Endpoint:

```text
DELETE /api/solicitacoes/{id}
```

Em caso de sucesso, a API retorna:

```text
204 No Content
```

---

# 4. Tecnologias utilizadas

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Jakarta Validation
- Maven
- Swagger / OpenAPI
- BCrypt
- PostgreSQL

## Frontend

- Angular 17
- TypeScript
- HTML5
- CSS3
- Angular Router
- Angular HttpClient
- FormsModule

## Banco de dados

- PostgreSQL

## Ferramentas utilizadas

- Visual Studio Code
- IntelliJ IDEA / IDE Java
- Git
- GitHub
- Postman
- Swagger UI
- PostgreSQL
- pgAdmin

---

# 5. Arquitetura da aplicação

O projeto utiliza uma arquitetura separada em três partes principais:

```text
┌─────────────────────────────┐
│          FRONTEND           │
│          Angular            │
│                             │
│ Interface + Navegação       │
│ Formulários + Filtros       │
└──────────────┬──────────────┘
               │ HTTP
               │ REST
               ▼
┌─────────────────────────────┐
│          BACKEND            │
│       Spring Boot           │
│                             │
│ Controllers                 │
│ Services                    │
│ Repositories                │
│ Security                    │
│ DTOs                        │
│ Entities                    │
└──────────────┬──────────────┘
               │
               │ JPA / Hibernate
               ▼
┌─────────────────────────────┐
│        POSTGRESQL           │
│                             │
│ Usuários                    │
│ Solicitações                │
└─────────────────────────────┘
```

---

# 6. Estrutura do projeto

Atualmente o projeto está organizado em duas aplicações independentes.

Backend:

```text
C:\Projetos\portal-solicitacoes
```

Frontend:

```text
C:\Projetos\portal-solicitacoes-frontend
```

## Backend

Estrutura principal:

```text
portal-solicitacoes/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── br/
│       │       └── com/
│       │           └── portal/
│       │               └── solicitacoes/
│       │
│       │                   ├── config/
│       │                   ├── controller/
│       │                   ├── dto/
│       │                   ├── entity/
│       │                   ├── repository/
│       │                   └── service/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 7. Organização do Backend

## Controller

Responsável por receber as requisições HTTP e disponibilizar os endpoints da API.

Principais controllers:

```text
AuthController
SolicitacaoController
```

## Service

Contém as regras de negócio da aplicação.

Exemplos:

```text
UsuarioService
SolicitacaoService
```

O `SolicitacaoService` é responsável por operações como:

- Listagem;
- Busca;
- Criação;
- Edição;
- Alteração de status;
- Exclusão.

## Repository

Responsável pelo acesso aos dados através do Spring Data JPA.

Exemplos:

```text
UsuarioRepository
SolicitacaoRepository
```

## Entity

Representa as entidades persistidas no banco.

Principais entidades:

```text
Usuario
Solicitacao
```

## DTO

Os DTOs são utilizados para transportar dados entre as requisições e a aplicação.

Exemplos:

```text
UsuarioCadastroRequest
SolicitacaoRequest
```

---

# 8. Frontend

O frontend foi desenvolvido em Angular.

Diretório:

```text
C:\Projetos\portal-solicitacoes-frontend
```

A aplicação possui páginas e serviços separados por responsabilidade.

Estrutura aproximada:

```text
src/
└── app/
    ├── guards/
    ├── interceptors/
    ├── pages/
    │   ├── inicio/
    │   ├── login/
    │   └── solicitacoes/
    │
    ├── services/
    │   ├── auth.service.ts
    │   └── solicitacao.service.ts
    │
    ├── app.config.ts
    └── app.routes.ts
```

---

# 9. Páginas do sistema

## Página inicial

Rota:

```text
/
```

A página apresenta:

- Identidade do sistema;
- Menu de navegação;
- Mensagem de boas-vindas;
- Acesso às solicitações;
- Recursos disponíveis no portal.

## Página de login

Rota:

```text
/login
```

Permite ao usuário informar:

```text
Usuário
Senha
```

Após autenticação bem-sucedida, o usuário é direcionado para a página inicial.

## Página de solicitações

Rota:

```text
/solicitacoes
```

A página permite:

- Criar;
- Consultar;
- Filtrar;
- Editar;
- Alterar status;
- Excluir solicitações.

---

# 10. Controle de acesso

A rota:

```text
/solicitacoes
```

é protegida pelo `authGuard`.

O guard verifica o endpoint:

```text
GET /api/auth/me
```

Caso o usuário esteja autenticado:

```text
autenticado = true
```

o acesso é permitido.

Caso contrário, o usuário é redirecionado para:

```text
/login
```

Isso impede que um usuário não autenticado acesse diretamente a tela de solicitações.

---

# 11. Sessão do usuário

A autenticação utiliza sessão HTTP.

O navegador mantém o cookie de sessão:

```text
JSESSIONID
```

As requisições do Angular utilizam:

```text
withCredentials: true
```

permitindo o envio das credenciais de sessão para o backend.

---

# 12. Segurança

O projeto utiliza Spring Security para controlar autenticação e autorização.

Também foi implementada proteção CSRF.

O sistema utiliza:

```text
CookieCsrfTokenRepository
```

com o cookie:

```text
XSRF-TOKEN
```

e o header:

```text
X-XSRF-TOKEN
```

As operações que modificam dados utilizam o token CSRF.

Exemplos:

```text
POST
PUT
DELETE
```

---

# 13. CORS

O backend possui configuração de CORS permitindo a comunicação com o frontend Angular.

Origem configurada:

```text
http://localhost:4200
```

São permitidos os métodos:

```text
GET
POST
PUT
DELETE
OPTIONS
```

Também são permitidas credenciais:

```text
allowCredentials = true
```

---

# 14. Banco de dados

O sistema utiliza PostgreSQL como banco de dados relacional.

As principais informações armazenadas são:

## Usuários

Informações relacionadas aos usuários cadastrados no sistema.

## Solicitações

Informações relacionadas às demandas registradas.

Uma solicitação possui relacionamento com o usuário responsável pelo seu registro.

---

# 15. Exemplo de solicitação

Uma resposta da API pode possuir o seguinte formato:

```json
{
  "id": 3,
  "titulo": "Solicitação de suporte",
  "descricao": "Necessário verificar o equipamento.",
  "categoria": "TI",
  "status": "ABERTO",
  "dataCriacao": "2026-10-04T17:29:35.0770398",
  "solicitanteId": 1,
  "solicitanteNome": "Usuário Teste"
}
```

---

# 16. Endpoints da API

## Autenticação

### Cadastro

```http
POST /api/auth/cadastro
```

### Verificação de CSRF

```http
GET /api/auth/csrf
```

### Usuário autenticado

```http
GET /api/auth/me
```

### Login

```http
POST /login
```

### Logout

```http
POST /logout
```

## Solicitações

### Listar

```http
GET /api/solicitacoes
```

### Buscar por ID

```http
GET /api/solicitacoes/{id}
```

### Criar

```http
POST /api/solicitacoes
```

### Editar

```http
PUT /api/solicitacoes/{id}
```

### Alterar status

```http
PUT /api/solicitacoes/{id}/status
```

### Excluir

```http
DELETE /api/solicitacoes/{id}
```

---

# 17. Swagger

A API possui documentação através do Swagger/OpenAPI.

Após iniciar o backend, a interface pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação permite visualizar e testar os endpoints disponíveis.

Entre os recursos disponíveis estão:

- Autenticação;
- Cadastro;
- Solicitações;
- Consulta;
- Edição;
- Alteração de status;
- Exclusão.

---

# 18. Execução do Backend

Entre no diretório do backend:

```powershell
cd C:\Projetos\portal-solicitacoes
```

Execute:

```powershell
.\mvnw.cmd spring-boot:run
```

O backend será iniciado em:

```text
http://localhost:8080
```

---

# 19. Execução do Frontend

Abra outro terminal.

Entre no diretório:

```powershell
cd C:\Projetos\portal-solicitacoes-frontend
```

Instale as dependências, caso necessário:

```powershell
npm install
```

Execute o Angular:

```powershell
ng serve
```

A aplicação ficará disponível em:

```text
http://localhost:4200
```

---

# 20. Pré-requisitos

Para executar o projeto é necessário possuir:

- Java 21;
- Node.js;
- npm;
- Angular CLI;
- PostgreSQL;
- Git.

O backend utiliza Maven Wrapper, portanto não é necessário possuir uma instalação global do Maven para executar o projeto.

---

# 21. Configuração do banco

O PostgreSQL deve estar em execução antes de iniciar o backend.

As configurações de conexão ficam no arquivo:

```text
src/main/resources/application.properties
```

É necessário configurar:

- URL do banco;
- Usuário;
- Senha;
- Driver PostgreSQL.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/portal_solicitacoes
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

A porta utilizada deve corresponder à configuração do PostgreSQL instalado no ambiente.

---

# 22. Fluxo principal do sistema

O fluxo básico da aplicação é:

```text
Usuário
   │
   ▼
Tela de Login
   │
   ▼
Autenticação
   │
   ├── Falha ──────► Login novamente
   │
   ▼
Página Inicial
   │
   ▼
Solicitações
   │
   ├── Criar
   │
   ├── Consultar
   │
   ├── Filtrar
   │
   ├── Editar
   │
   ├── Atender
   │
   ├── Concluir
   │
   └── Excluir
   │
   ▼
Logout
   │
   ▼
Tela de Login
```

---

# 23. Regras de negócio

O sistema possui algumas regras principais.

### Regra 1 — Login

Somente usuários autenticados podem acessar as funcionalidades protegidas.

### Regra 2 — Nova solicitação

Toda nova solicitação inicia com:

```text
ABERTO
```

### Regra 3 — Atendimento

Uma solicitação aberta pode ser colocada em atendimento:

```text
ABERTO → EM_ATENDIMENTO
```

### Regra 4 — Conclusão

Uma solicitação em atendimento pode ser concluída:

```text
EM_ATENDIMENTO → CONCLUIDO
```

### Regra 5 — Edição

Solicitações concluídas não podem ser editadas.

### Regra 6 — Exclusão

A exclusão requer confirmação do usuário na interface.

---

# 24. Interface

A interface foi desenvolvida com foco em simplicidade e organização.

O layout utiliza:

- Cards;
- Tabelas;
- Formulários;
- Filtros;
- Botões de ação;
- Indicadores visuais de status;
- Navegação entre páginas;
- Layout responsivo.

As cores principais utilizadas são baseadas em tons de azul, cinza, verde, amarelo e vermelho, permitindo diferenciar ações e estados das solicitações.

---

# 25. Status visuais

Os status são apresentados com indicadores visuais:

### Aberto

Representado por indicador azul.

### Em atendimento

Representado por indicador amarelo.

### Concluído

Representado por indicador verde.

As ações destrutivas, como exclusão, são apresentadas em vermelho.

---

# 26. Validações e tratamento de erros

O sistema possui tratamento de erros tanto no frontend quanto no backend.

No frontend, erros de comunicação com a API são apresentados ao usuário através de mensagens.

Exemplo:

```text
Não foi possível carregar as solicitações.
```

ou:

```text
Não foi possível criar a solicitação.
```

No backend, as regras de negócio são aplicadas nos services.

---

# 27. Testes realizados

Durante o desenvolvimento foram realizados testes utilizando principalmente:

- Swagger;
- Postman;
- Navegador;
- Interface Angular.

Foram testados:

- Login;
- Logout;
- Cadastro;
- Consulta;
- Criação de solicitação;
- Edição;
- Exclusão;
- Alteração de status;
- Filtros;
- Proteção de rota;
- Controle de sessão;
- Proteção CSRF.

Também foram realizados testes das operações autenticadas e das respostas HTTP da API.

---

# 28. Proteção de rota no frontend

Foi implementado um `authGuard` para impedir o acesso direto à tela de solicitações sem autenticação.

Exemplo:

```text
Usuário não autenticado
        │
        ▼
/solicitacoes
        │
        ▼
authGuard
        │
        ▼
/login
```

Para usuários autenticados:

```text
Usuário autenticado
        │
        ▼
/solicitacoes
        │
        ▼
Acesso permitido
```

---

# 29. Interceptor de credenciais

O frontend possui um interceptor responsável por adicionar:

```typescript
withCredentials: true
```

às requisições HTTP.

Isso permite que os cookies utilizados na autenticação sejam enviados nas requisições entre Angular e Spring Boot.

---

# 30. Comunicação entre frontend e backend

A comunicação ocorre através de uma API REST.

O Angular realiza requisições HTTP para o backend:

```text
Angular
   │
   │ HTTP
   ▼
Spring Boot
   │
   │ JPA
   ▼
PostgreSQL
```

As respostas da API são processadas pelos services do Angular e utilizadas pelos componentes da interface.

---

# 31. Tecnologias e conceitos aplicados

Durante o desenvolvimento foram utilizados conceitos relacionados a:

- Desenvolvimento Full Stack;
- APIs REST;
- Arquitetura em camadas;
- MVC;
- Orientação a objetos;
- Injeção de dependência;
- Spring Security;
- Autenticação baseada em sessão;
- CSRF;
- CORS;
- JPA;
- Hibernate;
- DTO;
- Repository Pattern;
- Angular Components;
- Angular Services;
- Angular Guards;
- Angular Interceptors;
- Formulários;
- HTTP Client;
- PostgreSQL;
- Git;
- Swagger/OpenAPI.

---

# 32. Considerações finais

O Portal de Solicitações Internas foi desenvolvido como uma aplicação Full Stack, integrando frontend, backend e banco de dados.

O projeto contempla um fluxo completo de autenticação e gerenciamento de solicitações, desde o cadastro até a conclusão da demanda.

A aplicação possui separação entre as responsabilidades do frontend e backend, comunicação através de API REST, persistência em PostgreSQL e mecanismos de segurança utilizando Spring Security, sessão HTTP, CORS e proteção CSRF.

O frontend fornece uma interface simples e responsiva para utilização das funcionalidades disponibilizadas pela API.

O projeto também foi estruturado de forma a facilitar sua execução, manutenção e evolução futura.

---

# 33. Autor

Projeto desenvolvido como mini-projeto Full Stack.

**Portal de Solicitações Internas**

Tecnologias principais:

```text
Java
Spring Boot
Spring Security
JPA / Hibernate
PostgreSQL
Angular
TypeScript
HTML
CSS
REST API
Swagger
```
