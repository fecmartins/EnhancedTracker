package com.martins.ExpenseTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static double monthlyBudget = 0.0;


    public static void main(String[] args) {


/*      Expense Tracker

        Requirements

        Application should run from the command line and should have the following features:

        - Users can add an expense with a description and amount.
        - Users can update an expense.
        - Users can delete an expense.
        - Users can view all expenses.
        - Users can view a summary of all expenses.
        - Users can view a summary of expenses for a specific month (of current year).

        Here are some additional features that you can add to the application:

        - Add expense categories and allow users to filter expenses by category.
        - Allow users to set a budget for each month and show a warning when the user exceeds the budget.
        - Allow users to export expenses to a CSV file.

*/

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Update Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. View Expenses by Category");
            System.out.println("6. View Expense Summary");
            System.out.println("7. View Monthly Summary");
            System.out.println("8. Set Monthly Budget");
            System.out.println("9. Export to CSV");
            System.out.println("10. Import from CSV");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        addExpense(scanner, expenses);
                        break;
                    case 2:
                        updateExpense(scanner, expenses);
                        break;
                    case 3:
                        deleteExpense(scanner, expenses);
                        break;
                    case 4:
                        viewAllExpenses(expenses);
                        break;
                    case 5:
                        viewSummaryForCategory(scanner, expenses);
                        break;
                    case 6:
                        viewExpenseSummary(expenses);
                        break;
                    case 7:
                        viewMonthlySummary(scanner, expenses);
                        break;
                    case 8:
                        setMonthlyBudget(scanner);
                        break;
                    case 9:
                        exportToCSV(expenses);
                        break;
                    case 10:
                        importFromCSV(expenses);
                        break;
                    case 11:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private static void addExpense(Scanner scanner, ArrayList<Expense> expenses) {
       try {
        System.out.println("Enter expense description: ");
        String description = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        scanner.nextLine(); // Consume the newline character

        // Add date input
        System.out.println("Expense date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Using current date instead.");
            date = LocalDate.now();
        }

        // Add category
        System.out.println("Enter expense category: ");
        String category = scanner.nextLine();

        expenses.add(new Expense(description, amount, date, category));

        // Check if budget is exceeded
        if (calculateTotalExpenses(expenses) > monthlyBudget && monthlyBudget > 0) {
            System.out.println("Warning: You have exceeded your monthly budget!");
        }

        System.out.println("Expense added successfully!");
         } catch (IllegalArgumentException e) {
              System.err.println("Error adding expense: " + e.getMessage());
    }
    }

    private static void updateExpense(Scanner scanner, ArrayList<Expense> expenses) {
    try {
        System.out.println("Enter the index of the expense to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < expenses.size()) {
            System.out.println("Enter new description: ");
            String description = scanner.nextLine();
            System.out.println("Enter new amount: ");
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            scanner.nextLine(); // Consume the newline character

            expenses.get(index).setDescription(description);
            expenses.get(index).setAmount(amount);
            System.out.println("Expense updated successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }

    } catch (InputMismatchException e) {
        System.err.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // Consume the newline character
    } catch (IllegalArgumentException e) {
        System.err.println("Error updating expense: " + e.getMessage());
    }
    }

    private static void deleteExpense(Scanner scanner, ArrayList<Expense> expenses) {
    try {
        System.out.println("Enter the index of the expense to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    } catch (InputMismatchException e) {
        System.err.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // Consume the newline character
    }
    }

    private static void viewAllExpenses(ArrayList<Expense> expenses) {
        System.out.println("\nAll expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void viewExpenseSummary(ArrayList<Expense> expenses) {
        double total = calculateTotalExpenses(expenses);
        System.out.println("\nTotal Expenses: $" + total);

        if (total > monthlyBudget && monthlyBudget > 0) {
            System.out.println("Warning: You have exceeded your monthly budget!");
        }
    }

    private static void viewMonthlySummary(Scanner scanner, ArrayList<Expense> expenses) {
        System.out.print("Enter the month (e.g., January, February): ");
        String monthStr = scanner.nextLine();
        Month month = Month.valueOf(monthStr.toUpperCase());
        int currentYear = Year.now().getValue();

        double monthlyTotal = 0;
        for (Expense expense : expenses) {
            if (expense.getDate().getMonth() == month && expense.getDate().getYear() == currentYear) {
                monthlyTotal += expense.getAmount();
            }
        }

        System.out.println("\nTotal Expenses for " + monthStr + ": $" + monthlyTotal);
    }

    private static void viewSummaryForCategory(Scanner scanner, ArrayList<Expense> expenses) {
        System.out.println("Enter category to filter by: ");
        String category = scanner.nextLine();

        System.out.println("\nExpenses for category: " + category);
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);;
            }
        }
    }

    private static void setMonthlyBudget(Scanner scanner) {
        System.out.print("Enter monthly budget: ");
        double newBudget = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        monthlyBudget = newBudget;
        System.out.println("Monthly budget set to $" + newBudget);
    }

    private static void exportToCSV(ArrayList<Expense> expenses) {
        try (FileWriter writer = new FileWriter("expenses.csv")) {
            writer.append("Date,Description,Amount,Category\n");
            for (Expense expense : expenses) {
                writer.append(expense.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .append(",")
                        .append(expense.getDescription())
                        .append(",")
                        .append(Double.toString(expense.getAmount()))
                        .append(",")
                        .append(expense.getCategory())
                        .append("\n");
            }
            System.out.println("Expenses exported to expenses.csv successfully!");
        } catch (IOException e) {
            System.err.println("Error exporting expenses to CSV: " + e.getMessage());
        }
    }

    private static void importFromCSV(ArrayList<Expense> expenses) {
        try {
            List<Expense> importedExpenses = CSVImporter.importFromCSV("expenses.csv");
            expenses.addAll(importedExpenses);
            System.out.println("Expenses imported successfully!");
        } catch (Exception e) {
            System.err.println("Error importing expenses: " + e.getMessage());
        }
    }

    // Helper method to calculate total expenses
    private static double calculateTotalExpenses(ArrayList<Expense> expenses) {
        return ExpenseUtils.calculateTotalExpenses(expenses);
    }
}
