package com.interview.librarymanagement.repository;

import com.interview.librarymanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthor(String author, Pageable page);
    Page<Book> findByPublishedYear(Integer year, Pageable page);

    Page<Book> findByAvailableCopiesGreaterThan(int minCopies, Pageable page);

    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0 " +
            "AND (:author IS NULL OR b.author = :author) " +
            "AND (:year IS NULL OR b.publishedYear = :year)")
    List<Book> searchAvailableBooks(@Param("author") String author, @Param("year") Integer year);
}
