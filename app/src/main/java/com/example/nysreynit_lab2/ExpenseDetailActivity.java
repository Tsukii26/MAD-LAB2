package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        amountTextView.setText("Amount: " + amount);
        currencyTextView.setText("Currency: " + currency);
        categoryTextView.setText("Category: " + category);
        remarkTextView.setText("Remark: " + remark);
        dateTextView.setText("Created Date: " + createdDate);

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

        // ✅ Bottom Navigation setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_add);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.nav_add) {
                // Already in this activity
                return true;
            } else if (itemId == R.id.nav_list) {
                startActivity(new Intent(this, ListActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}
