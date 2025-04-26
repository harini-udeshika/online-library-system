package com.interview.librarymanagement.repository;

import com.interview.librarymanagement.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> { }