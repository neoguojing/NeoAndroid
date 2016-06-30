package com.neo.neoapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.neo.neoandroidlib.JsonResolveUtils;
import com.neo.neoapp.R;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.UI.adapters.NeoPeopleListAdapter;
import com.neo.neoapp.UI.views.list.NeoRefreshListView;
import com.neo.neoapp.UI.views.list.NeoRefreshListView.OnCancelListener;
import com.neo.neoapp.UI.views.list.NeoRefreshListView.OnRefreshListener;
import com.neo.neoapp.activities.chat.ChatActivity;
import com.neo.neoapp.entity.People;
import com.neo.neoapp.entity.PeopleProfile;

public class RefreshListFragment extends NeoBasicFragment implements
OnItemClickListener, OnRefreshListener, OnCancelListener{

	private NeoRefreshListView refreshList;
	private NeoPeopleListAdapter peopleListAdpt;
	
	public RefreshListFragment() {
		super();
	}

	public RefreshListFragment(NeoBasicApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_peoples, container,
				false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	protected void initViews() {
		refreshList = (NeoRefreshListView) findViewById(R.id.people_list);
	}

	@Override
	protected void initEvents() {
		refreshList.setOnItemClickListener(this);
		refreshList.setOnRefreshListener(this);
		refreshList.setOnCancelListener(this);
	}

	@Override
	protected void init() {
		getPeoples();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int position = (int) arg3;
		People people = mApplication.mNearByPeoples.get(position);
		//PeopleProfile profile = mApplication..get(position);
		/*String uid = null;
		String name = null;
		String avatar = null;
		if (position > 3) {
			uid = "momo_p_other";
		} else {
			uid = people.getUid();
		}
		name = people.getName();
		avatar = people.getAvatar();
		Intent intent = new Intent(mContext, OtherProfileActivity.class);
		intent.putExtra("uid", uid);
		intent.putExtra("name", name);
		intent.putExtra("avatar", avatar);
		intent.putExtra("entity_people", people);*/
		
		Intent intent = new Intent(mContext, ChatActivity.class);
		intent.putExtra("entity_people", people);
		//intent.putExtra("entity_profile", mProfile);
		startActivity(intent);
	}

	private void getPeoples() {
		
		if (mApplication.mNearByPeoples.isEmpty()) {
			putAsyncTask(new AsyncTask<Void, Void, Boolean>() {

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					showLoadingDialog("正在加载,请稍后...");
				}

				@Override
				protected Boolean doInBackground(Void... params) {
					Boolean rtn = true;
					rtn = JsonResolveUtils.resolveNearbyPeople(mApplication);
					rtn = JsonResolveUtils.resolveMyFriends(mApplication, mContext);
					if (rtn){
						mApplication.mNearByPeoples.addAll(mApplication.mMyFriends);
					}
					rtn = JsonResolveUtils.resolveMyNearbyPeople(mApplication, mContext);
					if (rtn){
						mApplication.mNearByPeoples.addAll(mApplication.mMyNearByPeoples);
					}
					return rtn;
				}

				@Override
				protected void onPostExecute(Boolean result) {
					super.onPostExecute(result);
					dismissLoadingDialog();
					if (!result) {
						showCustomToast("数据加载失败...");
					} 
					peopleListAdpt = new NeoPeopleListAdapter(mApplication,
							mContext, mApplication.mNearByPeoples);
					refreshList.setAdapter(peopleListAdpt);
				}

			});
		} else {
			peopleListAdpt = new NeoPeopleListAdapter(mApplication, mContext,
					mApplication.mNearByPeoples);
			refreshList.setAdapter(peopleListAdpt);
		}
	}

	@Override
	public void onCancel() {
		clearAsyncTask();
		refreshList.onRefreshComplete();
	}

	@Override
	public void onRefresh() {
		putAsyncTask(new AsyncTask<Void, Void, Boolean>() {

			@Override
			protected Boolean doInBackground(Void... params) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				}
				return null;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				refreshList.onRefreshComplete();
			}
		});
	}

	public void onManualRefresh() {
		refreshList.onManualRefresh();
	}
	
}
