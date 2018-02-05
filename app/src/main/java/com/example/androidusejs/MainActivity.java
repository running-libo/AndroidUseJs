package com.example.androidusejs;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webviewAuth;
    private TextView tvJs;
    private TextView tvJsArgs;
    private TextView tvShowmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWebview();
        initView();
    }

    private void initView() {
        tvJs = (TextView) findViewById(R.id.tv_androidcalljs);
        tvJsArgs = (TextView) findViewById(R.id.tv_androidcalljsargs);
        tvShowmsg = (TextView) findViewById(R.id.tv_showmsg);

        tvJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webviewAuth.loadUrl("javascript:javacalljs()");
            }
        });

        tvJsArgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webviewAuth.loadUrl("javascript:javacalljswith(" + "'Android传过来的参数'" + ")");
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    private void setWebview() {
        webviewAuth = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webviewAuth.getSettings();
        //与js交互必须设置
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webviewAuth.loadUrl("file:///android_asset/html.html");
        webviewAuth.addJavascriptInterface(MainActivity.this,"android");
    }

    @JavascriptInterface
    public void jsCallAndroid(){
        tvShowmsg.setText("Js调用Android方法");
    }

    @JavascriptInterface
    public void jsCallAndroidArgs(String args){
        tvShowmsg.setText(args);
    }
}
