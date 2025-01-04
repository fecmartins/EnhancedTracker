package com.martins.ExpenseTracker.repository;

import com.martins.ExpenseTracker.Expense;
import com.martins.ExpenseTracker.ExpenseCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Find by category
    List<Expense> findByCategory(ExpenseCategory category);

    // Find by date range
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Find by amount greater than
    List<Expense> findByAmountGreaterThan(BigDecimal amount);

    // Find by amount less than
    List<Expense> findByAmountLessThan(BigDecimal amount);

    // Find by description containing (case-insensitive)
    @Query("SELECT e FROM Expense e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Expense> findByDescriptionContainingIgnoreCase(@Param("keyword") String keyword);

    // Find by category and date range
    List<Expense> findByCategoryAndDateBetween(
        ExpenseCategory category, 
        LocalDate startDate, 
        LocalDate endDate
    );

    // Calculate total amount by category
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category = :category")
    BigDecimal sumAmountByCategory(@Param("category") ExpenseCategory category);

    // Calculate total amount by date range
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
    BigDecimal sumAmountByDateRange(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate
    );
} 