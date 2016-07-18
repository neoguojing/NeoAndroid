package com.neo.neoapp.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.neo.neoandroidlib.FileUtils;
import com.neo.neoandroidlib.NeoAsyncHttpUtil;
import com.neo.neoapp.NeoAppSetings;
import com.neo.neoapp.NeoAppSetings.NEO_ERRCODE;
import com.neo.neoapp.NeoBasicActivity;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.R;
import com.neo.neoapp.UI.adapters.NeoAppSettingsListAdapter;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter;
import com.neo.neoapp.UI.views.list.NeoCommonListView;
import com.neo.neoapp.entity.People;

import cz.msebera.android.httpclient.Header;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class NeoAppSettingsActivity extends NeoBasicActivity 
implements OnItemClickListener {
	private final String Tag = "NeoAppSettingsActivity";
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
			doLogout();
		}
	}
	
	private void doLogout(){
		 NeoAsyncHttpUtil.get(this,NeoAppSetings.getLogOutUrl(mApplication.mNeoConfig),
	         						new JsonHttpResponseHandler() {
	    		 @Override
	             public void onFinish() {
	                 Log.i(NeoAppSettingsActivity.this.Tag, "onFinish");
	             }
	             @Override
	             public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
	                 super.onSuccess(statusCode, headers, response);
	                 Log.i(NeoAppSettingsActivity.this.Tag, new StringBuilder(String.valueOf(response.length())).toString());
	                 
	                 NeoAsyncHttpUtil.addPersistCookieToGlobaList(NeoAppSettingsActivity.this);

	             }
	             @Override
	             public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
	                 Log.e(NeoAppSettingsActivity.this.Tag, " onFailure" + throwable.toString());
	                 NeoAppSettingsActivity.this.showAlertDialog("NEO", throwable.toString());
	             }
	             @Override
	             public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
	                 super.onSuccess(statusCode, headers, response);
	                 Log.i(NeoAppSettingsActivity.this.Tag, "onSuccess ");
	                 NeoAsyncHttpUtil.addPersistCookieToGlobaList(NeoAppSettingsActivity.this);
	                 if (response.has("errcode")){
	 					try {
	 						if (response.getString("errcode").equals(
	 								NEO_ERRCODE.UERE_LOGOUT.toString())){
	 							NeoAppSettingsActivity.this.startActivity(new Intent(NeoAppSettingsActivity.this, LoginActivity.class));
	 							NeoAppSettingsActivity.this.finish();
	 						}
	 					} catch (JSONException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	                 }
	             }
	             @Override
	             public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
	            	 NeoAppSettingsActivity.this.showAlertDialog("NEO", throwable.toString());
	             }
	         });
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
