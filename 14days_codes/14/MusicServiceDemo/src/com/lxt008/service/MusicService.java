package com.lxt008.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
	
	public static final String TAG="MusicService";
	
	/* MediaPlayer���� */
	public MediaPlayer	mMediaPlayer = new MediaPlayer();
	
	/* �����б� */
	Object mMusicList[];
	
	/* ����״̬ */
	String playerState;
	
	/* ��ǰ�������� */
	int currentListItem;

	public void onCreate() {	
		super.onCreate();
	}


	public void onDestroy() {
		super.onDestroy();
		mMediaPlayer=null;
	}


	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}


	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public IBinder onBind(Intent intent) {
		Log.v(TAG,"onBind()");
		mMusicList=(Object[])intent.getExtras().getSerializable("mMusicList");
		currentListItem=(int)intent.getExtras().getInt("currentListItem");
		Log.v(TAG,""+currentListItem);		
		return new MyBinder();
	}
	
	public void startMusic(String path)
	{
		Log.v(TAG,"StartMusic");
	}

	/* ��һ�� */
	public void nextMusic()
	{
		Log.v(TAG,"nextMusic");
	}
	
	/* ��һ�� */
	public void previousMusic()
	{
		Log.v(TAG,"previousMusic");
	}
	
	public void stopMusic()
	{
		Log.v(TAG,"stopMusic");
	}
	
	public void pauseMusic()
	{
		Log.v(TAG,"pauseMusic");
	}
	
	public class MyBinder extends Binder
	{
		MusicService getService()
		{
			return MusicService.this;
		}
	}

}
