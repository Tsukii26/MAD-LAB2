package com.example.nysreynit_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText, remarkEditText;
    private RadioGroup currencyRadioGroup;
    private Spinner categorySpinner;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDarkModeEnabled()) {
            setTheme(R.style.Theme_NysreynitLab2_Dark);
        } else {
            setTheme(R.style.Theme_NysreynitLab2_Light);
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d("DebugLab", "App started successfully!");

        // Initialize views
        amountEditText = findViewById(R.id.amountEditText);
        remarkEditText = findViewById(R.id.remarkEditText);
        currencyRadioGroup = findViewById(R.id.currencyRadioGroup);
        categorySpinner = findViewById(R.id.categorySpinner);
        submitButton = findViewById(R.id.submitButton);

        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Entertainment"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get amount value
                String amountStr = amountEditText.getText().toString().trim();
                double amount = 0;
                if (!amountStr.isEmpty()) {
                    try {
                        amount = Double.parseDouble(amountStr);
                    } catch (NumberFormatException e) {
                        amount = 0;
                    }
                }

                // Get currency value from selected RadioButton
                int selectedCurrencyId = currencyRadioGroup.getCheckedRadioButtonId();
                String currency = "";
                if (selectedCurrencyId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedCurrencyId);
                    currency = selectedRadioButton.getText().toString();
                }


                String category = categorySpinner.getSelectedItem().toString();


                String remark = remarkEditText.getText().toString().trim();

                Log.d("DebugLab", "Expense input: " + amount + " " + currency + ", category: " + category + ", remark: " + remark);

                // Prepare intent to open ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("lastAmount", amount);
                intent.putExtra("lastCurrency", currency);
                intent.putExtra("lastCategory", category);
                intent.putExtra("lastRemark", remark);

                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean isDarkModeEnabled() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}