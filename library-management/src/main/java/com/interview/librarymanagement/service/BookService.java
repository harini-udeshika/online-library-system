package com.interview.librarymanagement.service;

import com.interview.librarymanagement.model.Book;
import com.interview.librarymanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    /** List all books (not paginated anymore) */
    public List<Book> findAll() {
        return repo.findAll();
    }

    /** List only available books (not paginated anymore) */
    public List<Book> findAvailable() {
        return repo.findByAvailableCopiesGreaterThan(0, Pageable.unpaged()).getContent();
    }

    /** Fetch one by id or throw 404 */
    public Book findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
    }

    /** Find available books optionally by author and/or year */
    public List<Book> searchAvailableBooks(String author, Integer year) {
        return repo.searchAvailableBooks(author, year);
    }

}
