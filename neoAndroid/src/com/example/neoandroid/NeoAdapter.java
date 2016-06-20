package com.example.neoandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class NeoAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context context;//иообнд
	public Integer[] myImageIds = { R.drawable.advancedbt, R.drawable.click,
			R.drawable.error, R.drawable.ic_launcher, R.drawable.lost,
			R.drawable.onfocus, };
	
	public NeoAdapter(Context context){
		this.context = context;
		TypedArray ta = context.obtainStyledAttributes(R.styleable.Gallery);
		mGalleryItemBackground = ta.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0);
		ta.recycle();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myImageIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ImageView imgv = new ImageView(context);
		imgv.setImageResource(myImageIds[arg0]);
		imgv.setScaleType(ImageView.ScaleType.FIT_XY);
		imgv.setLayoutParams(new Gallery.LayoutParams(128,128));
		imgv.setBackgroundResource(mGalleryItemBackground);
		return imgv;
	}

}
