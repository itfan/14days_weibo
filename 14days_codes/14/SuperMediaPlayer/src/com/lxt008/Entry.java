package com.lxt008;

import com.lxt008.music.MusicActivity;
import com.lxt008.video.VideoActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Entry extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enrty);
		ImageView imageview = (ImageView) this.findViewById(R.id.logo);
		imageview.setImageResource(R.drawable.logo);
		imageview.setOnClickListener(new View.OnClickListener(){
	
			public void onClick(View arg0) {
				Intent mainIntent=new Intent(Entry.this,Main.class);
				startActivity(mainIntent);				
			}
			
			
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		// 设置menu界面为res/menu/menu.xml
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();

		switch (menuId)
		{
			case R.id.music:
				Intent musicIntent=new Intent(Entry.this,MusicActivity.class);
				startActivity(musicIntent);
				break;
			case R.id.video:
				Intent videoIntent=new Intent(Entry.this,VideoActivity.class);
				startActivity(videoIntent);
				break;
			case R.id.about:
				new AlertDialog.Builder(this)
					.setTitle("刘晓涛软件培训课程")
					.setIcon(R.drawable.netbeans)
					.setMessage("软件名称:超级媒体播放器\n\n作者:刘晓涛\n")
					.setPositiveButton("确认", 
							new DialogInterface.OnClickListener(){							
								public void onClick(DialogInterface arg0,int arg1) {								
									
								}						
							}
					).show();
				break;
			case R.id.exit:
				Entry.this.finish();
				break;
		}

		return super.onOptionsItemSelected(item);
	}
}
