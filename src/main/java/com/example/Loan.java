package com.example;

import java.util.Date;

public class Loan {
    private String loanId;
    private String customerName;
    private double loanAmount;
    private int interestRate;
    private Date loanStartDate;
    private int termInYears;

    public Loan(String loanId, String customerName, double loanAmount, int interestRate, Date loanStartDate, int termInYears) {
        this.loanId = loanId;
        this.customerName = customerName;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanStartDate = loanStartDate;
        this.termInYears = termInYears;
    }

    // Getter and Setter methods
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }
    public double calculateMonthlyInterestRate() {
        return (double) interestRate / 12 / 100; // Monthly interest rate
    }

    public double calculateMonthlyPayment() {
        // Check for negative loan term or loan amount
        if (termInYears <= 0) {
            throw new IllegalArgumentException("Loan term must be a positive value.");
        }

        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be a positive value.");
        }

        double monthlyInterestRate = calculateMonthlyInterestRate();
        int numberOfPayments = termInYears * 12; // Total number of payments

        // If interest rate is 0, we can return loanAmount / numberOfPayments directly.
        if (monthlyInterestRate == 0) {
            return loanAmount / numberOfPayments;
        }

        // Otherwise, use the calculation formula
        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    }

    public double calculateTotalRepayment() {
        return calculateMonthlyPayment() * termInYears * 12;
    }
}
