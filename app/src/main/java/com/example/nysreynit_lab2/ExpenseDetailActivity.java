package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {

    private TextView amountTextView, currencyTextView, categoryTextView, remarkTextView, dateTextView;
    private Button addNewExpenseButton, backToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        // Initialize views
        amountTextView = findViewById(R.id.amountTextView);
        currencyTextView = findViewById(R.id.currencyTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        remarkTextView = findViewById(R.id.remarkTextView);
        dateTextView = findViewById(R.id.dateTextView);

        addNewExpenseButton = findViewById(R.id.addNewExpenseButton);
        backToHomeButton = findViewById(R.id.backToHomeButton);


        double amount = getIntent().getDoubleExtra("lastAmount", 0);
        String currency = getIntent().getStringExtra("lastCurrency");
        if (currency == null) currency = "";

        String category = getIntent().getStringExtra("lastCategory");
        if (category == null) category = "";

        String remark = getIntent().getStringExtra("lastRemark");
        if (remark == null) remark = "";

        String createdDate = getIntent().getStringExtra("createdDate");
        if (createdDate == null) createdDate = "";

        // Set TextViews with consistent labels
        amountTextView.setText("Amount: " + amount);
        currencyTextView.setText("Currency: " + currency);
        categoryTextView.setText("Category: " + category);
        remarkTextView.setText("Remark: " + remark);
        dateTextView.setText("Created Date: " + createdDate);

        // Button click listeners
        addNewExpenseButton.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseDetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        backToHomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseDetailActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        });
    }
}