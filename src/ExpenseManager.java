import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {

    private static final String FILE = "expenses.txt";

    public static void addExpense(Expense e) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(e.toString());
            bw.newLine();
            System.out.println("Expense added successfully!");
        } catch (Exception ex) {
            System.out.println("Error adding expense.");
        }
    }

    public static List<Expense> loadExpenses() {
        List<Expense> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                double amount = Double.parseDouble(arr[0]);
                String category = arr[1];
                String description = arr[2];
                LocalDate date = LocalDate.parse(arr[3]);

                list.add(new Expense(amount, category, description, date));
            }
        } catch (Exception ignored) {
        }

        return list;
    }

    public static void viewExpenses() {
        List<Expense> list = loadExpenses();

        if (list.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n====== ALL EXPENSES ======");
        for (Expense e : list) {
            System.out.println(e.getDate() + " | " +
                               e.getCategory() + " | " +
                               e.getAmount() + " | " +
                               e.getDescription());
        }
    }

    public static void monthlyReport() {
        List<Expense> list = loadExpenses();
        if (list.isEmpty()) {
            System.out.println("No data to calculate.");
            return;
        }

        double total = 0;
        LocalDate now = LocalDate.now();

        for (Expense e : list) {
            if (e.getDate().getMonth() == now.getMonth() &&
                e.getDate().getYear() == now.getYear())
            {
                total += e.getAmount();
            }
        }

        System.out.println("\nMONTHLY EXPENSE REPORT (" + now.getMonth() + ")");
        System.out.println("Total: Rs. " + total);
    }

    public static void totalByCategory() {
        List<Expense> list = loadExpenses();
        if (list.isEmpty()) {
            System.out.println("No data to process.");
            return;
        }

        Map<String, Double> map = new HashMap<>();

        for (Expense e : list) {
            map.put(e.getCategory(), map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\n===== EXPENSE BY CATEGORY =====");
        for (String cat : map.keySet()) {
            System.out.println(cat + ": Rs. " + map.get(cat));
        }
    }
}
