package com.neo.neoandroidlib;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.neo.neoapp.NeoBasicApplication;
import com.neo.neoapp.R;
import com.neo.neoapp.entity.Entity;
import com.neo.neoapp.entity.People;
import com.neo.neoapp.entity.PeopleProfile;
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
		private static final String NearByPeopleProfile = null;

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
		
		public static boolean resolveNearbyProfile(Context context,
				PeopleProfile profile, String uid) {
			if (!android.text.TextUtils.isEmpty(uid)) {
				String json = TextUtils.getJson(context, PROFILE + uid + SUFFIX);
				if (json != null) {
					try {
						JSONObject object = new JSONObject(json);
						String userId = object.getString(PeopleProfile.UID);
						String avatar = object
								.getString(PeopleProfile.AVATAR);
						String name = object.getString(PeopleProfile.NAME);
						int gender = object.getInt(PeopleProfile.GENDER);
						int genderId = -1;
						int genderBgId = -1;
						if (gender == 0) {
							genderId = R.drawable.ic_user_famale;
							genderBgId = R.drawable.bg_gender_famal;
						} else {
							genderId = R.drawable.ic_user_male;
							genderBgId = R.drawable.bg_gender_male;
						}
						int age = object.getInt(PeopleProfile.AGE);
						String constellation = object
								.getString(PeopleProfile.CONSTELLATION);
						String distance = object
								.getString(PeopleProfile.DISTANCE);
						String time = object.getString(PeopleProfile.TIME);

						boolean isHasSign = false;
						String sign = null;
						String signPic = null;
						String signDis = null;

						if (object.has(PeopleProfile.SIGNATURE)) {
							isHasSign = true;
							JSONObject signObject = object
									.getJSONObject(PeopleProfile.SIGNATURE);
							sign = signObject.getString(PeopleProfile.SIGN);
							if (signObject.has(PeopleProfile.SIGN_PIC)) {
								signPic = signObject
										.getString(PeopleProfile.SIGN_PIC);
							}
							signDis = signObject
									.getString(PeopleProfile.SIGN_DIS);
						}

						JSONArray photosArray = object
								.getJSONArray(PeopleProfile.PHOTOS);
						List<String> photos = new ArrayList<String>();
						for (int i = 0; i < photosArray.length(); i++) {
							photos.add(photosArray.getString(i));
						}
						profile.setUid(userId);
						profile.setAvatar(avatar);
						profile.setName(name);
						profile.setGender(gender);
						profile.setGenderId(genderId);
						profile.setGenderBgId(genderBgId);
						profile.setAge(age);
						profile.setConstellation(constellation);
						profile.setDistance(distance);
						profile.setTime(time);
						profile.setHasSign(isHasSign);
						profile.setSign(sign);
						profile.setSignPicture(signPic);
						profile.setSignDistance(signDis);
						profile.setPhotos(photos);
					} catch (JSONException e) {
						e.printStackTrace();
						profile = null;
						return false;
					}
					return true;
				}
			}
			return false;
		}

		/**
		 * 解析附近个人状态
		 * 
		 * @param context
		 * @param feeds
		 * @param uid
		 * @return
		 */
		/*public static boolean resolveNearbyStatus(Context context,
				List<Feed> feeds, String uid) {
			if (!android.text.TextUtils.isEmpty(uid)) {
				String json = TextUtils.getJson(context, STATUS + uid + SUFFIX);
				if (json != null) {
					try {
						JSONArray array = new JSONArray(json);
						Feed feed = null;
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							String time = object.getString(Feed.TIME);
							String content = object.getString(Feed.CONTENT);
							String contentImage = null;
							if (object.has(Feed.CONTENT_IMAGE)) {
								contentImage = object.getString(Feed.CONTENT_IMAGE);
							}
							String site = object.getString(Feed.SITE);
							int commentCount = object.getInt(Feed.COMMENT_COUNT);
							feed = new Feed(time, content, contentImage, site,
									commentCount);
							feeds.add(feed);
						}
					} catch (JSONException e) {
						e.printStackTrace();
						feeds = null;
						return false;
					}
					return true;
				}
			}
			return false;
		}*/

		/**
		 * 解析状态评论
		 * 
		 */
		/*public static boolean resoleFeedComment(Context context,
				List<FeedComment> comments) {
			String json = TextUtils.getJson(context, FEEDCOMMENT);
			if (json != null) {
				try {
					JSONArray array = new JSONArray(json);
					FeedComment comment = null;
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						String name = object.getString(FeedComment.NAME);
						String avatar = object.getString(FeedComment.AVATAR);
						String content = object.getString(FeedComment.CONTENT);
						String time = object.getString(FeedComment.TIME);
						comment = new FeedComment(name, avatar, content, time);
						comments.add(comment);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					comments = null;
					return false;
				}
				return true;
			}
			return false;
		}
	}*/
}
