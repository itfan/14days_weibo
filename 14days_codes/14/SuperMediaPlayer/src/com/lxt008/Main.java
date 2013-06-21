package com.lxt008;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * 需要继承自TabActivity类！
 * @author teacher
 *
 */
public class Main extends TabActivity {
	public static final String TAG="Main";
	//声明TabHost对象
	TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//取得TabHost对象
		mTabHost = getTabHost();
	    
		/* 为TabHost添加标签 */
		//新建一个newTabSpec(newTabSpec)
		//设置其标签和图标(setIndicator)
		//设置内容(setContent)
	    mTabHost.addTab(mTabHost.newTabSpec("tab_local_music")
	    		.setIndicator("本地播放音乐",getResources().getDrawable(R.drawable.img1))
	    		.setContent(R.id.musicLa));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_remote_music")
	    		.setIndicator("在线播放音乐",getResources().getDrawable(R.drawable.img2))
	    		.setContent(R.id.textview2));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_local_video")
	    		.setIndicator("本地播放视频",getResources().getDrawable(R.drawable.img3))
	    		.setContent(R.id.textview3));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_remote_video")
	    		.setIndicator("在线播放视频",getResources().getDrawable(R.drawable.img3))
	    		.setContent(R.id.textview4));
	    
	    //设置TabHost的背景颜色
	    mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	    //设置TabHost的背景图片资源
	    //mTabHost.setBackgroundResource(R.drawable.bg0);
	    
	    //设置当前显示哪一个标签
	    mTabHost.setCurrentTab(0);
	    
	    //标签切换事件处理，setOnTabChangedListener 
	    mTabHost.setOnTabChangedListener(new OnTabChangeListener()
	    {	
			public void onTabChanged(String tabId) {			
				Log.v(TAG,tabId);
			}      
        });
	}
	
	

}
