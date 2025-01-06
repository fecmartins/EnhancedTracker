package com.martins.ExpenseTracker;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {
    
    public static List<Expense> importExpenses(MultipartFile file) throws Exception {
        List<Expense> expenses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Skip header line
            String line = reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    Expense expense = new Expense(
                        data[0].trim(), // description
                        new BigDecimal(data[1].trim()), // amount
                        LocalDate.parse(data[2].trim(), formatter), // date
                        ExpenseCategory.valueOf(data[3].trim().toUpperCase()) // category
                    );
                    expenses.add(expense);
                }
            }
        }
        return expenses;
    }
}