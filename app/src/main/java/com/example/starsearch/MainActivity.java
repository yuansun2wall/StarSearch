package com.example.starsearch;

import android.app.Activity;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /** The player's name entered by user. */
    private static String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar searchProgressBar = findViewById(R.id.progressBar);
        searchProgressBar.setProgress(99);

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            EditText inputText = findViewById(R.id.nameEditText);
            playerName = inputText.getText().toString();

            String formatName = getFormatName(playerName);
            Log.d("editText", formatName);

            // Attempt to launch a new activity to demonstrate the player's information
            Intent startPlayerInfoIntent = new Intent(getApplicationContext(),PlayerInfoActivity.class);
            startPlayerInfoIntent.putExtra("formatName", formatName);
            startPlayerInfoIntent.putExtra("playerName", playerName);
            startActivity(startPlayerInfoIntent);
        });

        Button cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(v -> {
        });
    }

    private String getFormatName(String inputName) {
        String[] names = inputName.split(" ");
        String firstName = names[0];
        String lastName = names[1];
        String initialTwo = firstName.substring(0, 2) + String.valueOf(0) +String.valueOf(1);
        if (lastName.length() > 5) {
            lastName = lastName.substring(0, 5);
        }
        return String.valueOf(lastName.charAt(0)) + "/" + lastName + initialTwo + ".html/";
    }
}
