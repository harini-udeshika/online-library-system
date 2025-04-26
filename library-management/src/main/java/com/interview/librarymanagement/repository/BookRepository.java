package com.interview.librarymanagement.repository;

import com.interview.librarymanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthor(String author, Pageable page);
    Page<Book> findByPublishedYear(Integer year, Pageable page);
}
