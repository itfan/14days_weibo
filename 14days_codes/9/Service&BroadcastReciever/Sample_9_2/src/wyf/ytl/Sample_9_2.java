package wyf.ytl;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Sample_9_2 extends Activity {
	Button button1;//声明按钮的引用
	Button button2;//声明按钮的引用
	TextView myTextView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1 = (Button) this.findViewById(R.id.myButton1);
        button2 = (Button) this.findViewById(R.id.myButton2);
        myTextView = (TextView) this.findViewById(R.id.myTextView);
        button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Sample_9_2.this, MyService.class);
				startService(i);
				Toast.makeText(Sample_9_2.this, "Service启动成功", Toast.LENGTH_SHORT).show();
			}
        });
        button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Sample_9_2.this, MyService.class);
				stopService(i);
				Toast.makeText(Sample_9_2.this, "Service停止成功", Toast.LENGTH_SHORT).show();
			}
        });      
        IntentFilter intentFilter = new IntentFilter("wyf.ytl.myThread");
        MyBroadcasReceiver myBroadcasReceiver = new MyBroadcasReceiver();
        registerReceiver(myBroadcasReceiver, intentFilter);
    }
    public class MyBroadcasReceiver extends BroadcastReceiver{
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		Bundle myBundle = intent.getExtras();
    		int myInt = myBundle.getInt("myThread");
    		if(myInt < 10){
    			myTextView.setText("后台Service运行了"+myInt+"秒");
    		}else{
    			Intent i = new Intent(Sample_9_2.this, MyService.class);
    			stopService(i);
    			myTextView.setText("后台Service在"+myInt+"秒后停止运行");
    		}
    	}   
    }
}