package com.neo.neoapp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.neo.neoapp.R;
import com.neo.neoandroidlib.NeoIntentFactiory;
import com.neo.neoapp.UI.ChangeColorIconWithTextView;
import com.neo.neoapp.UI.NeoViewPagerAdapter;
import com.neo.neoapp.activities.DBOprActivity;
import com.neo.neoapp.broadcasts.NeoAppBroadCastMessages;
import com.neo.neoapp.fragments.NeoBasicFragment;
import com.neo.neoapp.fragments.NeoDisPlayFragment;
import com.neo.neoapp.fragments.NeoSetingListFragment;
import com.neo.neoapp.fragments.RefreshListFragment;
import com.neo.neoapp.fragments.TabFragment;
import com.neo.neoapp.handlers.NeoAppUIThreadHandler;
import com.neo.neoapp.handlers.NeoAppWorkerThreadHandler;
import com.neo.neoapp.services.NeoAppBackgroundService;
import com.neo.neoapp.tasks.ServiceWorkerWithLooper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;

public class MainActivity extends NeoBasicActivity implements OnClickListener,
	OnPageChangeListener{
	
	private NeoBasicApplication mApplication;
	
	private static final String TAG = "MainActivity";
	private int counter = 1;
	
	private ServiceWorkerWithLooper mtMainActivityWorker;
	private NeoAppWorkerThreadHandler mWorkerThreadHandler;
	
	//for vies
	private ViewPager mViewPager;
	private NeoViewPagerAdapter mfPagerAdapter;
	private List<NeoBasicFragment> mListFragments = new ArrayList<NeoBasicFragment>();
	//private List<RefreshListFragment> mListFragments = new ArrayList<RefreshListFragment>();
	
	private List<ChangeColorIconWithTextView> mListViews = 
			new ArrayList<ChangeColorIconWithTextView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mApplication = (NeoBasicApplication) getApplication();
		//����������
		setOverflowButtonAlways();
		getActionBar().setDisplayShowHomeEnabled(false);
		
		//��ʼ������
		initViews();
		initService();
		initWorkerThread();
		//sendNotifications();
			
	}
	
	public void onDestroy(){
		
		stopService();
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
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
			if (menu.getClass().getSimpleName().equals("MenuBuilder")){
				try {
					//利用反射
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible",Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return super.onMenuOpened(featureId, menu);
	}
	
	protected void initViews(){
		
		mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
		
		ChangeColorIconWithTextView one=(ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
		ChangeColorIconWithTextView two=(ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
		ChangeColorIconWithTextView three=(ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
		ChangeColorIconWithTextView four=(ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);
	
		mListViews.add(one);
		mListViews.add(two);
		mListViews.add(three);
		mListViews.add(four);
		
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		one.setIconAlpha(1.0f);
		
		initDatas();
		initEvents();
	}
	
	private void initDatas(){
		
		String [] titles=new String[]{"Fourth Fragment!"};
		
		RefreshListFragment refreshList = 
				new RefreshListFragment(mApplication,this,this);
		mListFragments.add(refreshList);
		
		NeoDisPlayFragment displayView = 
				new NeoDisPlayFragment(mApplication,this,this);
		mListFragments.add(displayView);
		
		NeoSetingListFragment setingList = 
				new NeoSetingListFragment(mApplication,this,this);
		mListFragments.add(setingList);
		
		for(String title : titles){
			
			TabFragment tabFragment = new TabFragment();
			Bundle bd = new Bundle();
			bd.putString(TabFragment.TITLE,title);
			tabFragment.setArguments(bd);
			mListFragments.add(tabFragment);
		}
		
		mfPagerAdapter = new NeoViewPagerAdapter(this.getSupportFragmentManager()
				,mListFragments);
		mViewPager.setAdapter(mfPagerAdapter);
		
	}
	
	@SuppressWarnings("deprecation")
	protected void initEvents(){
		//mViewPager.setOnPageChangeListener(this);
		mViewPager.addOnPageChangeListener(this);
	}
	
	private void initService(){
		//�������ط���
		Intent bgsvc = new Intent(MainActivity.this,
				NeoAppBackgroundService.class);
		bgsvc.putExtra("counter", counter++);
		startService(bgsvc);
	}
	
	private void stopService(){
		Intent bgsvc = new Intent(MainActivity.this,
				NeoAppBackgroundService.class);
		stopService(bgsvc);
	}
	
	private void initWorkerThread(){
		//�����������߳�
		mtMainActivityWorker = new ServiceWorkerWithLooper("MainActivity Worker thread",
				MainActivity.this);
		mWorkerThreadHandler = new NeoAppWorkerThreadHandler
				(mtMainActivityWorker.getLooper());
		mWorkerThreadHandler.sendMessage(1);
	}
	
	private void sendNotifications(){
		//send notifiation
		NeoAppBroadCastMessages.sendBroadCastTestMsg(this);
	}
	
	private void setOverflowButtonAlways(){
		try {
			ViewConfiguration viewconfig=ViewConfiguration.get(this);
			//利用反射
			Field menuKey=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(viewconfig, false);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		onTabClick(view);
	}
	
	private void onTabClick(View view){
		resetOthersTab();
		
		switch(view.getId()){
		case R.id.id_indicator_one:
			mListViews.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0,false);
			break;
		case R.id.id_indicator_two:
			mListViews.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1,false);
			break;
		case R.id.id_indicator_three:
			mListViews.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2,false);
			break;
		case R.id.id_indicator_four:
			mListViews.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3,false);
			break;
		}
	}

	private void resetOthersTab() {
		// TODO Auto-generated method stub
		for (int i=0;i<mListViews.size();i++){
			mListViews.get(i).setIconAlpha(0.0f);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		if(positionOffset>0){
			ChangeColorIconWithTextView left = mListViews.get(position);
			ChangeColorIconWithTextView right = mListViews.get(position+1);
			left.setIconAlpha(1-positionOffset);
			right.setIconAlpha(positionOffset);
		}
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
