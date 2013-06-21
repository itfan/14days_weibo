package com.lxt008;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class MyService extends Service {
	
	// 定义个一个Tag标签
    private static final String TAG = "MyService";   
    // 一个Binder类，用在onBind()有方法里，这样Activity那边可以获取到
    private MyBinder mBinder = new MyBinder(); 
    
    public IBinder onBind(Intent intent) {   
        Log.e(TAG, "start IBinder~~~");   
        return mBinder;   
    }      
    public void onCreate() {   
        Log.e(TAG, "start onCreate~~~");   
        super.onCreate();   
    }    
    public void onStart(Intent intent, int startId) {   
        Log.e(TAG, "start onStart~~~");   
        super.onStart(intent, startId);    
    }        
    public void onDestroy() {   
        Log.e(TAG, "start onDestroy~~~");   
        super.onDestroy();   
    }        
    public boolean onUnbind(Intent intent) {   
        Log.e(TAG, "start onUnbind~~~");   
        return super.onUnbind(intent);   
    }      
    public String getSystemTime(){           
        Time t = new Time();   
        t.setToNow();   
        return t.toString();   
    }        
    public class MyBinder extends Binder{   
        MyService getService()   
        {   
            return MyService.this;   
        }   
    } 
 }
