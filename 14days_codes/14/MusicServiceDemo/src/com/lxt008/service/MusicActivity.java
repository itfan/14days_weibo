package com.lxt008.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MusicActivity extends ListActivity 
{
	/* ����������ť */
	private ImageButton	mFrontImageButton	= null;
	private ImageButton	mStopImageButton	= null;
	private ImageButton	mStartImageButton	= null;
	private ImageButton	mPauseImageButton	= null;
	private ImageButton	mNextImageButton	= null;
	
	private Intent serviceIntent;
	
	private MusicService musicService;	
	
	
	private ServiceConnection serviceConnection = new ServiceConnection()
	{
		public void onServiceDisconnected(ComponentName name)
		{
			musicService = null;
			Toast.makeText(MusicActivity.this, "Service Failed.", Toast.LENGTH_LONG)
					.show();
		}


		public void onServiceConnected(ComponentName name, IBinder service)
		{
			
			musicService = ((MusicService.MyBinder) service).getService();
			Toast.makeText(MusicActivity.this, "Service Connected.", Toast.LENGTH_LONG)
					.show();

		}
	};
	
	
	
	/* �����б� */
	private List<String> mMusicList = new ArrayList<String>();
	
	/* ��ǰ���Ÿ��������� */
	private int currentListItem = 0;
	
	/* ���ֵ�·�� */
	private static final String MUSIC_PATH = new String("/sdcard/");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaa);	
		
		
		/* ������ʾ�����б� */
		musicList();		

		mFrontImageButton = (ImageButton) findViewById(R.id.LastImageButton); 
		mStopImageButton = (ImageButton) findViewById(R.id.StopImageButton);
		mStartImageButton = (ImageButton) findViewById(R.id.StartImageButton); 
		mPauseImageButton = (ImageButton) findViewById(R.id.PauseImageButton);
		mNextImageButton = (ImageButton) findViewById(R.id.NextImageButton); 
		
		serviceIntent=new Intent(MusicActivity.this,MusicService.class);
		serviceIntent.putExtra("mMusicList", mMusicList.toArray());			
		serviceIntent.putExtra("currentListItem", currentListItem);
		bindService(serviceIntent, serviceConnection,Context.BIND_AUTO_CREATE);	
		
		//��ʼ��ť
		mStartImageButton.setOnClickListener(new ImageButton.OnClickListener() 
		{		
			public void onClick(View v)
			{
				musicService.startMusic(MUSIC_PATH + mMusicList.get(currentListItem));		
			}
		});  
		
		//ֹͣ��ť
		mStopImageButton.setOnClickListener(new ImageButton.OnClickListener() 
		{
	
			public void onClick(View v)
			{
				musicService.stopMusic();
			}
		}); 		
		
		
		//��ͣ
		mPauseImageButton.setOnClickListener(new ImageButton.OnClickListener() 
		{
			public void onClick(View view)
			{	
				musicService.pauseMusic();
			}
		});
		
		//��һ��
		mNextImageButton.setOnClickListener(new ImageButton.OnClickListener() 
		{

			public void onClick(View arg0)
			{
				musicService.nextMusic();	
			}
		});
		//��һ��
		mFrontImageButton.setOnClickListener(new ImageButton.OnClickListener() 
		{
	
			public void onClick(View arg0)
			{
				musicService.previousMusic();		
			}
		});
	}	
	
	
	@Override
	/* �����ǵ���б�ʱ�����ű���������� */
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		musicService.startMusic(MUSIC_PATH + mMusicList.get(currentListItem));	
	}


	/* �����б� */
	public void musicList()
	{
		//ȡ��ָ��λ�õ��ļ�������ʾ�������б�
		File home = new File(MUSIC_PATH);
		if (home.listFiles(new MusicFilter()).length > 0)
		{
			for (File file : home.listFiles(new MusicFilter()))
			{
				mMusicList.add(file.getName());
			}
			ArrayAdapter<String> musicList = new ArrayAdapter<String>(MusicActivity.this,R.layout.musicitme, mMusicList);
			setListAdapter(musicList);
		}
	}
	

}

/* �����ļ����� */
class MusicFilter implements FilenameFilter
{
	public boolean accept(File dir, String name)
	{
		//���ﻹ��������������ʽ�������ļ�
		return (name.endsWith(".mp3"));
	}
}

