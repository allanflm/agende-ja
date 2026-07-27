# AgendaJá

Sistema de agendamento e cobrança para profissionais autônomos (barbeiros, manicures,
personal trainers, professores particulares). Substitui o controle manual em caderno/WhatsApp
por agenda, controle de pagamentos e dashboard de negócio.

## Stack

- Java 21
- Spring Boot 4.1.0
- Spring Web, Spring Data JPA
- PostgreSQL
- Maven
- Spring Boot DevTools

## Arquitetura

Camadas padrão Spring Boot:
- `model` (ou `entity`): entidades JPA
- `repository`: interfaces Spring Data JPA (extends JpaRepository)
- `service`: regras de negócio
- `controller`: endpoints REST

## Modelo de dados

- **Cliente**: id, nome, telefone, email
- **Servico**: id, nome, preco, duracaoMinutos
- **Agendamento**: id, cliente_id (FK), servico_id (FK), dataHora, status (AGENDADO, CONCLUIDO, CANCELADO)
- **Pagamento**: id, agendamento_id (FK, relação 1:1 opcional), valor, formaPagamento, statusPagamento, dataPagamento

Relações: Cliente 1:N Agendamento, Servico 1:N Agendamento, Agendamento 1:0..1 Pagamento.

## Convenções

- Nomes de classes, métodos e variáveis em inglês (padrão do ecossistema Java)
- Comentários e mensagens de commit em português
- Endpoints REST em `/api/{recurso}` (ex: `/api/clientes`, `/api/agendamentos`)
- Usar DTOs para request/response, nunca expor entidades JPA diretamente nos endpoints
- Validação de entrada com Bean Validation (`@Valid`, `@NotNull`, etc.)
- Tratamento de erros centralizado com `@ControllerAdvice`

## Commits

Mensagens de commit descritivas e em português, ex: "adiciona cadastro de clientes",
"cria endpoint de agendamento". Commits pequenos e frequentes.

## Escopo do MVP (v1)

1. CRUD de Cliente
2. CRUD de Servico
3. Criar/listar/cancelar Agendamento
4. Marcar Agendamento como pago (cria Pagamento)
5. Dashboard básico (faturamento do mês, taxa de inadimplência)

Fora do escopo do MVP (fica pra depois): autenticação de usuários, agendamento público
via link para o cliente, integração de pagamento online (Stripe).