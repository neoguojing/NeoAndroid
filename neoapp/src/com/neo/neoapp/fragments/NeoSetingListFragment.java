package com.neo.neoapp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.neo.neoandroidlib.JsonResolveUtils;
import com.neo.neoapp.R;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.UI.adapters.NeoCommonListAdapter;
import com.neo.neoapp.UI.views.list.NeoCommonListView;
import com.neo.neoapp.entity.People;
import com.neo.neoapp.entity.Setings;

public class NeoSetingListFragment extends NeoBasicFragment implements
OnItemClickListener{

	private NeoCommonListView commonList;
	private NeoCommonListAdapter commonListAdpt;
    private ImageView headpic;
	public static List<Setings> mSetingList = new ArrayList<Setings>();
	
	public NeoSetingListFragment() {
		super();
	}

	public NeoSetingListFragment(NeoBasicApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_setings, container,
				false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	protected void initViews() {
		commonList  = (NeoCommonListView) findViewById(R.id.setings_list);
        this.headpic = (ImageView) findViewById(R.id.userheader);
        this.headpic.setImageBitmap(BitmapFactory.decodeFile(
        		mApplication.mAppDataPath+mApplication.mMe.getName()));
	}

	@Override
	protected void initEvents() {
		commonList .setOnItemClickListener(this);

	}

	@Override
	protected void init() {
		getListItems();	
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int position = (int) arg3;
		People people = mApplication.mNearByPeoples.get(position);
		String uid = null;
		String name = null;
		String avatar = null;
		if (position > 3) {
			uid = "momo_p_other";
		} else {
			uid = people.getUid();
		}
		name = people.getName();
		avatar = people.getAvatar();
		/*Intent intent = new Intent(mContext, OtherProfileActivity.class);
		intent.putExtra("uid", uid);
		intent.putExtra("name", name);
		intent.putExtra("avatar", avatar);
		intent.putExtra("entity_people", people);
		startActivity(intent);8*/
	}

	private void getListItems() {
		
		putAsyncTask(new AsyncTask<Void, Void, Boolean>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				//showLoadingDialog("正在加载,请稍后...");
			}

			@Override
			protected Boolean doInBackground(Void... params) {
				return JsonResolveUtils.resolveSetings(mApplication, mSetingList);
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				//dismissLoadingDialog();
				if (!result) {
					showCustomToast("数据加载失败...");
				} else {
					commonListAdpt = new NeoCommonListAdapter(mApplication,
							mContext, mSetingList);
					commonList.setAdapter(commonListAdpt);
				}
			}

		});
		
		

	}

	public void onCancel() {
		clearAsyncTask();
	}

	public void onRefresh() {
		
	}

	public void onManualRefresh() {

	}
	
}
