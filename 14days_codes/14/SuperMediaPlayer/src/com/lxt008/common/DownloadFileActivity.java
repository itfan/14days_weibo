package com.lxt008.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.lxt008.R;

public class DownloadFileActivity extends Activity {
	public static final String TAG = "DownloadFileActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置窗口模式，因为需要显示进度条在标题栏
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setProgressBarVisibility(true);		
		setContentView(R.layout.progressbar);
		Log.v(TAG, "start download......");
		URLConnection conn;
		InputStream is_xml;
		InputStream is_data;
		InputStream is_sdcard;
		String destPath = "/sdcard";
		try {
			URL url = new URL("http://192.168.50.50:8080/MediaSite/music/musics.xml");
			try {
				conn = url.openConnection();
				
				// 直接从输入流中解析
				is_xml = conn.getInputStream();						

				if (!LxtAndroidUtil.checkSDCard()) {
					Log.v(TAG, "sdcard not exist!!!");
					return;
				}
				Log.v(TAG, "sdcard exist!!!");

				List<String> musics = XmlUtil.parseXml(is_xml,"music");
				Iterator<String> it = musics.iterator();

				while (it.hasNext()) {
					String music = (String) it.next();
					Log.v(TAG, music);
				}
				
				is_xml.close();				
				
				// 写到/data/data中
				conn = url.openConnection();
				is_data = conn.getInputStream();				
				FileOutputStream fos = DownloadFileActivity.this
						.openFileOutput("musics.xml", MODE_PRIVATE);
				int bytesRead_data;
				byte buff_data[] = new byte[1024];
				while ((bytesRead_data = is_data.read(buff_data)) > 0) {
					fos.write(buff_data, 0, bytesRead_data);
				}
				
				fos.close();
				is_data.close();
				
				// 写到/sdcard中
				conn = url.openConnection();
				is_sdcard = conn.getInputStream();		
				File destFile = new File("/sdcard/musics.xml");
				destFile.createNewFile();				
				String path = destFile.getAbsolutePath();
				Log.v(TAG, path);
				FileOutputStream fos_sdcard = new FileOutputStream(destFile);
				int bytesRead;
				byte buff[] = new byte[1024];
				while ((bytesRead = is_sdcard.read(buff)) > 0) {
					fos_sdcard.write(buff, 0, bytesRead);
				}
				
				fos_sdcard.close();			
				is_sdcard.close();

			} catch (IOException e) {			
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}

	}
}
