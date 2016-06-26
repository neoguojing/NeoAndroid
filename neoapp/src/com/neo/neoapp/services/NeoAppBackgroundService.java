package com.neo.neoapp.services;

import com.neo.neoapp.broadcasts.NeoAppBroadCastMessages;
import com.neo.neoapp.handlers.NeoAppUIThreadHandler;
import com.neo.neoapp.socket.server.NeoAyncSocketServer;
import com.neo.neoapp.tasks.ServicieWorker;

import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class NeoAppBackgroundService extends Service {
	
	private static final String TAG = "NeoAppBackgroundService";
	private NotificationManager notifyMgr;
	//private ThreadGroup mThreadGroup = new ThreadGroup("ServiceWorker");
	private NeoAyncSocketServer aServer = null;
	private NeoAppUIThreadHandler mUIHandler = null;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		Log.v(TAG, "in onCreate");
		notifyMgr = (NotificationManager)getSystemService(
				NOTIFICATION_SERVICE);
		
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId){
		super.onStartCommand(intent, flags, startId);
		
		//int counter = intent.getExtras().getInt("counter");
		//Log.v(TAG,"in onStartCommand,counter = " + counter +
		//		",startId = "+ startId);
		//new Thread(mThreadGroup,new ServicieWorker (counter),
				//		TAG).start();
		displayNotifyMsg("WelcomeActivity.class",TAG+" is running");
		aServer = new NeoAyncSocketServer(this);
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		Log.v(TAG,"in onDestroy");
		
		//mThreadGroup.interrupt(); 
		aServer.close();
		notifyMgr.cancelAll();
		super.onDestroy();
	}
	
	
	private void displayNotifyMsg(String clsStr, String message) {
		// TODO Auto-generated method stub
		NeoAppBroadCastMessages.sendStaticBroadCastTestMsg(this,clsStr,message);
	}
}
