# Banking API

A simple banking backend built with Java and Spring Boot. Designed for 
learning, practice, and as a starting point for your own projects.

> ⚠️ **Learning project only** — no authentication, not production-ready.

---

## 📖 About

After testing, the system is ready for open source use. This project is 
a simple banking backend that you can plug into your own apps:

- 🎓 **Student projects** — use it as the backend for your dashboard or coursework
- 🖥️ **Frontend practice** — plug it into React/Vue/HTML and get real data
- 🏦 **Learning how banks work** — run it, create accounts, move money, see the flow
- 📋 **Starting point** — copy it and build your own project on top

It's meant for **learning and practice** — not for real money or real customers.

---

## 🏗️ Architecture

This project follows a **Layered Architecture**:

<img width="1024" height="559" alt="Architecture diagram" src="https://github.com/user-attachments/assets/074d1b19-d87b-400f-a6f4-d4c59f5e4e87" />

---

## ✨ Features

### Core Banking
- **Customer Management** — register, login, update profile, change password
- **Account Management** — open, view, update status
- **Transactions** — deposit, withdraw, transfer, view history
- **Data Integrity** — ACID transactions using `@Transactional`
- **Money Precision** — `BigDecimal` for all monetary values
- **Password Hashing** — BCrypt

### Architecture & Design
- **Layered Architecture** — clean separation of concerns
- **DTOs & Mappers** — entity ↔ DTO conversion
- **Exception Handling** — global handler with custom exceptions
- **Validation** — `@Valid`, `@NotBlank`, `@Email`, `@Pattern`, `@Positive`
- **Pagination** — `Pageable` on all list endpoints, with sorting
- **Custom IDs** — `customerCode`, `accountCode`, `transactionCode` (non-guessable)

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Database | PostgreSQL |
| ORM | JPA / Hibernate |
| Validation | Jakarta Bean Validation |
| Password Hashing | Spring Security Crypto (BCrypt) |
| Build | Maven |
| Version Control | Git |

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Setup

1. **Clone the repo**
   ```bash
   git clone https://github.com/IzanAlvaroForte/Bank_system_simple_no_security.git
   cd Bank_system_simple_no_security
