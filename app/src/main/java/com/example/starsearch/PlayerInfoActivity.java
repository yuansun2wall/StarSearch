package com.example.starsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class PlayerInfoActivity extends AppCompatActivity {

    private static final String PLAYER_INFO_WEB_ADDRESS = "https://www.basketball-reference.com/players/";

    /** The player's name entered by user. */
    private static String formatName;

    protected static String playerName;

    /** The web view to demonstrate player's info. */
    WebView infoWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        if (getIntent().hasExtra("formatName")) {
            infoWebView = findViewById(R.id.playerInfoWebView);
            formatName = getIntent().getExtras().getString("formatName").toLowerCase();
            playerName = getIntent().getExtras().getString("playerName");

            infoWebView.setWebViewClient(new WebViewClient());
            Log.d("format:", formatName);
            infoWebView.loadUrl(PLAYER_INFO_WEB_ADDRESS + formatName);
        }

        WebSettings infoWebSettings = infoWebView.getSettings();
        infoWebSettings.setJavaScriptEnabled(true);

        Button videoButton = findViewById(R.id.videoButton);
        videoButton.setOnClickListener(v -> {
            Intent videoIntent = new Intent(getApplicationContext(),VideoActivity.class);
            videoIntent.putExtra("playerName", playerName);
            startActivity(videoIntent);
        });
    }

    /** Modify the go back button so the view can shift back instead of quiting the activity. */
    @Override
    public void onBackPressed() {
        if (infoWebView.canGoBack()) {
            infoWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
