package com.neo.neoapp.broadcasts;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class NeoAppBroadCastMessages {
		
	public static void sendBroadCastTestMsg(Context context){
		
		String testIntent = "com.neo.neoapp.broadcasts.sendbroadcasts";
		Intent testBcintent = new Intent(testIntent);
		testBcintent.putExtra("testbc", "Hello broadCast");
		
		context.sendBroadcast(testBcintent);
		
	}
}
