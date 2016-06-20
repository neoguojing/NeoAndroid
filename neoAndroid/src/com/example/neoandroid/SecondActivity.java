package com.example.neoandroid;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.widget.DatePicker.OnDateChangedListener;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class SecondActivity extends Activity implements 
Button.OnClickListener,Runnable, GridView.OnItemClickListener,Spinner.OnItemSelectedListener{
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		/*setContentView(R.layout.viewtest);
		Button activity_bt = (Button)findViewById(R.id.button_test1);
		activity_bt.setText("This is activity 2");
		activity_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent itent = new Intent();
				itent.setClass(SecondActivity.this, MainActivity.class);
				startActivity(itent);
				SecondActivity.this.finish();
			}
		});
		
		Bundle bd = this.getIntent().getExtras();
		
		String sex = bd.getString("sex");
		double height = bd.getDouble("height");
		String wight;
		
		String setText;
		NumberFormat formatter = new DecimalFormat("0.00");
		if (sex.equals("M")){
			setText = "MAN";
			wight = formatter.format((height-80)*0.7);
		}else{
			setText = "WOMAN";
			wight = formatter.format((height-80)*0.6);
		}
		
		TextView tv = (TextView)findViewById(R.id.textview_test4);
		tv.setText("你是一位" + setText + "\n你的身高是" + height +
"厘米\n你的标准体重是"+ wight + "公斤");
		
		Button back = (Button)findViewById(R.id.button_back);
		back.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){
        
        		SecondActivity.this.setResult(RESULT_OK, SecondActivity.this.getIntent());
        		SecondActivity.this.finish();
        	}
        });*/
		
		//imageViewTest();
		//SpinnerTest();
		//galleryTest();
		//file_search();
		//AutoCompleteTest();
		//ClockTest();
		//DateTimeTest();
		GridViewTest();
	}
	
	
	public class ImageAdapter extends BaseAdapter{

		private Context neocontext;
		private ImageView theimageview;
		private Integer[] imageids = {
			R.drawable.advancedbt,R.drawable.click,R.drawable.error,
			R.drawable.ic_launcher,R.drawable.lost,R.drawable.onfocus,
			R.drawable.right,R.drawable.wrong,R.drawable.advancedbt
		};
		
		public ImageAdapter(Context ct){
			this.neocontext = ct;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageids.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			theimageview = new ImageView(neocontext);
			theimageview.setImageResource(imageids[arg0]);
			theimageview.setAdjustViewBounds(true);
			
			return theimageview;
		}
		
		public Integer getcheckedImageIDPostion(int theindex) {
			return imageids[theindex];
		}
		
		
	}
	
	private GridView mygridview;
	private ImageView grid_imageview;
	private ImageAdapter grid_imageadapter;
	private void GridViewTest(){
		setContentView(R.layout.gridview_layout);
		
		mygridview = (GridView)findViewById(R.id.grid);
		grid_imageview = (ImageView)findViewById(R.id.ImageView_Big);
		
		grid_imageadapter = new ImageAdapter(this);
		mygridview.setAdapter(grid_imageadapter);
		
		mygridview.setOnItemClickListener(this);
		mygridview.setOnItemSelectedListener(new GridView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				grid_imageview.setImageResource(grid_imageadapter.getcheckedImageIDPostion(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	private TextView datetime_tv;
	private DatePicker dp;
	private TimePicker tp;
	int myyear,mymonth,myday,myhour,myminute;
	Calendar mycalendar;
	private DatePickerDialog dpdg;
	private TimePickerDialog tpdg;
	private Button dp_bt;
	private Button tp_bt;
	private OnDateSetListener myDateSetListener=new OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year,
		int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			/*把设置修改后的日期赋值给我的年、月、日变量*/
			myyear=year;
			mymonth=monthOfYear;
			myday=dayOfMonth;
			/*显示设置后的日期*/
			loadDate_Time();
		}
	};
		/*时间改变设置事件监听器*/
	private OnTimeSetListener myTimeSetListener=new OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		/*把设置修改后的时间赋值给我的时、分变量*/
			myhour=hourOfDay;
			myminute=minute;
			/*显示设置后的时间*/
			loadDate_Time();
		}
    };

	private void DateTimeTest(){
		setContentView(R.layout.datatime_layout);
		mycalendar = Calendar.getInstance(Locale.CHINA);
		myyear = mycalendar.get(Calendar.YEAR);
		mymonth = mycalendar.get(Calendar.MONTH);
		myday = mycalendar.get(Calendar.DAY_OF_MONTH);
		myhour = mycalendar.get(Calendar.HOUR_OF_DAY);
		myminute = mycalendar.get(Calendar.MINUTE);
		
		datetime_tv = (TextView)findViewById(R.id.datetime_TextView);
		dp = (DatePicker)findViewById(R.id.my_DatePicker);
		tp = (TimePicker)findViewById(R.id.my_TimePicker);
		dp_bt = (Button)findViewById(R.id.show_DatePicker);
		tp_bt = (Button)findViewById(R.id.show_TimePicker);
		
		dp_bt.setOnClickListener(this);
		tp_bt.setOnClickListener(this);
		
		tp.setIs24HourView(true);
		
		loadDate_Time();
		
		dp.init(myyear, mymonth, myday, new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				myyear = arg1;
				mymonth = arg2;
				myday = arg3;
				loadDate_Time();
			}
			
		});
		
		tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				myhour = arg1;
				myminute = arg2;
				loadDate_Time();
			}
		});
	}
	
	private void loadDate_Time(){
		datetime_tv.setText(new StringBuffer().append(myyear).append("/")
				.append(FormatDate(mymonth+1)).append("/").append(FormatDate(myday)).append(" ").append(FormatDate(myhour))
				.append(":").append(FormatDate(myminute)));
	}
	
	private String FormatDate(int n){
		
		String str =Integer.toString(n);
		if(str.length()==1){
			str = "0"+str;
		}
		return str;
	}
	private TextView clock_tv1;
	private TextView clock_tv2;
	private TextView clock_tv3;
	private TextView clock_tv4;
    AnalogClock analog_clock;
	DigitalClock digital_clock;
	
	public Handler handler;
	public Calendar calender;
	private int neohour,neosecond,neominitu;
	private Thread nthread;	
	private final int msg_key = 0x1234;
	
	private void ClockTest(){
		setContentView(R.layout.clock_layout);
		clock_tv1 = (TextView)findViewById(R.id.TextView_showTime);
		clock_tv2 = (TextView)findViewById(R.id.widget46);
		clock_tv3 = (TextView)findViewById(R.id.widget47);
		clock_tv4 = (TextView)findViewById(R.id.widget48);
		analog_clock = (AnalogClock)findViewById(R.id.Clock);
		digital_clock = (DigitalClock)findViewById(R.id.DigitalClock01);
		
		handler = new Handler(){
			
			@Override
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				switch(msg.what){
				case msg_key:
					clock_tv1.setText(neohour+":"+neominitu+":"+neosecond);
					break;
				
				default:
					break;
				}
			}
		};
		
		nthread = new Thread(this);
	    nthread.start();
	}
	
	private String[] normalString =
			new String[] {
			"Android", "Android Blog","Android Market", "Android SDK",
			"Android AVD","BlackBerry","BlackBerry JDE", "Symbian",
			"Symbian Carbide", "Java 2ME","Java FX", "Java 2EE",
			"Java 2SE", "Mobile", "Motorola", "Nokia", "Sun",
			"Nokia Symbian", "Nokia forum", "WindowsMobile", "Broncho",
			"Windows XP", "Google", "Google Android ", "Google 浏览器",
			"IBM", "MicroSoft", "Java", "C++", "C", "C#", "J#", "VB" };
	private TextView autocomp_tv;
	private Button aotucomp_bt;
	private AutoCompleteTextView autocomp_actv;
	private ArrayAdapter<String> autocomp_adpater;
	private MultiAutoCompleteTextView mutiautocomp_actv;
	private void AutoCompleteTest(){
		setContentView(R.layout.autocomp_layout);
		
		autocomp_tv = (TextView)findViewById(R.id.TextView_InputShow);
		aotucomp_bt = (Button)findViewById(R.id.Button_clean);
		autocomp_actv = (AutoCompleteTextView)findViewById(R.id.AutoCompleteTextView_input);
		mutiautocomp_actv = (MultiAutoCompleteTextView)findViewById(R.id.MultiAutoCompleteTextView);
		
		autocomp_adpater = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,normalString);
		autocomp_actv.setAdapter(autocomp_adpater);
		mutiautocomp_actv.setAdapter(autocomp_adpater);
		mutiautocomp_actv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		aotucomp_bt.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				autocomp_actv.setText("");
			}
			
		});
			
	}
	
	private File file;
	private String path;
	private String info;
	private String key_input;
	private EditText file_et;
	private TextView file_input_tv;	
	private TextView file_result_tv;	
	private Button search_bt;	
	
	private void file_search(){
		setContentView(R.layout.filesearch_layout);
		
		file_et = (EditText)findViewById(R.id.input_KEY_EditText);
		file_input_tv = (TextView)findViewById(R.id.TextView_showIn);
		file_result_tv = (TextView)findViewById(R.id.TextView_Result);
		search_bt = (Button)findViewById(R.id.Button_Search);
		
		search_bt.setOnClickListener(this);
		file = new File(Environment.getRootDirectory().getPath() );
		
		info = "sdcard root directory:";
	}
	
	private void BrowserFile(File f){
		 
		if(key_input.equals("")){
			Toast.makeText(this, "pls input something", Toast.LENGTH_SHORT).show();
		}else{
			ToSearchFile(f);
			
			if(file_result_tv.getText().equals("")){
				Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private void ToSearchFile(File f){
		
		File[] thefiles = f.listFiles();
		
		for(File temp : thefiles){
			if(temp.isDirectory()){
				ToSearchFile(temp);
			}else{
				try{
					if(temp.getName().indexOf(key_input)>-1){
						path += "\n"+temp.getPath();
						file_result_tv.setText(info+path);
					}
				}catch(Exception e){
					Toast.makeText(this, "path err", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
 	private Gallery neog;
	private ImageView imgv;
	private NeoAdapter nadp;
	private void galleryTest(){
		setContentView(R.layout.gallery_layout);
		
		neog = (Gallery)findViewById(R.id.Gallery_preView);
		imgv = (ImageView)findViewById(R.id.ImageView_photo);
		nadp = new NeoAdapter(this);
		neog.setAdapter(nadp);
		
		neog.setOnItemClickListener(new Gallery.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
			long id) {
				// TODO Auto-generated method stub
				/*显示该图片是几号*/
				Toast.makeText(SecondActivity.this,
				"这是图片："+position+"号", Toast.LENGTH_SHORT).show();
				/*设置大图片*/
				imgv.setBackgroundResource(nadp.myImageIds[position]);
				}
			});
	}
	
	private ImageView mImageView01;
	private ImageView mImageView02;
	private Button mButton01;
	private Button mButton02;
	
	private void imageViewTest(){
		setContentView(R.layout.viewtest3);
		
		mImageView01 = (ImageView) findViewById(R.id.myImageView1);
		mImageView02 = (ImageView) findViewById(R.id.myImageView2);
		mButton01 = (Button) findViewById(R.id.myButton1);
		mButton02 = (Button) findViewById(R.id.myButton2);
		
		mImageView01.setBackgroundResource(R.drawable.ic_launcher);
		mImageView02.setBackgroundResource(R.drawable.click);
		
		/* 用OnClickListener 事件来启动*/
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* 当启动后， ImageView 立刻换背景图*/
				mImageView01.setImageDrawable(getResources().getDrawable(
				R.drawable.right));
			}
		});
		mButton02.setOnClickListener(new Button.OnClickListener() {
		@Override
			public void onClick(View v) {
				mImageView01.setImageDrawable(getResources().getDrawable(
				R.drawable.error));
			}
		});
	}
	
	private Spinner neoSpiner;
	private ArrayAdapter<String> aAdpter;
	private static final String[] countriesStr =
		{ "Android", "BlackBerry", "J2ME","Symbian","Broncho", "LinuxMobile",
		"Palm", "WindwosMobile" };
	private TextView spinnerTv;
	private EditText city_ed;
	private Button spinneradd,spinnerdel;
	private String[] cities;
	private String addString;
	private List<String> cityList;
	
	Animation anim;
	private void SpinnerTest(){
		setContentView(R.layout.spinner_layout);
		
		neoSpiner = (Spinner)findViewById(R.id.Spinner_City);
		spinnerTv = (TextView)findViewById(R.id.Spinner_Show);
		anim = AnimationUtils.loadAnimation(this, R.anim.spinner);
		city_ed = (EditText)findViewById(R.id.EditView_Input);
		spinneradd = (Button)findViewById(R.id.Button_ADD);
		spinnerdel = (Button)findViewById(R.id.Button_DEL);
		
		cityList = new ArrayList<String>();
		for (int i=0;i<countriesStr.length;i++){
			cityList.add(countriesStr[i]);
		}
		
		aAdpter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cityList);
		aAdpter.setDropDownViewResource(R.layout.spinner_dropdown);
		
		neoSpiner.setAdapter(aAdpter);
		spinnerTv.setText("your choice is"+aAdpter.getItem(0));
		neoSpiner.setSelection(0);
		
		spinneradd.setOnClickListener(this);
		spinnerdel.setOnClickListener(this);
		
		neoSpiner.setOnItemSelectedListener(this);
        		
		/*neoSpiner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				spinnerTv.setText("your choice is"+countriesStr[arg2]);
				//arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				spinnerTv.setText("your choice is none");
			}
			
		});*/
		
		neoSpiner.setOnTouchListener(new Spinner.OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				arg0.startAnimation(anim);
				//arg0.setVisibility(View.INVISIBLE);
				return false;
			}
			
		});
		
		neoSpiner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.show_DatePicker:
			dpdg = new DatePickerDialog(SecondActivity.this,myDateSetListener,myyear,mymonth,myday);
			dpdg.show();
			break;
		case R.id.show_TimePicker:
			tpdg = new TimePickerDialog(SecondActivity.this,myTimeSetListener,myhour,myminute,false);
			tpdg.show();
			break;
		case R.id.Button_ADD:
			Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
			addString = city_ed.getText().toString();
			for(int i=0;i<aAdpter.getCount();i++){
				if(addString.equals(aAdpter.getItem(i))){
					return;
				}
			}
			
			if(!addString.equals("")){
				aAdpter.add(addString);
				int pos = aAdpter.getPosition(addString);
				neoSpiner.setSelection(pos);
			}
			city_ed.setText("");
			break;
		case R.id.Button_DEL:
			if(neoSpiner.getSelectedItem()!=null){
				aAdpter.remove(neoSpiner.getSelectedItem().toString());
				city_ed.setText("");
				
				if(aAdpter.getCount()==0){
					Toast.makeText(this, "all clear", Toast.LENGTH_SHORT).show();
					spinnerTv.setText("none");
				}
			}
			break;
		//for file search
		case R.id.Button_Search:
			path = "";
			file_result_tv.setText("");
			key_input = file_et.getText().toString();
			
			BrowserFile(file);
			break;
		}
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			spinnerTv.setText("your choice is"+aAdpter.getItem(arg2));		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		spinnerTv.setText("your choice is none");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Exit");
		return super.onCreateOptionsMenu(menu);
	} /*Menu菜
	单
	退
	出
	*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		finish();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void run() {
		try {
		do{
			long time = System.currentTimeMillis();
			calender = Calendar.getInstance();
			neohour = calender.get(Calendar.HOUR);
			neominitu =  calender.get(Calendar.MINUTE);
			neosecond = calender.get(Calendar.SECOND);
			Thread.sleep(1000);
			
			Message msg = new Message();
			msg.what = msg_key;
			handler.sendMessage(msg);
			
		}while(nthread.interrupted()==false);
		
		}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Integer viewid = arg1.getId();
		Integer gridid = R.id.grid;
	    Log.v("neo viewid",viewid.toString());
	    Log.v("neo gridid",gridid.toString());

		new AlertDialog.Builder(this)
		.setTitle("view picture").setIcon(grid_imageadapter.getcheckedImageIDPostion(arg2))
		.setPositiveButton(R.string.neo_back, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
		}).show();

	}
	
}
