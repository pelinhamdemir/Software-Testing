package com.example;

import java.util.Date;

public class CreditCard {
    private String cardNumber;
    private String cardHolder;
    private Date expirationDate;
    private String cvv;

    // Constructor
    public CreditCard(String cardNumber, String cardHolder, Date expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    // Getter and Setter methods
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    // Method to check if the expiration date is valid
    public boolean isExpirationDateValid() {
        // If expirationDate is null, return invalid
        if (expirationDate == null) {
            return false;
        }

        Date currentDate = new Date();
        return expirationDate.after(currentDate);
    }

    // CVV validation method
    public boolean isCvvValid(String inputCvv) {
        return this.cvv.equals(inputCvv);
    }

    // Method to check if the card number is valid
    public boolean isCardNumberValid() {
        return cardNumber != null && cardNumber.matches("\\d{4} \\d{4} \\d{4} \\d{4}");
    }

    // Method to return the card type (Visa, MasterCard, etc.)
    public String getCardType() {
        if (cardNumber.startsWith("4")) {
            return "Visa";
        } else if (cardNumber.startsWith("5")) {
            return "MasterCard";
        } else {
            return "Unknown";
        }
    }

    // Method to return card information
    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}