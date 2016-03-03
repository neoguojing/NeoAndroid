package com.neo.neoandroidlib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class NeoIntentFactiory {
	
	public static void getWebBrowser(Activity activity){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com"));
		activity.startActivity(intent);
	}
	public static void getWebSearch(Activity activity){
		Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		intent.setData(Uri.parse("http://www.baidu.com"));
		activity.startActivity(intent);
	}
	public static void getDial(Activity activity){
		Intent intent = new Intent(Intent.ACTION_DIAL);
		activity.startActivity(intent);
	}
	public static void getMap(Activity activity){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("geo:0,0?z=4&q=business+near+city"));
		activity.startActivity(intent);
	}

}
