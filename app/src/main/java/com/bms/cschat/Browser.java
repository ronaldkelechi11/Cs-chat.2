package com.bms.cschat;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Browser extends AppCompatActivity{
    EditText browserSearch;
    WebView webView;
    ProgressBar progressBar;
    int Counter = 1;

    ImageView webBack,webForward,webRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        webView = findViewById(R.id.browserWebview);
        browserSearch = findViewById(R.id.browser_search);
        progressBar = findViewById(R.id.progressBarWebView);

        webBack = findViewById(R.id.goBack);
        webForward = findViewById(R.id.goForward);
        webRefresh = findViewById(R.id.tryRefresh);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSaveFormData(true);

        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        loadMyUrl("https://ronaldkelechi11.github.io/CS-Chat-Browser-Homepage/");

        if(webView.getUrl().equals("about:blank")){
            browserSearch.setText("");
            progressBar.setVisibility(View.VISIBLE);
        }
        if(!webView.canGoBack()){
            webBack.setImageResource(R.drawable.no_left);
        }
        if(!webView.canGoForward()){
            webForward.setImageResource(R.drawable.no_right);
        }


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

        webBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
        webForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
        webRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    // Method to load URL and check if it's a url
    void loadMyUrl(String url){
        boolean isUrl = Patterns.WEB_URL.matcher(url).matches();
        if(isUrl){
          webView.loadUrl(url);
        }
        else{
           webView.loadUrl("https://wwww.google.com/search?q="+url);
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
            if(Counter == 1) {
                browserSearch.setText("");
                Counter++;
            }
            else{
                browserSearch.setText(webView.getUrl());
            }
        }

    }

}