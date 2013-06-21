package com.lxt008;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * ��Ҫ�̳���TabActivity�࣡
 * @author teacher
 *
 */
public class Main extends TabActivity {
	public static final String TAG="Main";
	//����TabHost����
	TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//ȡ��TabHost����
		mTabHost = getTabHost();
	    
		/* ΪTabHost��ӱ�ǩ */
		//�½�һ��newTabSpec(newTabSpec)
		//�������ǩ��ͼ��(setIndicator)
		//��������(setContent)
	    mTabHost.addTab(mTabHost.newTabSpec("tab_local_music")
	    		.setIndicator("���ز�������",getResources().getDrawable(R.drawable.img1))
	    		.setContent(R.id.musicLa));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_remote_music")
	    		.setIndicator("���߲�������",getResources().getDrawable(R.drawable.img2))
	    		.setContent(R.id.textview2));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_local_video")
	    		.setIndicator("���ز�����Ƶ",getResources().getDrawable(R.drawable.img3))
	    		.setContent(R.id.textview3));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_remote_video")
	    		.setIndicator("���߲�����Ƶ",getResources().getDrawable(R.drawable.img3))
	    		.setContent(R.id.textview4));
	    
	    //����TabHost�ı�����ɫ
	    mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	    //����TabHost�ı���ͼƬ��Դ
	    //mTabHost.setBackgroundResource(R.drawable.bg0);
	    
	    //���õ�ǰ��ʾ��һ����ǩ
	    mTabHost.setCurrentTab(0);
	    
	    //��ǩ�л��¼�����setOnTabChangedListener 
	    mTabHost.setOnTabChangedListener(new OnTabChangeListener()
	    {	
			public void onTabChanged(String tabId) {			
				Log.v(TAG,tabId);
			}      
        });
	}
	
	

}
