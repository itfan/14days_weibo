package com.lxt008.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		Log.v("Activity1", "onCreate()");
		
		Bundle bundle=this.getIntent().getExtras();
		Log.v("Activity1", bundle.getString("username"));
		Log.v("Activity1", ""+bundle.getInt("age"));
		
		Button btn=(Button)findViewById(R.id.Button02);
	        btn.setOnClickListener(
	        		new OnClickListener() {
						
						public void onClick(View arg0) {
							
							Intent intent=new Intent(Activity1.this,Main.class);
							startActivity(intent);
							
							Activity1.this.finish();
						}
					}
	        );
	        
	     Intent intent=new Intent();
	     intent.putExtra("address", "sz");
	     this.setResult(3, intent);
	     this.finish();
	}	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("Activity1", "onDestroy()");
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		Log.v("Activity1", "onPause()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v("Activity1", "onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("Activity1", "onResume()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v("Activity1", "onStart()");
	}

	@Override
	protected void onStop() {		
		super.onStop();
		Log.v("Activity1", "onStop()");
	}
	
}
