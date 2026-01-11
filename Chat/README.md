# Java Chat Application - JDBC Learning Project

<div align="center">

**A comprehensive JDBC learning project demonstrating database operations, connection pooling, and the Repository pattern through a chat application.**

</div>

---

## Table of Contents

- [Overview](#-overview)
- [Project Architecture](#-project-architecture)
- [Database Design](#-database-design)
- [What We Learned](#-what-we-learned)
- [Skills Gained](#-skills-gained)
- [How to Use](#-how-to-use)
- [Technologies Used](#-technologies-used)

---

## Overview

This project is a hands-on learning exercise focused on mastering **JDBC (Java Database Connectivity)** through building a chat application backend. It covers essential database operations from basic CRUD to advanced techniques like pagination with CTEs (Common Table Expressions).

The application models a real-world chat system where users can create chatrooms, participate in conversations, and exchange messages.

---

## Project Architecture

```
Chat/
├── pom.xml                              # Maven configuration
├── src/
│   └── main/
│       ├── java/fr/fortyTwo/chat/
│       │   ├── app/                     # Application entry point
│       │   ├── models/                  # Entity classes (User, Chatroom, Message)
│       │   ├── repositories/            # Data access layer (DAO pattern)
│       │   └── exceptions/              # Custom exception handling
│       └── resources/
│           ├── schema.sql               # Database schema definition
│           └── data.sql                 # Sample data for testing
```

### Layer Breakdown

| Layer | Purpose |
|-------|---------|
| **Models** | Java entities representing database tables with proper relationships |
| **Repositories** | Data access layer implementing the Repository/DAO pattern with interfaces and JDBC implementations |
| **Configuration** | Database connection pooling setup using HikariCP |
| **Exceptions** | Custom exceptions for better error handling and validation |
| **Application** | Entry point demonstrating all CRUD operations |

---

## Database Design

### Entity Relationships

```
┌─────────┐     owns      ┌───────────┐     contains    ┌──────────┐
│  User   │──────1:N──────│  Chatroom │───────1:N───────│  Message │
└─────────┘               └───────────┘                 └──────────┘
     │                          │                            │
     │    participates (M:N)    │                            │
     └──────────────────────────┘                            │
                                                             │
     ┌───────────────────────────────────────────────────────┘
     │ authored by (N:1)
     ▼
┌─────────┐
│  User   │
└─────────┘
```

### Table Overview

| Table | Purpose | Relationships |
|-------|---------|---------------|
| `users` | Store user accounts | Owns chatrooms (1:N), Authors messages (1:N) |
| `chatrooms` | Store chat rooms | Belongs to owner (N:1), Contains messages (1:N) |
| `messages` | Store chat messages | Belongs to author (N:1), Belongs to room (N:1) |
| `user_chatrooms` | Junction table | Links users and chatrooms (M:N) |

---

## What We Learned

### 🔹 Database Schema Design

- **Relational modeling**: Designing tables with proper relationships between entities
- **Primary keys**: Using `BIGSERIAL` for auto-incrementing identifiers
- **Foreign keys**: Establishing referential integrity between tables
- **One-to-Many relationships**: A user owns multiple chatrooms; a chatroom contains multiple messages
- **Many-to-Many relationships**: Users can participate in multiple chatrooms through a junction table
- **Composite primary keys**: Using `(user_id, chatroom_id)` in the junction table

### 🔹 JDBC Fundamentals

- **Connection management**: Establishing connections to PostgreSQL using JDBC driver
- **PreparedStatement**: Writing parameterized queries to prevent SQL injection
- **ResultSet processing**: Iterating through query results and extracting data
- **Resource management**: Using try-with-resources for automatic cleanup of connections and statements
- **Exception handling**: Properly catching and handling `SQLException`

### 🔹 Connection Pooling with HikariCP

- **Why pooling matters**: Reusing connections instead of creating new ones for each request
- **Configuration options**: Setting pool size, timeouts, and connection lifecycle
- **Performance tuning**: Optimizing pool settings for application needs
- **Leak detection**: Identifying connections that aren't properly returned to the pool

### 🔹 CRUD Operations

- **Create (INSERT)**: Inserting new records and retrieving auto-generated IDs using `RETURN_GENERATED_KEYS`
- **Read (SELECT)**: Fetching data with JOIN queries to retrieve related entities
- **Update**: Modifying existing records while handling NULL values with `setNull()`
- **Delete**: (Extensible pattern for future implementation)

### 🔹 Advanced Query Techniques

- **JOIN operations**: Using `INNER JOIN` and `LEFT JOIN` to fetch related data in a single query
- **Pagination**: Implementing `LIMIT` and `OFFSET` for efficient data retrieval
- **CTEs (Common Table Expressions)**: Building complex, readable queries using the `WITH` clause
- **Subqueries**: Filtering data based on results from other queries

### 🔹 Avoiding the N+1 Problem

We learned to fetch all related data in a **single query** instead of making separate queries for each entity. This dramatically improves performance when dealing with collections of related objects.

### 🔹 Object-Relational Mapping (Manual)

- **ResultSet to Object mapping**: Converting database rows into Java objects
- **Handling duplicates**: Using Maps and Sets to aggregate multiple result rows into single objects
- **Null handling**: Using `wasNull()` to check for SQL NULL values
- **Nested object construction**: Building complex object graphs from flat query results

### 🔹 Design Patterns

- **Repository pattern**: Abstracting data access behind interfaces
- **DAO (Data Access Object)**: Separating business logic from database operations
- **Optional pattern**: Using `Optional<T>` for nullable return values
- **Dependency injection**: Passing `DataSource` to repositories for flexibility and testability

### 🔹 Data Validation

- **Foreign key verification**: Checking that referenced entities exist before INSERT/UPDATE
- **Custom exceptions**: Creating meaningful exception types like `NotSavedSubEntityException`
- **Entity state validation**: Ensuring objects have valid IDs before database operations

---

## Skills Gained

| Category | Skills |
|----------|--------|
| **JDBC Mastery** | Connection, PreparedStatement, ResultSet, resource management |
| **SQL Expertise** | JOINs, CTEs, pagination, subqueries, aggregation |
| **Design Patterns** | Repository/DAO pattern, interface-based programming, Optional pattern |
| **Resource Management** | try-with-resources, connection pooling configuration |
| **Data Validation** | Custom exceptions, foreign key verification, entity state checks |
| **Complex Data Mapping** | ResultSet to nested Java objects, deduplication strategies |
| **Performance** | Connection pooling, avoiding N+1 queries, efficient pagination |

---

## How to Use

### Prerequisites

- Java 21 or higher
- PostgreSQL database
- Maven 3.x

### 1️⃣ Setup Database

```bash
# Create the database
psql -U postgres -c "CREATE DATABASE chat_db;"

# Run schema and data scripts
psql -U postgres -d chat_db -f src/main/resources/schema.sql
psql -U postgres -d chat_db -f src/main/resources/data.sql
```

### 2️⃣ Configure Connection

Update the database configuration in `DatabaseConfig.java` with your PostgreSQL credentials:
- JDBC URL
- Username
- Password

### 3️⃣ Build and Run

```bash
# Compile the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="fr.fortyTwo.chat.app.Program"
```

### 4️⃣ Test the Operations

The application demonstrates:
1. **Find by ID**: Retrieve a message with its author and chatroom
2. **Save**: Create a new message and get its generated ID
3. **Update**: Modify an existing message
4. **Find All with Pagination**: Retrieve users with their created and participated chatrooms

---

## Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Core programming language |
| **PostgreSQL** | Latest | Relational database |
| **JDBC** | 4.3+ | Database connectivity API |
| **HikariCP** | 7.0.2 | High-performance connection pooling |
| **Maven** | 3.x | Build and dependency management |
