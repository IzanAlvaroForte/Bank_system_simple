# Banking API

A secure, production-ready Banking API built with Java and Spring Boot. 
Designed with enterprise architecture in mind — layered structure, ACID transactions, and a focus on data integrity.

---

## 🏗️ Architecture

This project follows a **Layered Architecture** (Controller → Service → Repository → Entity).

┌────────────────────────────────────────────────────────────────┐
│ CONTROLLERS                                                    │
│ CustomerController | AccountController | TransactionController │
└──────────────────────────┬─────────────────────────────────────┘
│
▼
┌───────────────────────────────────────────────────────┐
│ SERVICES │                                            │
│ CustomerService | AccountService | TransactionService │
└──────────────────────────┬────────────────────────────┘
│
▼
┌──────────────────────────────────────────────┐
│ REPOSITORIES │                               │
│ CustomerRepo | AccountRepo | TransactionRepo │
└──────────────────────────┬───────────────────┘
│
▼
┌─────────────────────────────────────┐
│ DATABASE │                          │
│ customers | accounts | transactions │
└─────────────────────────────────────┘


---

## ✨ Features

### Core Banking
- **Customer Management** — Register, login, update profile, change password
- **Account Management** — Open, view, update status, close accounts
- **Transactions** — Deposit, withdraw, transfer, view history
- **Data Integrity** — ACID transactions using `@Transactional`
- **Money Precision** — `BigDecimal` for all monetary values

### Architecture & Design
- **Layered Architecture** — Clean separation of concerns
- **DTOs & Mappers** — Entity ↔ DTO conversion
- **Exception Handling** — Global handler with custom exceptions
- **Validation** — `@Valid`, `@NotBlank`, `@Email`, `@Size`
- **Pagination** — `Pageable` on all list endpoints
- **Custom IDs** — `customerCode`, `accountCode`, `transactionCode` (non-guessable)

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| ORM | JPA / Hibernate |
| Build | Maven |
| Version Control | Git |

## 📌 Status

🚧 **In Development** — Core features are built. Security and deployment are next.

---

*Built with Java, Spring Boot, and PostgreSQL. MIT License*
