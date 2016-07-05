package com.neo.neoandroidlib;

import java.io.IOException;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.util.Log;

public class NeoImageUtil {
	
	private static String TAG = "NeoImageUtil";
	
	 /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
	public static Bitmap getImageFromSDCard(String name)
	{
		
		String SDCarePath=Environment.getExternalStorageDirectory().toString();
		String filePath=SDCarePath; 
		return BitmapFactory.decodeFile(filePath+"/"+name);
	}
	
	public static Bitmap getImageFromRes(Context ctx,String name)
	{
		ApplicationInfo appInfo = ctx.getApplicationInfo();
		int resID = ctx.getResources().getIdentifier(name, "drawable", appInfo.packageName);
		Log.v(TAG,String.valueOf(resID));
		Log.v(TAG,appInfo.packageName);
		return BitmapFactory.decodeResource(ctx.getResources(), resID);
	}
	
	public static Bitmap getImageFromSrc(Context ctx,String packagePath)
	{
		
		return BitmapFactory.decodeStream(
				ctx.getClassLoader().getResourceAsStream(packagePath));
	}
	
	public static Bitmap getImageFromAsset(Context ctx,String name)
	{
		
		try {
			return BitmapFactory.decodeStream(
					ctx.getResources().getAssets().open(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap compressTheImageToDestSize(String filepath,int destWidth,
			int destHeight){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filepath, options);
		options.inJustDecodeBounds = false;
		options.inSampleSize = caculateInsamplesize(options,destWidth,destHeight);
		Bitmap src = BitmapFactory.decodeFile(filepath, options);
		return createScaledBitMap(src,destWidth,destHeight);
	}

	private static Bitmap createScaledBitMap(Bitmap src, int destWidth,
			int destHeight) {
		// TODO Auto-generated method stub
		Bitmap dest = Bitmap.createScaledBitmap(src,destWidth,destHeight, false);
		if (dest!=src){
			src.recycle();
		}
		return dest;
	}

	private static int caculateInsamplesize(Options options,
			int destWidth, int destHeight) {
		// TODO Auto-generated method stub
		final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > destHeight || width > destWidth ) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > destHeight
                    && (halfWidth / inSampleSize) > destWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
	}
};
