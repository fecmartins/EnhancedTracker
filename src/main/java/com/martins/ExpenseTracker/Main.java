package com.martins.ExpenseTracker;

import com.martins.ExpenseTracker.Expense;
import com.martins.ExpenseTracker.ExpenseCategory;
import com.martins.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private final ExpenseService expenseService;
    private final Scanner scanner;

    @Autowired
    public Main(ExpenseService expenseService) {
        this.expenseService = expenseService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = getValidIntInput("Choose an option: ", 1, 5);
                running = processMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        scanner.close();
    }

    private boolean processMenuChoice(int choice) {
        try {
            return switch (choice) {
                case 1 -> { addExpense(); yield true; }
                case 2 -> { viewExpenses(); yield true; }
                case 3 -> { updateExpense(); yield true; }
                case 4 -> { deleteExpense(); yield true; }
                case 5 -> false;
                default -> { 
                    System.out.println("Invalid option. Please try again.");
                    yield true;
                }
            };
        } catch (Exception e) {
            System.out.println("Error processing your choice: " + e.getMessage());
            return true;
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Expense Tracker ===");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Exit");
    }

    private void addExpense() {
        try {
            String description = getValidStringInput("Enter description: ");
            BigDecimal amount = getValidAmount("Enter amount: ");
            ExpenseCategory category = getValidCategory();

            Expense expense = new Expense(description, amount, LocalDate.now(), category);
            expenseService.saveExpense(expense);
            System.out.println("Expense added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    private void viewExpenses() {
        try {
            List<Expense> expenses = expenseService.getAllExpenses();
            if (expenses.isEmpty()) {
                System.out.println("No expenses found.");
                return;
            }
            displayExpenses(expenses);
        } catch (Exception e) {
            System.out.println("Error viewing expenses: " + e.getMessage());
        }
    }

    private void updateExpense() {
        try {
            viewExpenses();
            Long id = getValidLongInput("Enter expense ID to update: ");
            
            Expense existingExpense = expenseService.getExpenseById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));

            System.out.println("Current expense details:");
            displayExpense(existingExpense);

            String description = getValidStringInput("Enter new description (press Enter to keep current): ");
            if (!description.isEmpty()) {
                existingExpense.setDescription(description);
            }

            String amountStr = getValidStringInput("Enter new amount (press Enter to keep current): ");
            if (!amountStr.isEmpty()) {
                BigDecimal amount = validateAmount(new BigDecimal(amountStr));
                existingExpense.setAmount(amount);
            }

            String categoryStr = getValidStringInput("Enter new category (press Enter to keep current): ");
            if (!categoryStr.isEmpty()) {
                existingExpense.setCategory(ExpenseCategory.valueOf(categoryStr.toUpperCase()));
            }

            expenseService.saveExpense(existingExpense);
            System.out.println("Expense updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating expense: " + e.getMessage());
        }
    }

    private void deleteExpense() {
        try {
            viewExpenses();
            Long id = getValidLongInput("Enter expense ID to delete: ");
            
            if (!expenseService.getExpenseById(id).isPresent()) {
                System.out.println("Expense not found with ID: " + id);
                return;
            }

            String confirm = getValidStringInput("Are you sure you want to delete this expense? (y/n): ");
            if (confirm.toLowerCase().startsWith("y")) {
                expenseService.deleteExpense(id);
                System.out.println("Expense deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting expense: " + e.getMessage());
        }
    }

    // Helper methods for input validation and display
    private int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d%n", min, max);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // consume invalid input
            }
        }
    }

    private Long getValidLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // consume invalid input
            }
        }
    }

    private String getValidStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private BigDecimal getValidAmount(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                BigDecimal amount = new BigDecimal(scanner.nextLine());
                return validateAmount(amount);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BigDecimal validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        return amount;
    }

    private ExpenseCategory getValidCategory() {
        while (true) {
            try {
                System.out.println("Available categories:");
                for (ExpenseCategory category : ExpenseCategory.values()) {
                    System.out.println("- " + category);
                }
                String input = getValidStringInput("Enter category: ");
                return ExpenseCategory.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please try again.");
            }
        }
    }

    private void displayExpenses(List<Expense> expenses) {
        System.out.println("\nExpense List:");
        System.out.println("----------------------------------------");
        expenses.forEach(this::displayExpense);
        System.out.println("----------------------------------------");
    }

    private void displayExpense(Expense expense) {
        System.out.printf("ID: %d%nDescription: %s%nAmount: $%.2f%nDate: %s%nCategory: %s%n%n",
            expense.getId(),
            expense.getDescription(),
            expense.getAmount(),
            expense.getDate(),
            expense.getCategory());
    }
}
