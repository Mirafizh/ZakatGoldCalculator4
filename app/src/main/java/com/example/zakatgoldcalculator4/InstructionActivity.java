package com.example.zakatgoldcalculator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        // 1. Set up your toolbar using the ID from activity_instruction.xml (toolbar2)
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // 2. Enforce the universal app title across the screen
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gold Zakat Apps");
        }

        // 3. Set up the Skip Button to redirect to MainActivity
        Button btnSkip = findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(InstructionActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // 4. Inflate your universal 'menu.xml' file into the Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Loads res/menu/menu.xml
        return true;
    }

    // 5. Handle clicks using your menu item IDs (itemShare and item_about)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.itemShare) {
            // Trigger a native Android system share sheet
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Gold Zakat Apps");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this useful Gold Zakat Calculator app!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;

        }  else if (itemId == R.id.item_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}