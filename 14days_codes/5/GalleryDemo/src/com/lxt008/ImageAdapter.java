package com.lxt008;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	// 定义Context
	private Context		mContext;		
	// 定义整型数组 即图片源
	private Integer[]	mImageIds = 
	{ 						
			R.drawable.img1, 
			R.drawable.img2, 
			R.drawable.img3, 
			R.drawable.img4, 
			R.drawable.img5, 
			R.drawable.img6, 
			R.drawable.img7,
			R.drawable.img8,		
	};

	// 声明 ImageAdapter
	public ImageAdapter(Context c)
	{
		mContext = c;
	}

	// 获取图片的个数
	public int getCount()
	{
		return mImageIds.length;
	}

	// 获取图片在库中的位置
	public Object getItem(int position)
	{
		return position;
	}

	// 获取图片ID
	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageview = new ImageView(mContext);

		// 给ImageView设置资源
		imageview.setImageResource(mImageIds[position]);
		// 设置布局 图片120×120显示
		imageview.setLayoutParams(new Gallery.LayoutParams(120, 120));
		// 设置显示比例类型
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}
}

