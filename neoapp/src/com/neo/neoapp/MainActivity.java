package com.neo.neoapp;

import com.neo.neoandroidlib.NeoIntentFactiory;
import com.neo.neoapp.activities.DBOprActivity;
import com.neo.neoapp.broadcasts.NeoAppBroadCastMessages;
import com.neo.neoapp.handlers.NeoAppUIThreadHandler;
import com.neo.neoapp.handlers.NeoAppWorkerThreadHandler;
import com.neo.neoapp.services.NeoAppBackgroundService;
import com.neo.neoapp.tasks.ServiceWorkerWithLooper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private int counter = 1;
	
	private ServiceWorkerWithLooper mtMainActivityWorker;
	private NeoAppWorkerThreadHandler mWorkerThreadHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//启动本地服务
		Intent bgsvc = new Intent(MainActivity.this,
				NeoAppBackgroundService.class);
		bgsvc.putExtra("counter", counter++);
		startService(bgsvc);
		
		//启动工作者线程
		mtMainActivityWorker = new ServiceWorkerWithLooper("MainActivity Worker thread",
				MainActivity.this);
		mWorkerThreadHandler = new NeoAppWorkerThreadHandler
				(mtMainActivityWorker.getLooper());
		mWorkerThreadHandler.sendMessage(1);
		
		//send notifiation
		//NeoAppBroadCastMessages.sendBroadCastTestMsg(this);
		
	}
	
	public void onDestroy(){
		
		Intent bgsvc = new Intent(MainActivity.this,
				NeoAppBackgroundService.class);
		stopService(bgsvc);
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.action_settings:
			return true;
		case R.id.menu_webbrowser:
			NeoIntentFactiory.getWebBrowser(this);
			return true;
		case R.id.menu_websearch:
			NeoIntentFactiory.getWebSearch(this);
			return true;
		case R.id.menu_dial:
			NeoIntentFactiory.getDial(this);
			return true;
		case R.id.menu_map:
			NeoIntentFactiory.getMap(this);
			return true;
		case R.id.menu_db:
			Intent db_activity = new Intent();
			db_activity.setClass(MainActivity.this, DBOprActivity.class);
			startActivity(db_activity);
			MainActivity.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	
		
	}
}
