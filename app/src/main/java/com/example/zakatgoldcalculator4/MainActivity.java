package com.example.zakatgoldcalculator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Input views
    private EditText etGoldWeight, etGoldValue;
    private RadioGroup rgGoldType;

    // Containers and Output views
    private LinearLayout resultsContainer;
    private TextView tvTotalValue, tvPayableValue, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Toolbar and universal App Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gold Zakat Apps");
        }

        // 🏠 2. Initialize and Setup Home Icon Click Event
        LinearLayout layoutBack = findViewById(R.id.layout_back);
        layoutBack.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Closes the current activity to clean up navigation stack
        });

        // 3. Initialize Input Elements
        etGoldWeight = findViewById(R.id.et_gold_weight);
        etGoldValue = findViewById(R.id.et_gold_value);
        rgGoldType = findViewById(R.id.rg_gold_type);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnReset = findViewById(R.id.btnReset);

        // 4. Initialize Output Elements & Hide Results Container initially
        resultsContainer = findViewById(R.id.results_container);
        tvTotalValue = findViewById(R.id.tv_total_value);
        tvPayableValue = findViewById(R.id.tv_payable_value);
        tvTotalZakat = findViewById(R.id.tv_total_zakat);

        // Hiding results container until calculation succeeds
        resultsContainer.setVisibility(View.GONE);

        // 5. Set Up Button Click Listeners
        btnCalculate.setOnClickListener(v -> performCalculation());
        btnReset.setOnClickListener(v -> resetCalculator());
    }

    private void performCalculation() {
        String weightStr = etGoldWeight.getText().toString().trim();
        String valueStr = etGoldValue.getText().toString().trim();

        // Warning Condition: Check if text boxes are left empty
        if (weightStr.isEmpty() || valueStr.isEmpty()) {
            showWarningDialog("Missing Information", "Please enter both the gold weight and the current market value per gram before calculating.");
            return;
        }

        // Warning Condition: Check if a gold category type was selected
        int selectedRadioId = rgGoldType.getCheckedRadioButtonId();
        if (selectedRadioId == -1) {
            showWarningDialog("Select Gold Type", "Please select whether your gold is kept or worn to apply the correct Nisab threshold value.");
            return;
        }

        // Parse clean numerical data safely
        double weight = Double.parseDouble(weightStr);
        double valuePerGram = Double.parseDouble(valueStr);
        double thresholdX = (selectedRadioId == R.id.rb_keep) ? 85.0 : 200.0;

        // Equation I: Total Value = weight * valuePerGram
        double totalValue = weight * valuePerGram;

        // Equation II: Zakat Payable Value = Max(0, (weight - X) * valuePerGram)
        double payableValue = (weight > thresholdX) ? (weight - thresholdX) * valuePerGram : 0.0;

        // Equation III: Zakat Due = 2.5% of Zakat Payable Value
        double totalZakat = 0.025 * payableValue;

        // Update Text Output Labels formatted to Malaysian Ringgit locale standard layout
        tvTotalValue.setText(String.format(Locale.getDefault(), "RM %.2f", totalValue));
        tvPayableValue.setText(String.format(Locale.getDefault(), "RM %.2f", payableValue));
        tvTotalZakat.setText(String.format(Locale.getDefault(), "RM %.2f", totalZakat));

        // Reveal the entire results display view box seamlessly onto the screen layout wrapper
        resultsContainer.setVisibility(View.VISIBLE);
    }

    private void resetCalculator() {
        // Wipe all text fields
        etGoldWeight.setText("");
        etGoldValue.setText("");
        rgGoldType.clearCheck();

        // Collapse and hide the results container panel block back out of view
        resultsContainer.setVisibility(View.GONE);
    }

    // Helper method to create clean native alert warning message boxes popups
    private void showWarningDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // 6. Shared Options Menu Structure Setup (Share / About items)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.itemShare) {
            // Trigger a native Android system share sheet
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Gold Zakat Apps");

            // Combined your message and your GitHub repository link here:
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this useful Gold Zakat Calculator app! View the source code here: https://github.com/Mirafizh/ZakatGoldCalculator4");

            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        } else if (itemId == R.id.item_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}