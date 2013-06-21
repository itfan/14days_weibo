package com.lxt008.onlinemp3;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main extends Activity {
	
	private WebView webView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWidget();
    }
    
    public void getWidget(){
    	this.webView = (WebView) this.findViewById(R.id.webView);
    	this.webView.getSettings().setJavaScriptEnabled(true);
    	this.webView.getSettings().setAppCacheEnabled(false);
    	this.webView.getSettings().setSupportZoom(true);
    	this.webView.getSettings().setBuiltInZoomControls(true);
    	this.webView.addJavascriptInterface(new PlayMusic(this), "musicplayer");
    	this.webView.loadUrl("http://192.168.1.72:8888/MediaSite/index.html");
    }
    
}