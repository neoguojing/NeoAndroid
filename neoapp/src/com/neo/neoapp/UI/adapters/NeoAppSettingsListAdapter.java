package com.neo.neoapp.UI.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.neo.neoandroidlib.NeoImageUtil;
import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.R;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter.ButtonViewHolder;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter.StringImageViewHolder;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter.StringStringViewHolder;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter.ViewItem;
import com.neo.neoapp.UI.adapters.NeoMeProfileListAdapter.ViewType;
import com.neo.neoapp.UI.views.NeoBasicTextView;
import com.neo.neoapp.entity.People;

public class NeoAppSettingsListAdapter extends NeoBasicListAdapter {
	
	private final int VIEW_TYPE_COUNT = 4;
	private List<ViewItem> viewList = new ArrayList<ViewItem>();
	
	enum ViewType {
		STRING_STRING(0),
		STRING_IMAGE(1),
		STRING_CHECKBOX(2),
		BUTTON(3);
		
		private int id;
		ViewType(int id){
			this.id = id;
		}
		
		public int getId(){
			return this.id;
		}
	}
	
	public NeoAppSettingsListAdapter(NeoBasicApplication application,
			Context context) {
		super(application, context);
		// TODO Auto-generated constructor stub
		initViewList();
	}
	
	private void initViewList(){
		viewList.add(new ViewItem("切换账号","",
				ViewType.BUTTON));
		viewList.add(new ViewItem("退出","",
				ViewType.BUTTON));
		
	}
	
	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public Object getItem(int position) {
		return viewList.get(position);
	}
	
	@Override
	public int getItemViewType(int position) {
	  // TODO Auto-generated method stub
	  return viewList.get(position).type.getId();
	}
	
	public ViewType getViewType(int position) {
		  // TODO Auto-generated method stub
		  return viewList.get(position).type;
		}
	
	@Override
	public int getViewTypeCount() {
	    // TODO Auto-generated method stub
		return VIEW_TYPE_COUNT;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewType type = getViewType(position);
		
		ButtonViewHolder buttonholder = null;
		if (convertView == null) {
			switch(type){
			case STRING_STRING:
				break;
			case STRING_IMAGE:

				break;
			case STRING_CHECKBOX:
				break;
			case BUTTON:
				convertView = mInflater.inflate(R.layout.list_item_of_button, null);
				buttonholder = new ButtonViewHolder(convertView);
				buttonholder.button.setText(viewList.get(position).title);
				buttonholder.button.setBackgroundColor(Color.parseColor("#DC143C"));
				convertView.setTag(buttonholder);
				break;
			default:
				break;
			}

		} else {
			switch(type){
			case STRING_STRING:

				break;
			case STRING_IMAGE:
				break;
			case STRING_CHECKBOX:
				break;
			case BUTTON:
				buttonholder = (ButtonViewHolder) convertView.getTag();
				buttonholder.button.setText(viewList.get(position).title);
				buttonholder.button.setBackgroundColor(Color.parseColor("#DC143C"));
				break;
			default:
				break;
			}
		}
		
		return convertView;
	}
	
	class StringImageViewHolder {

		ImageView image;
		NeoBasicTextView text;
		
		StringImageViewHolder(View view){
			text = (NeoBasicTextView)view.findViewById(R.id.stringimage_name);
			image = (ImageView)view.findViewById(R.id.stringimage_pic);
		}

	}
	
	class StringStringViewHolder {
		
		NeoBasicTextView name;
		NeoBasicTextView value;
		
		StringStringViewHolder(View view){
			name = (NeoBasicTextView)view.findViewById(R.id.string_name);
			value = (NeoBasicTextView)view.findViewById(R.id.string_value);
		}

	}
	
	class ButtonViewHolder {
		
		Button button;
		
		ButtonViewHolder(View view){
			button = (Button)view.findViewById(R.id.list_item_button);
		}

	}
	
	class ViewItem{
		public String title;
		public String value;
		public ViewType type;
		
		ViewItem(String title,String value,ViewType type){
			this.title = title;
			this.value = value;
			this.type = type;
		}
		
		ViewItem(String title,int value,ViewType type){
			this.title = title;
			this.value = String.valueOf(value);
			this.type = type;
		}
	}

}
