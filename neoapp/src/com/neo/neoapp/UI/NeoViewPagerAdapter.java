package com.neo.neoapp.UI;

import java.util.List;

import com.neo.neoapp.fragments.TabFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NeoViewPagerAdapter extends FragmentPagerAdapter {
	
	private List<TabFragment> mList;

	public NeoViewPagerAdapter(FragmentManager fm,List<TabFragment> inlist) {
		super(fm);
		// TODO Auto-generated constructor stub
		mList = inlist;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

}
