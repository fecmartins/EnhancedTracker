package com.martins.ExpenseTracker;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
            System.out.println("1. Add an expense");
            System.out.println("2. Update an expense");
            System.out.println("3. Delete an expense");
            System.out.println("4. View all expenses");
            System.out.println("5. View summary of expenses");
            System.out.println("6. View summary of expenses for a specific month");
            System.out.println("7. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    // Add an expense
                    addExpense(scanner, expenses);
                    break;
                case 2:
                    // Update an expense
                    updateExpense(scanner, expenses);
                    break;
                case 3:
                    // Delete an expense
                    deleteExpense(scanner, expenses);
                    break;
                case 4:
                    // View all expenses
                    viewAllExpenses(expenses);
                    break;
                case 5:
                    // View summary of expenses
                    viewSummary(expenses);
                    break;
                case 6:
                    // View summary of expenses for a specific month
                    viewSummaryForMonth(scanner, expenses);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addExpense(Scanner scanner, ArrayList<Expense> expenses) {
        System.out.println("Enter expense description: ");
        String description = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        expenses.add(new Expense(description, amount));
        System.out.println("Expense added successfully!");
    }

    private static void updateExpense(Scanner scanner, ArrayList<Expense> expenses) {
        System.out.println("Enter the index of the expense to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < expenses.size()) {
            System.out.println("Enter new description: ");
            String description = scanner.nextLine();
            System.out.println("Enter new amount: ");
            double amount = scanner.nextDouble();
            expenses.get(index).setDescription(description);
            expenses.get(index).setAmount(amount);
            System.out.println("Expense updated successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }

    }

    private static void deleteExpense(Scanner scanner, ArrayList<Expense> expenses) {
        System.out.println("Enter the index of the expense to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void viewAllExpenses(ArrayList<Expense> expenses) {
        System.out.println("\nAll expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void viewSummary(ArrayList<Expense> expenses) {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total expenses: " + total);
    }

    private static void viewSummaryForMonth(Scanner scanner, ArrayList<Expense> expenses) {
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
}
