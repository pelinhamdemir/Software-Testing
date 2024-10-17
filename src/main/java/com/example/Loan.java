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

    // Getter ve Setter metodları
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


    public double calculateMonthlyPayment() {
        // Negatif kredi süresi ya da negatif kredi tutarı kontrolü
        if (termInYears <= 0) {
            throw new IllegalArgumentException("Kredi süresi pozitif bir değer olmalıdır.");
        }

        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Kredi tutarı pozitif bir değer olmalıdır.");
        }

        double monthlyInterestRate = (double) interestRate / 12 / 100; // Aylık faiz oranı
        int numberOfPayments = termInYears * 12; // Toplam ödeme sayısı

        // Eğer faiz oranı 0 ise direkt loanAmount / numberOfPayments dönebiliriz.
        if (monthlyInterestRate == 0) {
            return loanAmount / numberOfPayments;
        }

        // Aksi takdirde hesaplama formülü
        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    }




    public double calculateTotalRepayment() {
        return calculateMonthlyPayment() * termInYears * 12;
    }
}