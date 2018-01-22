package com.lixm.tvdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends Activity {

    private WebView webView;
    private WebSettings settings;
    private ProgressBar pb;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.web);
        pb = findViewById(R.id.pb);
        url = "https://www.hao123.com/";
//url="http://www.cnfol.com";
//        url="https://www.baidu.com/";
        settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBlockNetworkImage(true);
        settings.setDefaultTextEncodingName("utf-8"); // 设置文本编码
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置
        //        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.setWebChromeClient(new MyChromeClient());
        webView.setWebViewClient(new MyWebViewClient());


        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();   //后退
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.next_in, R.anim.next_out);
    }

    private class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pb.setProgress(newProgress);
            if (newProgress == 100) {
                pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    // webview设置的代理类
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // return true;// 这里将图片的超链接屏蔽掉，点击图片的时候调用自己的javaScrip显示图片
            return super.shouldOverrideUrlLoading(view, url);

        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            view.getSettings().setBlockNetworkImage(false);
        }

        @Override
        public void onPageStarted(WebView view, final String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }
}
