package com.interview.librarymanagement.controller;

import com.interview.librarymanagement.model.BorrowRecord;
import com.interview.librarymanagement.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /** 1) POST /api/borrow/{bookId} - Borrow a book */
    @PostMapping("/{bookId}")
    public BorrowRecord borrowBook(@PathVariable Long bookId) {
        return borrowService.borrowBook(bookId);
    }

    /** 2) POST /api/borrow/return/{borrowRecordId} - Return a book */
    @PostMapping("/return/{borrowRecordId}")
    public BorrowRecord returnBook(@PathVariable Long borrowRecordId) {
        return borrowService.returnBook(borrowRecordId);
    }

    /** 3) GET /api/borrow/history - Get borrow history of current user */
    @GetMapping("/history")
    public List<BorrowRecord> getBorrowHistory() {
        return borrowService.getBorrowHistory();
    }
}