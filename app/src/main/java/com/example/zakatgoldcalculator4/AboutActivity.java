package com.example.zakatgoldcalculator4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // 1. Initialize your custom Toolbar (toolbar3)
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gold Zakat Apps");
        }

        // 2. Map GitHub link redirect logic
        Button btnGithub = findViewById(R.id.btnGithub);
        btnGithub.setOnClickListener(v -> {
            // Replace with your exact public username URL profile link
            String githubUrl = "https://github.com/Mirafizh/ZakatGoldCalculator4";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(intent);
        });
    }

    // 3. Inflate standard option menu structure layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 4. Shared menu functionality implementation block
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemShare) {
            // Trigger a native Android system share sheet
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Gold Zakat Apps");

            // Combined your message and your GitHub repository link here:
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this useful Gold Zakat Calculator app! View the source code here: https://github.com/Mirafizh/ZakatGoldCalculator4");

            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        } else if (id == R.id.item_about) {
            Toast.makeText(this, "Gold Zakat Apps v1.0", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}