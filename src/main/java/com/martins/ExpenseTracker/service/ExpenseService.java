package com.martins.ExpenseTracker.service;

import com.martins.ExpenseTracker.Expense;
import com.martins.ExpenseTracker.ExpenseCategory;
import com.martins.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        expense.setCategory(expenseDetails.getCategory());

        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category);
    }

    public byte[] exportToCSV() {
        List<Expense> expenses = expenseRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);

        // Write CSV header
        writer.println("ID,Description,Amount,Date,Category");

        // Write expense data
        expenses.forEach(expense -> writer.printf("%d,%s,%.2f,%s,%s%n",
            expense.getId(),
            expense.getDescription().replace(",", ";"),  // Escape commas in description
            expense.getAmount(),
            expense.getDate(),
            expense.getCategory()));

        writer.flush();
        return out.toByteArray();
    }
} 