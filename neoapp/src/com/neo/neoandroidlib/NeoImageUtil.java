package com.neo.neoandroidlib;

import java.io.IOException;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class NeoImageUtil {
	
	private static String TAG = "NeoImageUtil";
	
	public static Bitmap getImageFromSDCard(String name)
	{
		String SDCarePath=Environment.getExternalStorageDirectory().toString();
		String filePath=SDCarePath+"/"+"haha.jpg"; 
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
		
		return BitmapFactory.decodeStream(ctx.getClassLoader().getResourceAsStream(packagePath));
	}
	
	public static Bitmap getImageFromAsset(Context ctx,String name)
	{
		
		try {
			return BitmapFactory.decodeStream(ctx.getResources().getAssets().open(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
};
