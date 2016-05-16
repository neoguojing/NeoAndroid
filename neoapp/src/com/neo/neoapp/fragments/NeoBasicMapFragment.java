package com.neo.neoapp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.neo.neoapp.R;
import com.neo.neoapp.NeoBasicApplication;

public class NeoBasicMapFragment extends NeoBasicFragment{
	
	MapView mMapView = null; 
	
	public NeoBasicMapFragment() {
		super();
	}

	public NeoBasicMapFragment(NeoBasicApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_baidumap, container,
				false);  
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	protected void initViews() {
		mMapView = (MapView) findViewById(R.id.bmapView); 
	}

	@Override
	protected void initEvents() {


	}

	@Override
	protected void init() {

	}

	
	public void onCancel() {
		clearAsyncTask();
		
	}

	@Override
	public void onDestroy() {
		clearAsyncTask();
		mMapView.onDestroy(); 
		super.onDestroy();
	}
	
	@Override
	public void onResume() {  
        super.onResume();   
        mMapView.onResume();  
    }  
    @Override
	public void onPause() {  
        super.onPause();   
        mMapView.onPause();  
    } 
}
