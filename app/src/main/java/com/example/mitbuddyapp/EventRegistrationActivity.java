package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

public class EventRegistrationActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);

        // Initialize WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Find the CardViews for events
        CardView event1CardView = findViewById(R.id.event1);
        CardView event2CardView = findViewById(R.id.event2);
        CardView event3CardView = findViewById(R.id.event3);
        CardView event4CardView = findViewById(R.id.event4);

        // Set onClickListener for each event
        event1CardView.setOnClickListener(v -> openWebsite("https://dreamscape.ieeemanipal.com/events"));
        event2CardView.setOnClickListener(v -> openWebsite("https://atpi.eventsair.com/QuickEventWebsitePortal/41st-esa-antenna-workshop/antenna/ExtraContent/ContentPage?page=2"));
        event3CardView.setOnClickListener(v -> openWebsite("https://publichealth.jhu.edu/events/2024/workshop-version-control-using-git-and-github"));
        event4CardView.setOnClickListener(v -> openWebsite("https://docs.google.com/forms/d/e/1FAIpQLScVOLS5GaiH8LJsJmHemcfT7Q4xG9BEw9JpeSMXJjjjoWWatw/formrestricted"));
    }

    // Method to open a website in the WebView
    private void openWebsite(String url) {
        webView.setVisibility(WebView.VISIBLE);
        webView.loadUrl(url);
    }

    // Handle back button press to navigate back in WebView history
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
