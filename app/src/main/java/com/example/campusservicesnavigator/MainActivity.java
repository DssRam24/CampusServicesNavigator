package com.example.campusservicesnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnWebsite, btnMaps, btnDialer, btnShare, btnAboutCampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWebsite = findViewById(R.id.btnWebsite);
        btnMaps = findViewById(R.id.btnMaps);
        btnDialer = findViewById(R.id.btnDialer);
        btnShare = findViewById(R.id.btnShare);
        btnAboutCampus = findViewById(R.id.btnAboutCampus);

        // implicit intent: ACTION_VIEW with a web address
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vrsiddhartha.ac.in"));
                startSafely(intent);
            }
        });

        // implicit intent: ACTION_VIEW with a geo location
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:16.4815,80.6938?q=Siddhartha Academy of Higher Education"));
                startSafely(intent);
            }
        });

        // implicit intent: ACTION_DIAL opens the dialer (no permission needed)
        btnDialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:08662582333"));
                startSafely(intent);
            }
        });

        // implicit intent: ACTION_SEND with a chooser
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Siddhartha Academy of Higher Education - https://www.vrsiddhartha.ac.in");
                startSafely(Intent.createChooser(intent, "Share campus info via"));
            }
        });

        // explicit intent: we name the activity class ourselves
        btnAboutCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutCampusActivity.class);
                startActivity(intent);
            }
        });
    }

    // if no app can handle the intent, show a message instead of crashing
    private void startSafely(Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app found to handle this action", Toast.LENGTH_SHORT).show();
        }
    }
}
