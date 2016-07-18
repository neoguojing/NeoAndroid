package com.neo.neoapp.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.neo.neoapp.NeoBasicActivity;
import com.neo.neoapp.R;
import com.neo.neoapp.UI.adapters.NeoMeProfileEditListAdapter;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter;
import com.neo.neoapp.UI.views.list.NeoCommonListView;

public class NeoMeProfileEditActivity extends NeoBasicActivity 
	implements OnItemClickListener{
	private NeoCommonListView commonList;
	private NeoMeProfileEditListAdapter commonListAdpt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_profile_edit);
		initViews();
		initEvents();
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		commonListAdpt = new NeoMeProfileEditListAdapter(mApplication,this);
		commonList = (NeoCommonListView)findViewById(R.id.me_profile_edit_list);
		commonList.setAdapter(commonListAdpt);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int position = (int) arg3;
		int  end = commonListAdpt.getCount()-1;
		
		if (position==end){
			
		}
		
	}

}
