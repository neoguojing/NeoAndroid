package com.neo.neoandroidlib;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.entity.Entity;
import com.neo.neoapp.entity.People;
import com.neo.neoapp.entity.Setings;

public class JsonResolveUtils {
	// 附近个人的json文件名称
		private static final String NEARBY_PEOPLE = "nearby_people.json";
		// 附近个人的json文件名称
		private static final String NEARBY_GROUP = "nearby_group.json";
		private static final String SETINGS = "setings.json";
		// 用户资料文件夹
		private static final String PROFILE = "profile/";
		// 用户状态文件夹
		private static final String STATUS = "status/";
		// 后缀名
		private static final String SUFFIX = ".json";
		// 状态评论
		private static final String FEEDCOMMENT = "feedcomment.json";
		
		private static final String TAG = "JsonResolveUtils";

		/**
		 * 解析附近个人Json数据
		 * 
		 * @param application
		 * @return
		 */
		public static boolean resolveNearbyPeople(NeoBasicApplication application) {
			if (application.mNearByPeoples != null
					&& application.mNearByPeoples.isEmpty()) {
				String json = TextUtils.getJson(
						application.getApplicationContext(), NEARBY_PEOPLE);
				if (json != null) {
					try {
						JSONArray array = new JSONArray(json);
						People people = null;
						JSONObject object = null;
						for (int i = 0; i < array.length(); i++) {
							object = array.getJSONObject(i);
							String uid = object.getString(People.UID);
							String avatar = object.getString(People.AVATAR);
							int isVip = object.getInt(People.VIP);
							int isGroupRole = object
									.getInt(People.GROUP_ROLE);
							String industry = object
									.getString(People.INDUSTRY);
							int isbindWeibo = object.getInt(People.WEIBO);
							int isbindTxWeibo = object
									.getInt(People.TX_WEIBO);
							int isbindRenRen = object.getInt(People.RENREN);
							int device = object.getInt(People.DEVICE);
							int isRelation = object.getInt(People.RELATION);
							int isMultipic = object.getInt(People.MULTIPIC);
							String name = object.getString(People.NAME);
							int gender = object.getInt(People.GENDER);
							int age = object.getInt(People.AGE);
							String distance = object
									.getString(People.DISTANCE);
							String time = object.getString(People.TIME);
							String sign = object.getString(People.SIGN);

							people = new People(uid, avatar, isVip,
									isGroupRole, industry, isbindWeibo,
									isbindTxWeibo, isbindRenRen, device,
									isRelation, isMultipic, name, gender, age,
									distance, time, sign);
							application.mNearByPeoples.add(people);
						}
					} catch (JSONException e) {
						application.mNearByPeoples.clear();
					}
				}
			}
			if (application.mNearByPeoples.isEmpty()) {
				return false;
			} else {
				return true;
			}
		}
		
		public static boolean resolveSetings(NeoBasicApplication application,List<Setings> datas) {
			
			if (datas != null && datas.isEmpty())
			{
				String json = TextUtils.getJson(
						application.getApplicationContext(), SETINGS);
				if (json != null) {
					try {
						JSONArray array = new JSONArray(json);
						Setings seting = null;
						JSONObject object = null;
						for (int i = 0; i < array.length(); i++) {
							object = array.getJSONObject(i);
							String uid = object.getString(Setings.UID);
							String name = object.getString(Setings.NAME);
							String image = object.getString(Setings.IMAGE);
							
							seting = new Setings(uid, image, name);
							datas.add(seting);
						}
					} catch (JSONException e) {
						e.printStackTrace();
						datas.clear();
					}
				}
			}
			if (datas.isEmpty()) {
				return false;
			} else {
				return true;
			}
		}
}
