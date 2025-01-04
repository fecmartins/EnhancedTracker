package com.martins.ExpenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import com.martins.ExpenseTracker.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // PostgreSQL specific queries example
    @Query(value = "SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Expense> findByDateRange(LocalDate startDate, LocalDate endDate);
    
    // Using ILIKE for case-insensitive search (PostgreSQL specific)
    @Query(value = "SELECT * FROM expenses WHERE description ILIKE %:keyword%", nativeQuery = true)
    List<Expense> searchByDescription(String keyword);
} 