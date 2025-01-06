package com.martins.ExpenseTracker.controller;

import com.martins.ExpenseTracker.Expense;
import com.martins.ExpenseTracker.ExpenseCategory;
import com.martins.ExpenseTracker.CSVImporter;
import com.martins.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable ExpenseCategory category) {
        return expenseService.getExpensesByCategory(category);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportToCSV() {
        byte[] csvData = expenseService.exportToCSV();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "expenses.csv");
        
        return ResponseEntity.ok()
            .headers(headers)
            .body(csvData);
    }

    @PostMapping("/import/csv")
    public ResponseEntity<String> importFromCSV(@RequestParam("file") MultipartFile file) {
        try {
            List<Expense> importedExpenses = CSVImporter.importExpenses(file);
            expenseService.saveAllExpenses(importedExpenses);
            return ResponseEntity.ok(String.format("Successfully imported %d expenses", importedExpenses.size()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error importing CSV: " + e.getMessage());
        }
    }
} 