package com.lxt008;

import com.lxt008.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloAndroidActivity extends Activity
{
	/* ����TAG��ǩ���������Ժܺõ����ִ�ӡ������log */
	private static final String	TAG	= "HelloAndroid";


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* ��ӡ����ͬ��log��Ϣ */
		Log.v(TAG, "VERBOSE");
		Log.d(TAG, "DEBUG");
		Log.i(TAG, "INFO");
		Log.w(TAG, "WARN");
		Log.e(TAG, "ERROR");
		/* ����ActivityҪ��ʾ�Ĳ���Ϊ(R.layout.main) */
		setContentView(R.layout.main);
	}
}
