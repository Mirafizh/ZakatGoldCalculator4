package com.example.zakatgoldcalculator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        // 1. Initialize custom toolbar header spacing structure
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gold Zakat Apps");
        }

        // 2. Map click event to the new high-visibility home layout navigation container row
        LinearLayout layoutBack = findViewById(R.id.layout_back);
        layoutBack.setOnClickListener(v -> {
            // Securely redirects back to your main application landing hub
            Intent intent = new Intent(InstructionActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        CheckBox cbUnderstand = findViewById(R.id.cb_understand);
        Button btnNext = findViewById(R.id.btnSkip);

        btnNext.setOnClickListener(v -> {
            if (cbUnderstand.isChecked()) {
                Intent intent = new Intent(InstructionActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                new AlertDialog.Builder(InstructionActivity.this)
                        .setTitle("Attention Required")
                        .setMessage("Please review the instructions completely and tick the checkbox at the bottom to confirm you understand before proceeding.")
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.itemShare) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Gold Zakat Apps");
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