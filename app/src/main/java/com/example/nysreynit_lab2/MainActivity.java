package com.example.nysreynit_lab2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the theme based on dark mode setting
        if (isDarkModeEnabled()) {
            setTheme(R.style.Theme_NysreynitLab2_Dark);  // Dark theme
        } else {
            setTheme(R.style.Theme_NysreynitLab2_Light);  // Light theme
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d("DebugLab", "App started successfully!");

        // Example arithmetic operation
        int num1 = 10;
        int num2 = 20;
        int result = num1 + num2;
        Log.d("DebugLab", "Sum: " + result);

        // Initialize Spinner
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Entertainment"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        Log.d("DebugLab", "Spinner initialized with " + categories.length + " categories");

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to check if Dark Mode is enabled
    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}
