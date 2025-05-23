package com.example.nysreynit_lab2;

public class Expense {
    private double amount;
    private String currency;
    private String category;
    private String remark;

    public Expense(double amount, String currency, String category, String remark) {
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.remark = remark;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getRemark() {
        return remark;
    }
}