package com.martins.ExpenseTracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    private static long lastId = 0; // Static variable to keep track of the last assigned ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(nullable = false, precision = 10, scale = 2)
    private double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseCategory category;

    // Getters and Setters (using Lombok or manually)
    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public ExpenseCategory getCategory() {
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

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Amount: " + amount + ", Date: " + date + ", Category: " + category;
    }

    public LocalDate getDate() {
        return date;
    }


    // Constructor
    public Expense(String description, double amount, LocalDate date, ExpenseCategory category) {
        this.id = ++lastId; // Increment lastId and assign it to the current object's ID
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;

        // ...
    }

    public Expense() {
        // Default constructor for JPA
    }

}
