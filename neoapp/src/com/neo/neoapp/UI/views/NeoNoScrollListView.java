package com.neo.neoapp.UI.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NeoNoScrollListView extends GridView {

	public NeoNoScrollListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public NeoNoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public NeoNoScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, mExpandSpec);  
   } 

}
