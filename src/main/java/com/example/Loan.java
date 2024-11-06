package com.example;

import java.util.Date;

public class Loan extends FinancialProduct {
    private String customerName;
    private double loanAmount;
    private int interestRate;
    private Date loanStartDate;
    private int termInYears;

    public Loan(String productId, String customerName, double loanAmount, int interestRate, Date loanStartDate, int termInYears) {
        super(productId);
        this.customerName = customerName;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanStartDate = loanStartDate;
        this.termInYears = termInYears;
    }

    // Getter and Setter methods
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
        return (double) interestRate / 12 / 100;
    }

    public double calculateMonthlyPayment() {
        if (termInYears <= 0) {
            throw new IllegalArgumentException("Loan term must be a positive value.");
        }
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be a positive value.");
        }

        double monthlyInterestRate = calculateMonthlyInterestRate();
        int numberOfPayments = termInYears * 12;

        if (monthlyInterestRate == 0) {
            return loanAmount / numberOfPayments;
        }

        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    }

    public double calculateTotalRepayment() {
        return calculateMonthlyPayment() * termInYears * 12;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "productId=" + getProductId() +
                ", customerName='" + customerName + '\'' +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", loanStartDate=" + loanStartDate +
                ", termInYears=" + termInYears +
                '}';
    }
}
