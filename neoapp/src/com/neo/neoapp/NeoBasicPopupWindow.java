package com.neo.neoapp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

public abstract class NeoBasicPopupWindow extends PopupWindow {

	protected View mContentView;
	protected onSubmitClickListener mOnSubmitClickListener;

	public NeoBasicPopupWindow() {
		super();
	}

	public NeoBasicPopupWindow(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public NeoBasicPopupWindow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public NeoBasicPopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NeoBasicPopupWindow(Context context) {
		super(context);
	}

	public NeoBasicPopupWindow(int width, int height) {
		super(width, height);
	}

	public NeoBasicPopupWindow(View contentView, int width, int height,
			boolean focusable) {
		super(contentView, width, height, focusable);
	}

	public NeoBasicPopupWindow(View contentView) {
		super(contentView);
	}

	@SuppressWarnings("deprecation")
	public NeoBasicPopupWindow(View contentView, int width, int height) {
		super(contentView, width, height, true);
		mContentView = contentView;
		setBackgroundDrawable(new BitmapDrawable());
		initViews();
		initEvents();
		init();
	}

	public abstract void initViews();

	public abstract void initEvents();

	public abstract void init();

	public View findViewById(int id) {
		return mContentView.findViewById(id);
	}

	/**
	 * 显示在parent的上部并水平居中
	 * 
	 * @param parent
	 */
	public void showViewTopCenter(View parent) {
		showAtLocation(parent, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	/**
	 * 显示在parent的中心
	 * 
	 * @param parent
	 */
	public void showViewCenter(View parent) {
		showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	/**
	 * 添加确认单击监听
	 * 
	 * @param l
	 */
	public void setOnSubmitClickListener(onSubmitClickListener l) {
		mOnSubmitClickListener = l;
	}

	public interface onSubmitClickListener {
		void onClick();
	}

}
