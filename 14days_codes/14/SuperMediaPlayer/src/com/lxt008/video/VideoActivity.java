package com.lxt008.video;

import com.lxt008.R;
import com.lxt008.R.id;
import com.lxt008.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		//VideoView videoView=(VideoView)findViewById(R.id.VideoView01);
		/* 操作播放的三个按钮 */
		Button loadButton = (Button) this.findViewById(R.id.btnLoad);
		Button playButton = (Button) this.findViewById(R.id.btnPlay);
		Button pauseButton = (Button) this.findViewById(R.id.btnPause);
		
		WebView webView=(WebView)findViewById(R.id.webview);
		WebSettings setting=webView.getSettings();
		setting.setJavaScriptEnabled(true);
			
		webView.loadUrl("http://192.168.50.50:8080/MediaSite/index.jsp");
		webView.setWebChromeClient(new WebChromeClient());
		
		
	}
}
