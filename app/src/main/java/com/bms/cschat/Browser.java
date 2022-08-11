package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Browser extends AppCompatActivity implements View.OnClickListener{
    EditText browserSearch;
    WebView webView;
    ProgressBar progressBar;
    CardView browserHomepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        webView = findViewById(R.id.browserWebview);
        browserSearch = findViewById(R.id.browser_search);
        progressBar = findViewById(R.id.progressBarWebView);
        browserHomepage = findViewById(R.id.browserHomeScreen);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSaveFormData(true);

        webView.getUrl();


        webView.setWebViewClient(new MyWebViewClient());

        browserSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(browserSearch.getWindowToken(),0);
                    webView.loadUrl(browserSearch.getText().toString());
                }

                return false;
            }
        });

    }

    //Back Pressed Button
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            Intent i = new Intent(getApplicationContext(),HomeScreen.class);
            startActivity(i);
        }
        super.onBackPressed();
    }


    // Method to load URL and check if it's a url
    void loadUrl(String url){
        boolean isUrl = Patterns.WEB_URL.matcher(url).matches();
        if(isUrl){
          webView.loadUrl(url);
        }
        else{
           webView.loadUrl("google.com/search?q="+url);
        }
    }

    //Switch Case for Redirection
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myschoolgist:
                loadUrl("www.myschoolgist.com");
                browserHomepage.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.waecdirect:
                loadUrl("www.waecdirect.com");
                browserHomepage.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.jumia:
                loadUrl("www.jumia.com");
                browserHomepage.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.manirecruits:
                loadUrl("www.manirecruits.com");
                browserHomepage.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.coralexec:
                loadUrl("www.coralexecutives.com");
                browserHomepage.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                break;
        }
    }
    // Web Client Service
    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Page Loaded", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        browserHomepage.setVisibility(View.VISIBLE);
        webView.setVisibility(View.INVISIBLE);
        super.onStart();
    }
}