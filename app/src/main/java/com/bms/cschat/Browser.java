package com.bms.cschat;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Browser extends AppCompatActivity{
    SwipeRefreshLayout refreshLayout;
    EditText browserSearch;
    WebView webView;
    ProgressBar progressBar;
    int Counter = 1;
    public final String Homepage = "https://ronaldkelechi11.github.io/CS-Chat-Browser-Homepage/";

    ImageView webBack,webForward;
    CardView lowerNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        webView = findViewById(R.id.browserWebview);
        browserSearch = findViewById(R.id.browser_search);
        progressBar = findViewById(R.id.progressBarWebView);

        webBack = findViewById(R.id.goBack);
        webForward = findViewById(R.id.goForward);
        refreshLayout = findViewById(R.id.refresh);
        lowerNavBar = findViewById(R.id.lowerNavBar);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSaveFormData(true);
        webView.setWebViewClient(new MyWebViewClient());

        loadMyUrl(Homepage);



        // Keyboard Visibility Event to hide the Lower Nav Bar if KeyBoard is visible
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if(isOpen){
                    lowerNavBar.setVisibility(View.GONE);
                }
                else {
                    lowerNavBar.setVisibility(View.VISIBLE);
                }
            }
        });


        // Keyboard Input DONE or GO
        browserSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String url = browserSearch.getText().toString();
                if(i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(browserSearch.getWindowToken(),0);
                    loadMyUrl(url);
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
                else if(!webView.canGoBack()){
                    Toast.makeText(getApplicationContext(), "Can't Go Backward", Toast.LENGTH_SHORT).show();
                }
            }
        });
        webForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
                else if(!webView.canGoForward()){
                    Toast.makeText(getApplicationContext(), "Can't Go Forward", Toast.LENGTH_SHORT).show();
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
    }// End of Initial Class

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

    //KeyBack Button
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
           webView.loadUrl("https://www.google.com/search?q=" + url);
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
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
            refreshLayout.setRefreshing(true);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.INVISIBLE);
            refreshLayout.setRefreshing(false);
            if(Counter == 1) {
                browserSearch.setText("");
                Counter++;
            }
            else{
                browserSearch.setText(webView.getUrl());
            }
            super.onPageFinished(view, url);
        }
    }
    
}