package com.example.starsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class VideoActivity extends AppCompatActivity {

    WebView videoWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoWebView = findViewById(R.id.videoWebView);
        videoWebView.setWebViewClient(new WebViewClient());
        videoWebView.loadUrl("https://www.youtube.com/results?search_query=" + PlayerInfoActivity.playerName);

        WebSettings videoWebSettings = videoWebView.getSettings();
        videoWebSettings.setJavaScriptEnabled(true);

        Button searchButton = findViewById(R.id.infoButton);
        searchButton.setOnClickListener(v -> {

            // Attempt to launch a new activity to demonstrate the player's information
            Intent startPlayerInfoIntent = new Intent(getApplicationContext(),PlayerInfoActivity.class);
            startActivity(startPlayerInfoIntent);
        });
    }

    /** Modify the go back button so the view can shift back instead of quiting the activity. */
    @Override
    public void onBackPressed() {
        if (videoWebView.canGoBack()) {
            videoWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
