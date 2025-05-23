package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddExpenseActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDarkModeEnabled()) {
            setTheme(R.style.Theme_NysreynitLab2_Dark);
        } else {
            setTheme(R.style.Theme_NysreynitLab2_Light);
        }

        setContentView(R.layout.activity_add_expense);

        // Initialize bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Bottom navigation setup
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                startActivity(new Intent(AddExpenseActivity.this, MainActivity.class));
                return true;
            } else if (id == R.id.navigation_add_expense) {
                // Already here, no action needed
                return true;
            } else if (id == R.id.navigation_expense_list) {
                startActivity(new Intent(AddExpenseActivity.this, ExpenseListActivity.class));
                return true;
            }
            return false;
        });

        // Highlight the current item in bottom nav
        bottomNavigationView.setSelectedItemId(R.id.navigation_add_expense);
    }

    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}