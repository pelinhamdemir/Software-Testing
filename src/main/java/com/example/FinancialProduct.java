package com.example;

public abstract class FinancialProduct {
    private String productId;

    public FinancialProduct(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "FinancialProduct{" +
                "productId='" + productId + '\'' +
                '}';
    }
}
