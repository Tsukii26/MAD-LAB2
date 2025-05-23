package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private List<Expense> expenseList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDarkModeEnabled()) {
            setTheme(R.style.Theme_NysreynitLab2_Dark);
        } else {
            setTheme(R.style.Theme_NysreynitLab2_Light);
        }

        setContentView(R.layout.activity_expense_list);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewExpenses);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Temporary sample data for demonstration
        expenseList = new ArrayList<>();
        expenseList.add(new Expense(12.50, "USD", "Food", "Lunch at cafe"));
        expenseList.add(new Expense(5.00, "KHR", "Transport", "Bus fare"));
        expenseList.add(new Expense(30.00, "USD", "Shopping", "Clothes"));
        expenseList.add(new Expense(60.00, "KHR", "Bills", "Electricity bill"));
        expenseList.add(new Expense(15.00, "USD", "Entertainment", "Movie ticket"));

        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapter);

        // Bottom navigation setup
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                startActivity(new Intent(ExpenseListActivity.this, MainActivity.class));
                return true;
            } else if (id == R.id.navigation_add_expense) {
                startActivity(new Intent(ExpenseListActivity.this, AddExpenseActivity.class));
                return true;
            } else if (id == R.id.navigation_expense_list) {
                // Already here, no action needed
                return true;
            }
            return false;
        });

        // Highlight the current item in bottom nav
        bottomNavigationView.setSelectedItemId(R.id.navigation_expense_list);
    }

    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}