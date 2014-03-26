package com.example.constdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		
		mWebView = (WebView) findViewById(R.id.webView1);
		// Stop local links and redirects from opening in browser instead of WebView
		mWebView.setWebViewClient(new MyAppWebViewClient());
		
		Intent intent = getIntent();
		String link = intent.getStringExtra("url");
		
		// Enable Javascript
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// Stop local links and redirects from opening in browser instead of WebView
		//mWebView.setWebViewClient(new MyAppWebViewClient());
		
		mWebView.loadUrl(link);
	}

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.web_view, menu);
		//return true;
	//}

}
