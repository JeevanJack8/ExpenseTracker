import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=========== EXPENSE TRACKER ===========");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Total by Category");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Category (Food/Travel/Bills/Shopping/Other): ");
                    String cat = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    Expense e = new Expense(amount, cat, desc, LocalDate.now());
                    ExpenseManager.addExpense(e);
                    break;

                case 2:
                    ExpenseManager.viewExpenses();
                    break;

                case 3:
                    ExpenseManager.monthlyReport();
                    break;

                case 4:
                    ExpenseManager.totalByCategory();
                    break;

                case 5:
                    System.out.println("Exiting… Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
