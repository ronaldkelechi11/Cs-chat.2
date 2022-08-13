package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
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

public class Browser extends AppCompatActivity{
    EditText browserSearch;
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        webView = findViewById(R.id.browserWebview);
        browserSearch = findViewById(R.id.browser_search);
        progressBar = findViewById(R.id.progressBarWebView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSaveFormData(true);

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://ronaldkelechi11.github.io/CS-chat-Browser-Homepage/");

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
            finish();
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

}