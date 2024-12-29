package com.martins.ExpenseTracker;

import java.time.LocalDate;

public class Expense {

    private String description;
    private double amount;
    private LocalDate date; // Added date field

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now(); // Set default date to current date

    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: " + amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
