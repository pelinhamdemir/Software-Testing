package com.example;

public class Account {
    private double balance;
    private double totalDeposits;
    private double totalWithdrawals;

    // Constructor
    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
        this.totalDeposits = 0;
        this.totalWithdrawals = 0;
    }

    // Method to deposit money
    public void putMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
        this.totalDeposits += amount;
        System.out.println("Deposited: $" + amount);
    }

    // Method to withdraw money
    public void getMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        this.balance -= amount;
        this.totalWithdrawals += amount;
        System.out.println("Withdrew: $" + amount);
    }

    // Method to check balance
    public double checkBalance() {
        return balance;
    }

    // Get total deposits
    public double getTotalDeposits() {
        return totalDeposits;
    }

    // Get total withdrawals
    public double getTotalWithdrawals() {
        return totalWithdrawals;
    }

    // Display account information
    @Override
    public String toString() {
        return "Account{" +
                "balance=$" + balance +
                ", totalDeposits=$" + totalDeposits +
                ", totalWithdrawals=$" + totalWithdrawals +
                '}';
    }
}