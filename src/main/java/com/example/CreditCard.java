package com.example;

import java.util.Date;

public class CreditCard {
    private String cardNumber;
    private String cardHolder;
    private Date expirationDate;
    private String cvv;

    // Yapıcı metod
    public CreditCard(String cardNumber, String cardHolder, Date expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    // Getter ve Setter metodları
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


    // Son kullanma tarihinin geçerliliğini kontrol eden metod
    public boolean isExpirationDateValid() {
        // Eğer expirationDate null ise, geçersiz olarak döner
        if (expirationDate == null) {
            return false;
        }
        
        Date currentDate = new Date();
        return expirationDate.after(currentDate);
    }

    // CVV kontrol metodu
    public boolean isCvvValid(String inputCvv) {
        return this.cvv.equals(inputCvv);
    }

    // Kart numarasının geçerli olup olmadığını kontrol eden metot
    public boolean isCardNumberValid() {
        return cardNumber != null && cardNumber.matches("\\d{4} \\d{4} \\d{4} \\d{4}");
    }

    // Kartın tipini döndüren metot (Visa, MasterCard vb.)
    public String getCardType() {
        if (cardNumber.startsWith("4")) {
            return "Visa";
        } else if (cardNumber.startsWith("5")) {
            return "MasterCard";
        } else {
            return "Unknown";
        }
    }

    // Kart bilgilerini döndüren metod
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
