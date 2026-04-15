import java.util.Scanner;

// Interface for billable entities
interface Billable {
    double calculateBill();
    void generateReceipt();
}

// Concrete class implementing Billable
class UtilityBill implements Billable {
    private String customerName;
    private double unitsConsumed;
    private double totalAmount;

    public UtilityBill(String customerName, double unitsConsumed) {
        this.customerName = customerName;
        this.unitsConsumed = unitsConsumed;
        this.totalAmount = 0.0;
    }

    @Override
    public double calculateBill() {
        double amount = 0;
        double remainingUnits = unitsConsumed;

        // Slab 1: 0 - 100 units -> $1.50 per unit
        if (remainingUnits > 0) {
            double unitsInSlab = Math.min(100, remainingUnits);
            amount += unitsInSlab * 1.50;
            remainingUnits -= unitsInSlab;
        }

        // Slab 2: 101 - 300 units -> $2.50 per unit
        if (remainingUnits > 0) {
            double unitsInSlab = Math.min(200, remainingUnits);
            amount += unitsInSlab * 2.50;
            remainingUnits -= unitsInSlab;
        }

        // Slab 3: Above 300 units -> $4.00 per unit
        if (remainingUnits > 0) {
            amount += remainingUnits * 4.00;
        }

        this.totalAmount = amount;
        return amount;
    }

    @Override
    public void generateReceipt() {
        System.out.println("\n=================================");
        System.out.println("        SMARTPAY RECEIPT");
        System.out.println("=================================");
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Units Consumed : " + String.format("%.2f", unitsConsumed));
        System.out.println("---------------------------------");
        System.out.println("TOTAL AMOUNT   : $" + String.format("%.2f", totalAmount));
        System.out.println("=================================\n");
    }
}

public class SmartPay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to SmartPay Utility Billing");

        String name = "";
        double units = -1;

        while (name.isEmpty()) {
            System.out.print("Enter Customer Name: ");
            name = scanner.nextLine().trim();
        }

        while (units < 0) {
            System.out.print("Enter Units Consumed: ");
            if (scanner.hasNextDouble()) {
                units = scanner.nextDouble();
                if (units < 0) {
                    System.out.println("Error: Units cannot be negative. Try again.");
                }
            } else {
                System.out.println("Error: Please enter a valid numerical value.");
                scanner.next(); // Clear invalid input
            }
        }

        UtilityBill bill = new UtilityBill(name, units);
        bill.calculateBill();
        bill.generateReceipt();

        scanner.close();
    }
}
