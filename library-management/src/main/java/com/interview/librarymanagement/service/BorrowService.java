package com.interview.librarymanagement.service;

import com.interview.librarymanagement.model.Book;
import com.interview.librarymanagement.model.BorrowRecord;
import com.interview.librarymanagement.model.User;
import com.interview.librarymanagement.repository.BookRepository;
import com.interview.librarymanagement.repository.BorrowRecordRepository;
import com.interview.librarymanagement.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRecordRepository borrowRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public BorrowService(BorrowRecordRepository borrowRepo, BookRepository bookRepo, UserRepository userRepo) {
        this.borrowRepo = borrowRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    /**  Borrow a book */
    @Transactional
    public BorrowRecord borrowBook(Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies for this book.");
        }

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowedAt(Instant.now());

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        return borrowRepo.save(record);
    }

    /**  Return a book */
    @Transactional
    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRepo.findById(borrowRecordId)
                .orElseThrow(() -> new EntityNotFoundException("Borrow record not found"));

        if (record.getReturnedAt() != null) {
            throw new IllegalStateException("Book already returned.");
        }

        record.setReturnedAt(Instant.now());

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        return borrowRepo.save(record);
    }

 @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<BorrowRecord> getBorrowHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return borrowRepo.findByUserId(user.getId());
    }

}
