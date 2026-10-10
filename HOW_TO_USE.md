# How to Use

This guide will help you run, test, and learn from this Banking API.

---

## 1. Requirements

- Java 17 or higher
- Maven
- PostgreSQL
- Bruno or Postman for testing

---

## 2. Setup PostgreSQL

Create a database:

```sql
CREATE DATABASE bank_system_db;
```

## 3. Setup Application.properties

Create a application.properties

```application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_system_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

You don't need to create tables, columns, relationships, or indexes manually.
Spring Boot + Hibernate handles all of that for you:

When you run the app, Hibernate reads your @Entity classes.
Your @Entity classes are the blueprint for the database.
Hibernate generates the SQL and creates the tables automatically.
This is controlled by the setting spring.jpa.hibernate.ddl-auto=update.
You create the database. Spring Boot creates everything inside it.

## 4. Run the app

In terminal:

```terminal
mvn spring-boot:run
```

## 📡 API Endpoints

### Customers — `/api/customers`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/register` | Register a new customer |
| `POST` | `/login` | Login with email and password |
| `GET` | `/{customerCode}` | Get a customer's profile |
| `GET` | `/allCustomers` | List all customers (paginated) |
| `PUT` | `/{customerCode}` | Update customer profile |
| `PATCH` | `/{customerCode}/password` | Change customer password |

### Accounts — `/api/accounts`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/{customerCode}` | Open a new account for a customer |
| `GET` | `/{accountCode}` | View account details |
| `GET` | `/customer/{customerCode}` | List a customer's accounts (paginated) |
| `PATCH` | `/{accountCode}/status` | Update account status |

### Transactions — `/api/transactions`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/{accountCode}/deposit` | Deposit money into an account |
| `POST` | `/{accountCode}/withdraw` | Withdraw money from an account |
| `POST` | `/transfer` | Transfer money between two accounts |
| `GET` | `/{transactionCode}` | View transaction details |
| `GET` | `/{accountCode}/summary` | Transaction history for an account (paginated) |

All URLs are relative to http://localhost:8080.

## 📄 Pagination

All list endpoints support pagination and sorting via query parameters:

| Parameter | Type | Description | Default |
|-----------|------|-------------|---------|
| `page` | `int` | Page number (0-based) | `0` |
| `size` | `int` | Number of items per page | `10` |
| `sort` | `string` | Field and direction (`field,asc` or `field,desc`) | `createdAt,desc` |

### Paginated Endpoints

| Endpoint | What It Returns |
|----------|-----------------|
| `GET /api/customers/allCustomers` | All customers |
| `GET /api/accounts/customer/{customerCode}` | Accounts for a customer |
| `GET /api/transactions/{accountCode}/summary` | Transactions for an account |

### Example

**Response:**

```json
{
  "content": [
    {
      "transactionCode": "TX-BBZJGZ1CGY1Y",
      "transactionType": "TRANSFER",
      "amount": 100,
      "createdAt": "2026-10-10T17:33:59"
    }
  ],
  "totalElements": 23,
  "totalPages": 5,
  "number": 0,
  "size": 5,
  "first": true,
  "last": false,
  "empty": false
}
