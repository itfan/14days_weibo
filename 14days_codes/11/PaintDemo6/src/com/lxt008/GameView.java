package com.lxt008;

import com.lxt008.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable
{
	/* ����Bitmap���� */
	Bitmap	mBitQQ	= null;
	
	Paint   mPaint = null;
	
	/* ����һ�������� */
	Bitmap	mSCBitmap = null;
	
	/* ����Canvas���� */
	Canvas mCanvas = null;   
	
	public GameView(Context context)
	{
		super(context);
		
		/* װ����Դ */
		mBitQQ = ((BitmapDrawable) getResources().getDrawable(R.drawable.qq)).getBitmap();
		
		/* ������Ļ��С�Ļ����� */
		mSCBitmap=Bitmap.createBitmap(320, 480, Config.ARGB_8888);  
		
		/* ����Canvas */
		mCanvas = new Canvas();  
		
		/* ���ý����ݻ�����mSCBitmap�� */
		mCanvas.setBitmap(mSCBitmap); 
		
		mPaint = new Paint();
		
		/* ��mBitQQ���Ƶ�mSCBitmap�� */
		mCanvas.drawBitmap(mBitQQ, 0, 0, mPaint);
		
		/* �����߳� */
		new Thread(this).start();
	}
	
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/* ��mSCBitmap��ʾ����Ļ�� */
		canvas.drawBitmap(mSCBitmap, 0, 0, mPaint);
	}
	
	// �����¼�
	public boolean onTouchEvent(MotionEvent event)
	{
		return true;
	}


	// ���������¼�
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		return true;
	}


	// ���������¼�
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		return false;
	}


	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		return true;
	}
	
	
	/**
	 * �̴߳���
	 */
	public void run()
	{
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			//ʹ��postInvalidate����ֱ�����߳��и��½���
			postInvalidate();
		}
	}
}
