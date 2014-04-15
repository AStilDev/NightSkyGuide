package com.example.constdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 
 * @author Nick Wilson, Alisha Hayman
 * 
 * Class works with an xml file in order to load a webpage inside an
 * application. 
 *
 */
public class WebViewActivity extends Activity {
	/* Basically a webpage object */
	private WebView mWebView;
	
	/**
	 * Method performs most of the work of this part of the application.
	 * It overrides the default webview so that the application does not
	 * call an external browser, and then loads and displays the url inside
	 * the application.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		
		/* This is so url is loaded in app rather than calling external browser */
		WebViewClient yourWebClient = new WebViewClient() {
			// override page so it loads my view only
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		};
		mWebView = (WebView) findViewById(R.id.webView1);
		// Stop local links and redirects from opening in browser instead of WebView
		mWebView.setWebViewClient(yourWebClient);
		
		Intent intent = getIntent();
		String link = intent.getStringExtra("url");
		
		// Enable Javascript
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// Stop local links and redirects from opening in browser instead of WebView
		mWebView.loadUrl(link);
	}
}
