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
			/* ����MediaPlayer */
			mediaPlayer.reset();
			/* ����Ҫ���ŵ��ļ���·�� */
			mediaPlayer.setDataSource(path);
			/* ׼������ */
			mediaPlayer.prepare();
			/* ��ʼ���� */
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mediaPlayer) {
					Intent i = new Intent("com.lxt008.MusicService_Music_Complete");//����Intent
					i.putExtra("music_state","complete");//��������
					sendBroadcast(i);//���͹㲥
				}
			});
		} catch (IOException e) {
		}
	}
}