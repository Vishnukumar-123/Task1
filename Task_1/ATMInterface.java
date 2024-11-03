import java.util.Scanner;

class ATM {
    private double balance;
    private final double dailyWithdrawalLimit;
    private double dailyWithdrawalTotal;

    public ATM(double initialBalance, double dailyLimit) {
        this.balance = initialBalance;
        this.dailyWithdrawalLimit = dailyLimit;
        this.dailyWithdrawalTotal = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance && dailyWithdrawalTotal + amount <= dailyWithdrawalLimit) {
            balance -= amount;
            dailyWithdrawalTotal += amount;
            System.out.println("Successfully withdrawn: " + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (dailyWithdrawalTotal + amount > dailyWithdrawalLimit) {
            System.out.println("Daily withdrawal limit exceeded.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
        return false;
    }

    public void resetDailyWithdrawal() {
        dailyWithdrawalTotal = 0.0;
    }
}

public class ATMInterface{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.0, 500.0);  // Initialize ATM with balance of 1000 and daily limit of 500

        while (true) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Current Balance: " + atm.getBalance());
                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                }
                case 4 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
