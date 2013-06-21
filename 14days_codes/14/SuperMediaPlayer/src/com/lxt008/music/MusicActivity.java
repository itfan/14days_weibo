package com.lxt008.music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lxt008.R;
import com.lxt008.common.BeanUtils;
import com.lxt008.common.DownloadFileActivity;
import com.lxt008.common.XmlUtil;

public class MusicActivity extends ListActivity implements OnClickListener {
	public MediaPlayer mediaPlayer;

	/* �����б� */
	private List<String> musicList = new ArrayList<String>();

	/* ��ǰ���Ÿ��������� */
	private int currentListItme = 0;

	/* ���ֵ�·�� */
	private static final String MUSIC_PATH = new String("/sdcard/");
	
	
	List<String> musics;
	
	TextView textView;
	TextView progressText;
	ProgressBar progressBar;
	
	int counter;
	float downPercent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music);

		textView=(TextView)findViewById(R.id.musicState);
		Button downButton = (Button) findViewById(R.id.btnDown);
		Button startButton = (Button) findViewById(R.id.btnStart);
		Button stopButton = (Button) findViewById(R.id.btnStop);
		Button pauseButton = (Button) findViewById(R.id.btnPause);
		Button nextButton = (Button) findViewById(R.id.btnNext);
		//Button previousButton = (Button) findViewById(R.id.btnPrevious);

		downButton.setOnClickListener(MusicActivity.this);
		startButton.setOnClickListener(MusicActivity.this);
		stopButton.setOnClickListener(MusicActivity.this);
		pauseButton.setOnClickListener(MusicActivity.this);
		nextButton.setOnClickListener(MusicActivity.this);
		//previousButton.setOnClickListener(MusicActivity.this);
		
		progressText=(TextView)findViewById(R.id.progressTextView);
		progressBar=(ProgressBar)findViewById(R.id.myProgressBar);

		/* ����MediaPlayer���� */
		mediaPlayer = new MediaPlayer();

		/* ������ʾ�����б� */
		musicList();
	}
	
	

	/* �����б� */
	public void musicList() {

		URLConnection conn;
		InputStream is_xml;
		

		try {
			URL url = new URL(
					"http://192.168.10.31:8080/MediaSite/music/musics.xml");
			try {
				conn = url.openConnection();

				// ֱ�Ӵ��������н���
				is_xml = conn.getInputStream();

				musics = XmlUtil.parseXml(is_xml, "music");
				Iterator it = musics.iterator();

				while (it.hasNext()) {
					String music = (String) it.next();
					Log.v("MusicActivity", music);
				}

				is_xml.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// ȡ������λ�õ��ļ�������ʾ�������б�

		ArrayAdapter<String> musicArrayList = new ArrayAdapter<String>(
				MusicActivity.this, R.layout.musicitme, musics);
		setListAdapter(musicArrayList);
		Log.v("musicList()","musicList success");

	}

	public void onClick(View view) {

		if (view.getId() == R.id.btnDown) {
			Intent intent = new Intent(MusicActivity.this,
					DownloadFileActivity.class);
			startActivity(intent);
		}
		if (view.getId() == R.id.btnStart) {
			Toast.makeText(MusicActivity.this.getApplicationContext(), "Start",
					Toast.LENGTH_SHORT).show();
			//String path = MUSIC_PATH + musicList.get(currentListItme);
			//playMusic(path);
		}
		if (view.getId() == R.id.btnStop) {
			Toast.makeText(MusicActivity.this.getApplicationContext(), "Stop",
					Toast.LENGTH_SHORT).show();
			stopMusic();
		}
		if (view.getId() == R.id.btnPause) {
			Toast.makeText(MusicActivity.this.getApplicationContext(), "Pause",
					Toast.LENGTH_SHORT).show();
			pauseMusic();
		}
		if (view.getId() == R.id.btnNext) {
			Toast.makeText(MusicActivity.this.getApplicationContext(), "Next",
					Toast.LENGTH_SHORT).show();
			nextMusic();
		}
//		if (view.getId() == R.id.btnPrevious) {
//			Toast.makeText(MusicActivity.this.getApplicationContext(),
//					"Previous", Toast.LENGTH_SHORT).show();
//			previousMusic();
//		}

	}

	@Override
	/* �����ǵ���б�ʱ�����ű���������� */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("Super Media Player", "downloading");
		final int index=position;
		
		new Thread(new Runnable(){				

			public void run() {
				//����
				try {
					URL url=new URL("http://192.168.10.31:8080/MediaSite/music/song.mp3");
					URLConnection conn=url.openConnection();
					InputStream is=conn.getInputStream();
					
					File destFile = new File("/sdcard/"+musics.get(index));
					destFile.createNewFile();				
					String path = destFile.getAbsolutePath();
					
					long fileSize=destFile.length();
					
					Log.v("MusicActivity", path);
					Log.v("MusicActivity","File length:"+destFile.length());
					FileOutputStream fos_sdcard = new FileOutputStream(destFile);
					int bytesRead;
					byte buff[] = new byte[1024];
					
					counter=0;
					
					while ((bytesRead = is.read(buff)) > 0) {
						
						counter=counter+bytesRead;				
						Log.v("MusicActivity",""+counter);
						downPercent=counter/fileSize;					
						Log.v("MusicActivity",downPercent+"%");
						fos_sdcard.write(buff, 0, bytesRead);
						if(bytesRead!=-1)
						{
							Message msg=new Message();
							msg.what=1;						
							handler.sendMessage(msg);
						}
						
//						if((downPercent%10)==0)
//						{
//							Message msg=new Message();
//							msg.what=1;						
//							handler.sendMessage(msg);
//						}											
					}
					//msg.what=0��ʾ�������
					//msg.what=1��ʾ��������
					if(bytesRead==-1)
					{
						Message msg=new Message();
						msg.what=0;
						handler.sendMessage(msg);
					}
					
					fos_sdcard.close();			
					is.close();					
					Log.v("Super Media Player", "downloaded");	
					
					startMusicService();
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}		
			
		}).start();		
		
		
	}
	
	private void startMusicService()
	{
		//��������		
		Intent intent=new Intent(MusicActivity.this,MusicService.class);
		intent.putExtra("music_path", MUSIC_PATH + musics.get(currentListItme));
		startService(intent);
		Log.v("Super Media Player", "start music service");
		//���չ㲥��Ϣ
		IntentFilter intentFilter=new IntentFilter("com.lxt008.MusicService_Music_Complete");
		MusicBroadcastReceiver musicBroadcastReceiver=new MusicBroadcastReceiver();
		registerReceiver(musicBroadcastReceiver, intentFilter);
	}

	private void playMusic(String path) {
		try {
			/* ����MediaPlayer */
			mediaPlayer.reset();
			/* ����Ҫ���ŵ��ļ���·�� */
			mediaPlayer.setDataSource(path);
			/* ׼������ */
			mediaPlayer.prepare();
			/* ��ʼ���� */
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					// �������һ��֮�������һ��
					nextMusic();
				}
			});
		} catch (IOException e) {
		}
	}

	/* ��һ�� */
	private void nextMusic() {
		if (++currentListItme >= musicList.size()) {
			currentListItme = 0;
		} else {
			playMusic(MUSIC_PATH + musicList.get(currentListItme));
		}
	}

	/* ��һ�� */
	private void previousMusic() {
		if (--currentListItme >= 0) {
			currentListItme = musicList.size();
		} else {
			playMusic(MUSIC_PATH + musicList.get(currentListItme));
		}
	}

	private void pauseMusic() {
		if (mediaPlayer.isPlaying()) {
			/* ��ͣ */
			mediaPlayer.pause();
		} else {
			/* ��ʼ���� */
			mediaPlayer.start();
		}
	}

	private void stopMusic() {
		if (mediaPlayer.isPlaying()) {
			/* ֹͣ */
			mediaPlayer.reset();
		}

	}	
	
	class MusicBroadcastReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context ctx, Intent intent) {
			Log.v("MusicActivity", "play complete one MP3!!!");
			Bundle data=intent.getExtras();
			String music_state=data.getString("music_state");
			if(music_state.equals("complete"))
			{
				textView.setText("Music complete play!!!");				
				textView.setTextColor(Color.RED);
			}
			
		}
		
	}
	
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			
			//��������
			if(msg.what==1)
			{
				if (!Thread.currentThread().isInterrupted()) {
					
					//String percent=msg.getData().getString("downPercent");
					
					BeanUtils.setFieldValue(progressBar, "mOnlyIndeterminate", new Boolean(false));
					progressBar.setIndeterminate(false);					
					
					progressBar.setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));   
					progressBar.setIndeterminateDrawable(getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));  
					
					progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress((int)(downPercent));
					/* ����ʾ������ʾ��TextView���� */
					progressText.setText("���ؽ��ȣ�"+downPercent+"%)\n");
				}				
			}
			//�������
			if(msg.what==0)
			{
				progressText.setText("�������");
				/* �趨ProgressBar WidgetΪ���� */
				progressBar.setVisibility(View.GONE);
				Thread.currentThread().interrupt();				
			}
			
			super.handleMessage(msg);
		}
		
	};

}