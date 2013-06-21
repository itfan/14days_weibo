package com.lxt008.video;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

public class VideoService extends Service{
	public static final String TAG="VideoService";
	MediaPlayer mediaPlayer=null;
	
	private void playVideo(String path) {
		try {
			mediaPlayer=new MediaPlayer();
			/* 重置MediaPlayer */
			mediaPlayer.reset();
			/* 设置要播放的文件的路径 */
			mediaPlayer.setDataSource(path);
			/* 准备播放 */
			mediaPlayer.prepare();
			/* 开始播放 */
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					Intent i = new Intent("com.lxt008.VideoService_Video_Complete");//创建Intent
					i.putExtra("video_state","complete");//放入数据
					sendBroadcast(i);//发送广播
				}
			});
		} catch (IOException e) {
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG,"onStart()");
		super.onStart(intent, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
	
	

}
