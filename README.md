# 📚 Library Management System - REST API

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

2. **Configure `application.properties`:**

> - Replace with your actual MySQL username and password if different.
> - Make sure you set `JWT_SECRET` environment variable before running the application.

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


