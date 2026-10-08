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

In termainal:

```terminal
mvn spring-boot:run
```
