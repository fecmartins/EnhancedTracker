package com.martins.ExpenseTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    public static List<Expense> importFromCSV(String filePath) {
        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line (if present)

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";"); // Use semicolon as delimiter
                if (data.length == 4) {
                    try {
                        LocalDate date = LocalDate.parse(data[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        // Adjust date format to "dd/MM/yyyy"
                        String description = data[1];
                        double amount = Double.parseDouble(data[2].replace(",", "."));
                        // Replace comma with dot for decimal separator
                        String category = data[3];

                        expenses.add(new Expense(description, amount, date, category));
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line + ". Skipping.");
                    }
                } else {
                    System.err.println("Invalid line format: " + line + ". Skipping.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return expenses;
    }
}