package com.example.zakatgoldcalculator4; // <-- Replace with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This links the Java code to your activity_home.xml layout
        setContentView(R.layout.activity_home);

        // Initialize the button using the ID from your XML (android:id="@+id/btnStart")
        Button btnStart = findViewById(R.id.btnStart);

        // Set an onClickListener to handle the button click
        btnStart.setOnClickListener(v -> {
            // Intent is used to navigate from HomeActivity to InstructionActivity
            Intent intent = new Intent(HomeActivity.this, InstructionActivity.class);
            startActivity(intent);
        });
    }
}