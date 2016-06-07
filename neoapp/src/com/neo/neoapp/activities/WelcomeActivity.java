package com.neo.neoapp.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.neo.neoandroidlib.FileUtils;
import com.neo.neoandroidlib.NeoAsyncHttpUtil;
import com.neo.neoandroidlib.NetWorkUtils.NetWorkState;
import com.neo.neoapp.NeoAppSetings;
import com.neo.neoapp.NeoBasicActivity;
import com.neo.neoapp.R;
import com.neo.neoapp.UI.views.NeoBasicTextView;
import com.neo.neoapp.activities.register.RegisterActivity;
import com.neo.neoapp.entity.NeoConfig;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class WelcomeActivity extends NeoBasicActivity implements OnClickListener {

	private String Tag = "WelcomeActivity";
	private LinearLayout mLinearCtrlbar;
	private LinearLayout mLinearAvatars;
	private Button mBtnRegister;
	private Button mBtnLogin;
	private ImageButton mIbtnAbout;

	private View[] mMemberBlocks;
	private String[] mAvatars = new String[] { "welcome_0", "welcome_1",
			"welcome_2", "welcome_3", "welcome_4", "welcome_5" };
	private String[] mDistances = new String[] { "0.84km", "1.02km", "1.34km",
			"1.88km", "2.50km", "2.78km" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initViews();
		initEvents();
		initData();
		//initAvatarsItem();
		showWelcomeAnimation();
	}

	

	@Override
	protected void initViews() {
		mLinearCtrlbar = (LinearLayout) findViewById(R.id.welcome_linear_ctrlbar);
		mLinearAvatars = (LinearLayout) findViewById(R.id.welcome_linear_avatars);
		mBtnRegister = (Button) findViewById(R.id.welcome_btn_register);
		mBtnLogin = (Button) findViewById(R.id.welcome_btn_login);
		mIbtnAbout = (ImageButton) findViewById(R.id.welcome_ibtn_about);
	}

	@Override
	protected void initEvents() {
		mBtnRegister.setOnClickListener(this);
		mBtnLogin.setOnClickListener(this);
		mIbtnAbout.setOnClickListener(this);
	}
	
	private void initData() {
		// TODO Auto-generated method stub
		if (mNetWorkUtils.getConnectState() == NetWorkState.NONE) {
			showAlertDialog("NEO","Please check your NetWork connection!");
		}
		else
		{
			NeoAsyncHttpUtil.get(this,NeoAppSetings.IpServerUrl,
					new JsonHttpResponseHandler(){
				
				@Override
				public void onSuccess(int statusCode, Header[] headers,JSONArray arg0) { // 成功后返回一个JSONArray数据
	                Log.i(Tag, arg0.length() + "");
	                try {
	                    //textView.setText("菜谱名字："
	                    //        + arg0.getJSONObject(2).getString("name")); //返回的是JSONArray， 获取JSONArray数据里面的第2个JSONObject对象，然后获取名字为name的数据值
	                } catch (Exception e) {
	                    Log.e(Tag, e.toString());
	                }
	            };
	            
	            @Override
	            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
	            	Log.e(Tag, " onFailure" + throwable.toString());
	            	showAlertDialog("NEO","Get Server Address failed"+throwable.toString());
	            }
	            
	            @Override 
	            public void onFinish() {
	                Log.i(Tag, "onFinish");
	                //showAlertDialog("NEO","Get Server Address failed");
	            }
	            
	            @Override
	            public void onSuccess(int statusCode, Header[] headers,  
	                    JSONObject response) {  
	                // TODO Auto-generated method stub  
	                super.onSuccess(statusCode, headers, response);  
	                Log.i(Tag, "onSuccess ");
	                try {
	                	showLongToast("ip:"+response.getString(NeoConfig.IP)
	                			+";port:"+response.getString(NeoConfig.PORT));
	                	
	                	mApplication.mNeoConfig = new NeoConfig(response.getString(NeoConfig.IP),
	                			response.getString(NeoConfig.PORT),"neo");
	                	
	                	FileUtils.overrideContent(FileUtils.getAppDataPath(WelcomeActivity.this)+NeoAppSetings.ConfigFile,
	                			response.toString());
	                	
	                	NeoAsyncHttpUtil.addPersistCookieToGlobaList(WelcomeActivity.this);
	                	//showAlertDialog("NEO",NeoAsyncHttpUtil.getCookieText(WelcomeActivity.this));
	                } catch (Exception e) {
	                    Log.e(Tag, e.toString());
	                }
	            }
	            
	            @Override
	            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
	            	Log.e(Tag, " onFailure" + throwable.toString());
	            	showAlertDialog("NEO","Get Server Address failed!\r\n statusCode="
	            	+String.valueOf(statusCode)+"\r\nexception:"
	            	+throwable.toString());
	            }
	            
			});
		}
	}

	private void initAvatarsItem() {
		initMemberBlocks();
		for (int i = 0; i < mMemberBlocks.length; i++) {
			((ImageView) mMemberBlocks[i]
					.findViewById(R.id.welcome_item_iv_avatar))
					.setImageBitmap(mApplication.getAvatar(mAvatars[i]));
			((NeoBasicTextView) mMemberBlocks[i]
					.findViewById(R.id.welcome_item_htv_distance))
					.setText(mDistances[i]);
		}
	}

	private void initMemberBlocks() {
		mMemberBlocks = new View[6];
		mMemberBlocks[0] = findViewById(R.id.welcome_include_member_avatar_block0);
		mMemberBlocks[1] = findViewById(R.id.welcome_include_member_avatar_block1);
		mMemberBlocks[2] = findViewById(R.id.welcome_include_member_avatar_block2);
		mMemberBlocks[3] = findViewById(R.id.welcome_include_member_avatar_block3);
		mMemberBlocks[4] = findViewById(R.id.welcome_include_member_avatar_block4);
		mMemberBlocks[5] = findViewById(R.id.welcome_include_member_avatar_block5);

		int margin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		int widthAndHeight = (mScreenWidth - margin * 12) / 6;
		for (int i = 0; i < mMemberBlocks.length; i++) {
			ViewGroup.LayoutParams params = mMemberBlocks[i].findViewById(
					R.id.welcome_item_iv_avatar).getLayoutParams();
			params.width = widthAndHeight;
			params.height = widthAndHeight;
			mMemberBlocks[i].findViewById(R.id.welcome_item_iv_avatar)
					.setLayoutParams(params);
		}
		mLinearAvatars.invalidate();
	}

	private void showWelcomeAnimation() {
		Animation animation = AnimationUtils.loadAnimation(
				WelcomeActivity.this, R.anim.welcome_ctrlbar_slideup);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				mLinearAvatars.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mLinearAvatars.setVisibility(View.VISIBLE);
					}
				}, 800);
			}
		});
		mLinearCtrlbar.startAnimation(animation);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.welcome_btn_register:
			startActivity(RegisterActivity.class);
			break;

		case R.id.welcome_btn_login:
			startActivity(LoginActivity.class);
			break;

		case R.id.welcome_ibtn_about:
			//startActivity(AboutTabsActivity.class);
			break;
		}
	}
}
