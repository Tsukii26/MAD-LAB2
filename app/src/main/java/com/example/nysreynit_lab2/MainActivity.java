package com.example.nysreynit_lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText, remarkEditText;
    private RadioGroup currencyRadioGroup;
    private Spinner categorySpinner;
    private Button submitButton;
    private BottomNavigationView bottomNavigationView;

    private Button loginButton, signupButton, signOutButton;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDarkModeEnabled()) {
            setTheme(R.style.Theme_NysreynitLab2_Dark);
        } else {
            setTheme(R.style.Theme_NysreynitLab2_Light);
        }

        setContentView(R.layout.activity_main);

        Log.d("DebugLab", "App started successfully!");

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        amountEditText = findViewById(R.id.amountEditText);
        remarkEditText = findViewById(R.id.remarkEditText);
        currencyRadioGroup = findViewById(R.id.currencyRadioGroup);
        categorySpinner = findViewById(R.id.categorySpinner);
        submitButton = findViewById(R.id.submitButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView); // Fixed ID
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        signOutButton = findViewById(R.id.signOutButton);

        // Check login state
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_LOGGED_IN, false);
        updateUIForLoginState(isLoggedIn);

        // Login button click
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Signup button click
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Sign out button click
        signOutButton.setOnClickListener(v -> {
            sharedPreferences.edit().putBoolean(KEY_LOGGED_IN, false).apply();
            updateUIForLoginState(false);
        });

        // Spinner setup
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Entertainment"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Submit button listener
        submitButton.setOnClickListener(v -> {
            String amountStr = amountEditText.getText().toString().trim();
            double amount = 0;
            if (!amountStr.isEmpty()) {
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    amount = 0;
                }
            }

            int selectedCurrencyId = currencyRadioGroup.getCheckedRadioButtonId();
            String currency = "";
            if (selectedCurrencyId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedCurrencyId);
                currency = selectedRadioButton.getText().toString();
            }

            String category = categorySpinner.getSelectedItem().toString();
            String remark = remarkEditText.getText().toString().trim();

            Log.d("DebugLab", "Expense input: " + amount + " " + currency + ", category: " + category + ", remark: " + remark);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("lastAmount", amount);
            intent.putExtra("lastCurrency", currency);
            intent.putExtra("lastCategory", category);
            intent.putExtra("lastRemark", remark);

            startActivity(intent);
            finish();
        });

        // Bottom navigation handling
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                return true;
            } else if (id == R.id.navigation_add_expense) {
                startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
                return true;
            } else if (id == R.id.navigation_expense_list) {
                startActivity(new Intent(MainActivity.this, ExpenseListActivity.class));
                return true;
            }
            return false;
        });

        // Apply padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateUIForLoginState(boolean isLoggedIn) {
        if (isLoggedIn) {
            loginButton.setVisibility(View.GONE);
            signupButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            loginButton.setVisibility(View.VISIBLE);
            signupButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }

    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}
