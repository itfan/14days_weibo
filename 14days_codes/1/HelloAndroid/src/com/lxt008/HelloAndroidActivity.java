package com.lxt008;

import com.lxt008.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloAndroidActivity extends Activity
{
	/* 定义TAG标签，这样可以很好的区分打印出来的log */
	private static final String	TAG	= "HelloAndroid";


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* 打印出不同的log信息 */
		Log.v(TAG, "VERBOSE");
		Log.d(TAG, "DEBUG");
		Log.i(TAG, "INFO");
		Log.w(TAG, "WARN");
		Log.e(TAG, "ERROR");
		/* 设置Activity要显示的布局为(R.layout.main) */
		setContentView(R.layout.main);
	}
}
