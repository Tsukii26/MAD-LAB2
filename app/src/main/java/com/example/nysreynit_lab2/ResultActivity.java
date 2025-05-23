package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView titleTextView, lastExpenseTextView;
    private Button addNewExpenseButton, viewDetailExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String yourName = "Ny sreynit";

        // Find views by id
        titleTextView = findViewById(R.id.titleTextView);
        lastExpenseTextView = findViewById(R.id.lastExpenseTextView);
        addNewExpenseButton = findViewById(R.id.addNewExpenseButton);
        viewDetailExpenseButton = findViewById(R.id.viewDetailExpenseButton);

        titleTextView.setText("Manage Your Expense, " + yourName);

        final double amount = getIntent().getDoubleExtra("lastAmount", 0);
        String tempCurrency = getIntent().getStringExtra("lastCurrency");
        if (tempCurrency == null) tempCurrency = "";
        final String currency = tempCurrency;

        String tempCategory = getIntent().getStringExtra("lastCategory");
        if (tempCategory == null) tempCategory = "";
        final String category = tempCategory;

        String tempRemark = getIntent().getStringExtra("lastRemark");
        if (tempRemark == null) tempRemark = "";
        final String remark = tempRemark;

        final String createdDate = java.text.DateFormat.getDateInstance().format(new java.util.Date());

        // Set last expense text
        lastExpenseTextView.setText("My last expense was " + amount + " " + currency);

        // Add button click listeners
        addNewExpenseButton.setOnClickListener(v -> {
            // Navigate to MainActivity (Add New Expense)
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        viewDetailExpenseButton.setOnClickListener(v -> {
            // Navigate to ExpenseDetailActivity with all expense data
            Intent intent = new Intent(ResultActivity.this, ExpenseDetailActivity.class);
            intent.putExtra("lastAmount", amount);
            intent.putExtra("lastCurrency", currency);
            intent.putExtra("lastCategory", category);
            intent.putExtra("lastRemark", remark);
            intent.putExtra("createdDate", createdDate);  // Pass current date as string

            startActivity(intent);
            finish();
        });
    }
}