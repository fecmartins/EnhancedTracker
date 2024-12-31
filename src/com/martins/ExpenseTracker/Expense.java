package com.martins.ExpenseTracker;

import java.time.LocalDate;

public class Expense {

    private String description;
    private double amount;
    private LocalDate date; // Added date field
    private String category; // Added category field

    public Expense(String description, double amount, LocalDate date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;

    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: " + amount + ", Date: " + date + ", Category: " + category;
    }

    public LocalDate getDate() {
        return date;
    }
}
