package com.neo.neoapp.activities;

import com.neo.neoapp.NeoBasicActivity;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.R;
import com.neo.neoapp.UI.adapters.NeoAppSettingsListAdapter;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter;
import com.neo.neoapp.UI.views.list.NeoCommonListView;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class NeoAppSettingsActivity extends NeoBasicActivity 
implements OnItemClickListener {

	private NeoCommonListView commonList;
	private NeoAppSettingsListAdapter commonListAdpt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neoapp_settings);
		initViews();
		initEvents();
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int position = (int) arg3;
		int  end = commonListAdpt.getCount()-1;
		//切换张哈
		if (position==end-1){
			
		}
		
		//退出
		if (position==end){
			
		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		commonListAdpt = new NeoAppSettingsListAdapter(mApplication,this);
		commonList = (NeoCommonListView)findViewById(R.id.app_settings_list);
		commonList.setAdapter(commonListAdpt);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}

}
