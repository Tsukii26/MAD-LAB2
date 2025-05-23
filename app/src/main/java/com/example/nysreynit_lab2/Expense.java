package com.example.nysreynit_lab2; // ✅ Add your package name here

public class Expense {
    public double amount;
    public String currency;
    public String date;
    public String category;
    public String remark;

    public Expense(double amount, String currency, String date, String category, String remark) {
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.category = category;
        this.remark = remark;
    }
}
