package com.interview.librarymanagement.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "books",
        indexes = {
                @Index(columnList = "author", name = "idx_book_author"),
                @Index(columnList = "published_year", name = "idx_book_year")
        })
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

}