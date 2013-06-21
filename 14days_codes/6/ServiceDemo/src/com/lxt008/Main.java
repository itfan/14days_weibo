package com.lxt008;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener  {
	private MyService  mMyService;   
    private TextView mTextView;   
    private Button startServiceButton;   
    private Button stopServiceButton;  
   
    private Context mContext;       
   
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
        setupViews();   
    }          
    public void setupViews(){ 
    	
        mContext = Main.this; 
        
        mTextView = (TextView)findViewById(R.id.text);        
           
        startServiceButton = (Button)findViewById(R.id.startservice);   
        stopServiceButton = (Button)findViewById(R.id.stopservice);   
         
        startServiceButton.setOnClickListener(this);   
        stopServiceButton.setOnClickListener(this);   
        
    }     
    public void onClick(View v) {         
        if(v == startServiceButton){   
            Intent i  = new Intent();   
            i.setClass(Main.this, MyService.class);   
            mContext.startService(i);   
        }else if(v == stopServiceButton){   
            Intent i  = new Intent();   
            i.setClass(Main.this, MyService.class);   
            mContext.stopService(i);   
        } 
    }        
}