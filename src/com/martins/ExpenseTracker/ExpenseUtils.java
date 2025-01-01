package com.martins.ExpenseTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseUtils {

    // Helper method to calculate total expenses
    static double calculateTotalExpenses(ArrayList<Expense> expenses) {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public static double calculateTotalExpenseByCategory(Expense[] expenses, String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByMonth(Expense[] expenses, int month) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByYear(Expense[] expenses, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByCategoryAndMonth(Expense[] expenses, String category, int month) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category) && expense.getDate().getMonthValue() == month) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByCategoryAndYear(Expense[] expenses, String category, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category) && expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByMonthAndYear(Expense[] expenses, int month, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateTotalExpenseByCategoryAndMonthAndYear(Expense[] expenses, String category, int month, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category) && expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static double calculateAverageExpense(Expense[] expenses) {
        ArrayList<Expense> expenseList = new ArrayList<>(Arrays.asList(expenses));
        return calculateTotalExpenses(expenseList) / expenses.length;
    }

    public static double calculateAverageExpenseByCategory(Expense[] expenses, String category) {
        return calculateTotalExpenseByCategory(expenses, category) / expenses.length;
    }

    public static double calculateAverageExpenseByMonth(Expense[] expenses, int month) {
        return calculateTotalExpenseByMonth(expenses, month) / expenses.length;
    }

    public static double calculateAverageExpenseByYear(Expense[] expenses, int year) {
        return calculateTotalExpenseByYear(expenses, year) / expenses.length;
    }

    public static double calculateAverageExpenseByCategoryAndMonth(Expense[] expenses, String category, int month) {
        return calculateTotalExpenseByCategoryAndMonth(expenses, category, month) / expenses.length;
    }

    public static double calculateAverageExpenseByCategoryAndYear(Expense[] expenses, String category, int year) {
        return calculateTotalExpenseByCategoryAndYear(expenses, category, year) / expenses.length;
    }
}

