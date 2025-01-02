package com.martins.ExpenseTracker;

import java.time.LocalDate;

public class Expense {

    private static int lastId = 0; // Static variable to keep track of the last assigned ID
    private int id; // ID field for each expense
    private String description;
    private double amount;
    private LocalDate date; // Added date field
    private String category; // Added category field

    public Expense(String description, double amount, LocalDate date, String category) {
        this.id = ++lastId; // Increment lastId and assign it to the current object's ID
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;

    }

    public int getId() {
        return id;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Amount: " + amount + ", Date: " + date + ", Category: " + category;
    }

    public LocalDate getDate() {
        return date;
    }

}
