# 📚 Library Management System

This project is a **Spring Boot REST API** for managing a library system, where users can:

- Register and log in
- Browse available books
- Borrow books
- Return borrowed books
- View their borrowing history

---

# 🚀 Features

## 📖 Book Management
- **List all books**:  
  `GET /api/books/all`
  
- **List available books** (only those with `availableCopies > 0`):  
  `GET /api/books/available`
  
- **Search books by author, year, or both**:  
  `GET /api/books/search?author={author}&year={year}`

- **Fetch a single book by ID**:  
  `GET /api/books/{id}`

---

## 📚 Borrowing and Returning Books
- **Borrow a book** (requires authentication):  
  `POST /api/borrow/{bookId}`

- **Return a borrowed book** (requires authentication):  
  `POST /api/borrow/return/{borrowRecordId}`

- **View borrow history** (requires authentication):  
  `GET /api/borrow/history`

---

# 🛠️ Database Setup

Make sure you have **MySQL** installed and running.

1. **Create the database:**

```sql
CREATE DATABASE library_system;
```
## 🛠️ Database Optimizations

To ensure the system remains performant as data grows, the following database-level optimizations were performed:

### Indexing:
  - Add indexes on columns that frequently filter or join on (e.g., `author`, `published_year`, `available_copies`, `user_id`, `borrowed_at`).

### Connection Pooling (HikariCP)
- ✅ Adjust `maximum-pool-size` based on expected concurrent traffic and database server capacity.  
  (e.g., Set to 20-50 for medium load applications; monitor and tune.)
- ✅ Ensure `minimum-idle` connections are tuned according to expected idle periods to avoid unnecessary connection churn.
- ✅ Reduce `idle-timeout` in environments with limited connection resources.


---

### 🚀 Hibernate Optimizations
- ✅ `hibernate.jdbc.batch_size=50` is enabled — this reduces the number of round-trips to the database during bulk inserts/updates.
- ✅ `hibernate.order_inserts=true` and `hibernate.order_updates=true` help maximize batching efficiency by grouping similar SQL operations together.
- ✅ Using `MySQL8Dialect` ensures Hibernate generates optimized SQL for MySQL 8+.


---

### 🗃️ Prepared Statement Caching
- ✅ `cachePrepStmts=true`, `prepStmtCacheSize=250`, and `prepStmtCacheSqlLimit=2048` are enabled — this minimizes parsing overhead on repeated queries.
- ✅ These settings significantly reduce latency for frequently executed queries.



---



# 📜 API Quick Reference

| HTTP Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/books/all` | Get all books |
| `GET` | `/api/books/available` | Get available books |
| `GET` | `/api/books/search` | Search books by author and/or year |
| `GET` | `/api/books/{id}` | Get book details by ID |
| `POST` | `/api/borrow/{bookId}` | Borrow a book |
| `POST` | `/api/borrow/return/{borrowRecordId}` | Return a borrowed book |
| `GET` | `/api/borrow/history` | View borrow history |

---

# 💎 How to Run Locally

Swagger UI :
```
http://localhost:8080/swagger-ui/index.html
```

---


