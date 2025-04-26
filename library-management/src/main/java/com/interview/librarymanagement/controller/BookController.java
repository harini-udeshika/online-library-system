package com.interview.librarymanagement.controller;

import com.interview.librarymanagement.model.Book;
import com.interview.librarymanagement.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    /** 1) GET /api/books/all – list all books */
    @GetMapping("/all")
    public List<Book> listAll() {
        return service.findAll();
    }

    /** 2) GET /api/books/available – list only those with copies > 0 */
    @GetMapping("/available")
    public List<Book> listAvailable() {
        return service.findAvailable();
    }

    /** 3) GET /api/books/{id} – fetch single book */
    @GetMapping("/{id}")
    public Book getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    /** 4) GET /api/books/search?author=&year= – fetch available books by author and/or published year */
    @GetMapping("/search")
    public List<Book> searchAvailableBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year
    ) {
        return service.searchAvailableBooks(author, year);
    }
}
