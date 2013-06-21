package com.lxt008;

import android.app.Activity;
import android.os.Bundle;

public class Activity02 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//这里直接限制一个TextView
		setContentView(R.layout.main2);
	}
}

