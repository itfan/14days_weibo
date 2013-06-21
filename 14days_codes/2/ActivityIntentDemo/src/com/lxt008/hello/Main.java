package com.lxt008.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn=(Button)findViewById(R.id.Button01);
        btn.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View arg0) {
						
						Intent intent=new Intent(Main.this,Activity1.class);
						
						Bundle bundle=new Bundle();
						bundle.putString("username","lxt008");
						bundle.putInt("age",100);
						
						intent.putExtras(bundle);
						
						//startActivity(intent);
						startActivityForResult(intent, 1);						
						
						//Main.this.finish();
					}
				}
        );
    
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Log.v("Main", ""+requestCode);
    	Log.v("Main", ""+resultCode);
    	Log.v("Main", data.getExtras().getString("address"));
    }
    
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("Main", "onDestroy()");
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		Log.v("Main", "onPause()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v("Main", "onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("Main", "onResume()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v("Main", "onStart()");
	}

	@Override
	protected void onStop() {		
		super.onStop();
		Log.v("Main", "onStop()");
	}
	
}