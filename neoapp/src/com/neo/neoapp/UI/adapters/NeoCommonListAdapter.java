package com.neo.neoapp.UI.adapters;

import java.net.URI;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.neo.neoapp.R;
import com.neo.neoapp.UI.views.NeoBasicTextView;
import com.neo.neoandroidlib.NeoImageUtil;
import com.neo.neoandroidlib.PhotoUtils;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.entity.Entity;
import com.neo.neoapp.entity.People;
import com.neo.neoapp.entity.Setings;


public class NeoCommonListAdapter extends NeoBasicListAdapter {

	public NeoCommonListAdapter(NeoBasicApplication application,
			Context context, List<? extends Entity> datas) {
		super(application, context, datas);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_of_setings, null);
			holder = new ViewHolder();

			holder.mSetingsIv = (ImageView) convertView
					.findViewById(R.id.seting_image);		

			holder.mSetingName = (NeoBasicTextView) convertView
					.findViewById(R.id.seting_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Setings seting = (Setings) getItem(position);
		
		holder.mSetingName.setText(seting.getmName());
		holder.mSetingsIv.setImageBitmap(NeoImageUtil.getImageFromRes(mContext, seting.getmImame())); 
		
		return convertView;
	}

	class ViewHolder {

		ImageView mSetingsIv;
		NeoBasicTextView mSetingName;

	}

}
