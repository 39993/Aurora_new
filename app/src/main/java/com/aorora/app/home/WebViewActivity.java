package com.aorora.app.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aorora.app.R;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.MyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 * @文件名 WebViewActivity.java
 * @注释 网页展示类
 */

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends AppBaseActivity {

    private Map<String, String> parameterMap = new HashMap<String, String>();
    private String url = ARLConfig.URL;
    private WebView mWebView ;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MyUtils.isNetworkAvailable(this.getApplicationContext())){

            setContentView(R.layout.activity_web_view);
            parameterMap.put("fromApp", "true");
            Intent intent = getIntent();
            url = intent.getStringExtra("url");

            if (url.indexOf("?") == -1) {

            } else {
                url = url + "&fromApp=true";
            }

            Log.e("url_onCreate", url + "");
            title = intent.getStringExtra("title");

            setTitle(title);
            setDisplayHomeAsUpEnabled(true);


            // 初始化webview
            mWebView = (WebView) findViewById(R.id.webview);

            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new SampleWebViewClient());

            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setAllowFileAccess(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.loadUrl(url);

        }else{

            setContentView(MyUtils.getLayoutFromInflater(getApplicationContext(),
                    R.layout.fragment_home));

        }

    }


    /**
     * 返回监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView != null
                && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    private class SampleWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            showLoading();
            
            super.onPageStarted(view, url, favicon);

        }


        @Override
        public void onPageFinished(WebView view, String url) {

            dismissLoading();

            super.onPageFinished(view, url);

        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {



            return super.shouldOverrideUrlLoading(view, url);
        }


    }




}
