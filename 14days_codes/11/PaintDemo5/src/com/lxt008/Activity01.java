package com.lxt008;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Activity01 extends Activity
{
	private GameView mGameView = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mGameView = new GameView(this);
		
		setContentView(mGameView);
	}
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		super.onKeyUp(keyCode, event);
		return true;
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ( mGameView == null )
		{
			return false;
		}
		return mGameView.onKeyDown(keyCode,event);
	}
}
