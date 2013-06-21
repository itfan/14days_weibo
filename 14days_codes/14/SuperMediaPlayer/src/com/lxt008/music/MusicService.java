package com.lxt008.music;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service{
	public MediaPlayer mediaPlayer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onCreate()
	{
		Log.d("MusicService", "onCreate");
		super.onCreate();
	}


	@Override
	public void onStart(Intent intent, int startId) {	
		Log.v("Super Media Player", "music service started");
		String path=intent.getExtras().getString("music_path");
		playMusic(path);
		Log.v("MusciService","start playMusic ......");
		super.onStart(intent, startId);
	}
	
	private void playMusic(String path) {
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
				public void onCompletion(MediaPlayer mediaPlayer) {
					Intent i = new Intent("com.lxt008.MusicService_Music_Complete");//创建Intent
					i.putExtra("music_state","complete");//放入数据
					sendBroadcast(i);//发送广播
				}
			});
		} catch (IOException e) {
		}
	}
}