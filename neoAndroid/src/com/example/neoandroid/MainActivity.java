package com.example.neoandroid;

import java.util.Random;

import com.example.neoandroid.R.id;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;



public class MainActivity extends Activity implements Button.OnClickListener {
	
	//example1
	private EditText nameET;
	private Button loginBT;
	private TextView showTV;
	
	//linerlayout and color test
	private LinearLayout lltest;
	private LinearLayout.LayoutParams llp;
	private int wc = LinearLayout.LayoutParams.WRAP_CONTENT;
	private TextView red, yellow, blue;
	private TextView editshow;
	private EditText ettest3;
	
	private ImageButton imgbt;
	private Button genbtforimg;
	
	private EditText et_tost;
	private Button bt_tost;
	
	private CheckBox cb_mp4;
	private CheckBox cb_cd;
	private CheckBox cb_book;
	private TextView cb_show;
	
	private RadioButton rboy;
	private RadioButton rgirl;
	private Button a_bt;
	private Button c_bt;
	private RadioGroup rg;
	private AlertDialog.Builder showrw;
	private MenuItem m_exit;
	private String[] boy_girl;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
        getFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment())
                .commit();
    	}*/
        
        //状态栏 标题栏
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        this.getWindow().setFlags(flag, flag);
        //example1
        setContentView(R.layout.activity_main);
        nameET = (EditText)findViewById(R.id.widget29_getName_EditText);
        loginBT = (Button)findViewById(R.id.widget30_button_OK);
        showTV = (TextView)findViewById(R.id.widget31_show_TextView);
        loginBT.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		showTV.setText(nameET.getText()+"Welcome!");
        	}
        });*/
        
        //text view
        /*setContentView(R.layout.viewtest);
        TextView tv = new TextView(this);
        String stext = "text view moudule";
        tv.setText(stext);
        TextView tv1 = (TextView)findViewById(R.id.textview_test);
        //setContentView(tv);*/
        
        //linerlayout and color test
        /*lltest = new LinearLayout(this);
        lltest.setOrientation(LinearLayout.VERTICAL);
        lltest.setBackgroundColor(Color.BLACK);
        setContentView(lltest);
        
        llp = new LinearLayout.LayoutParams(wc,wc);
        
        red = new TextView(this);
        yellow = new TextView(this);
        blue = new TextView(this);
        
        lltest.addView(red);
        lltest.addView(yellow);
        lltest.addView(blue);
        
        red.setTextColor(Color.RED);
        yellow.setTextColor(Color.YELLOW);
        blue.setTextColor(Color.BLUE);
        
        red.setText("red");
        yellow.setText("yellow");
        CharSequence cs1 = getString(R.string.textview_str);
        String str1 = "CharSequence example:";
        blue.setText(str1+cs1);
        
        red.setTextSize(30.0f);
        yellow.setTextSize(30.0f);
        blue.setTextSize(30.0f);
        
        red.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        yellow.setTypeface(Typeface.SERIF, Typeface.ITALIC);
        blue.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
        
        //获得屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        String str2 = "moble windows width,height:\n"+dm.widthPixels+"*"+dm.heightPixels;
        yellow.setText(str2);*/
        
        //edittext resource
        /*setContentView(R.layout.viewtest);
        EditText ettest1 = (EditText)findViewById(R.id.edittext_test1);
        EditText ettest2 = (EditText)findViewById(R.id.edittext_test2);
        editshow = (TextView)findViewById(R.id.textview_editview);
        ettest3 = (EditText)findViewById(R.id.edittext_test3);
        
        Resources rc = getBaseContext().getResources();
        Drawable color_m1 = rc.getDrawable(R.color.lightgreen);
        Drawable color_m2 = rc.getDrawable(R.color.darkgray);
        
        ettest1.setBackground(color_m1);
        ettest2.setBackground(color_m2);
        ettest3.setOnKeyListener(new EditText.OnKeyListener(){

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				editshow.setText(ettest3.getText().toString());
				return false;
			}
        });*/
        
        //layout switch test
        /*setContentView(R.layout.activity_main);
        Button layout1_bt = (Button)findViewById(R.id.button_test2);
        layout1_bt.setOnClickListener(new OnClickListener(){
        				public void onClick(View v)
        				{
        					switchToLayout2();
        				}
        			}
        		);*/
        
        //activity switch tset
        setContentView(R.layout.activity_main);
		Button activity_bt = (Button)findViewById(R.id.button_test2);
		activity_bt.setText("This is activity 1");
		activity_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent itent = new Intent();
				itent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(itent);
				MainActivity.this.finish();
			}
		});
        
        //radio and bundle between acticities
        /*setContentView(R.layout.activity_main);
        
        Button ok = (Button)findViewById(R.id.button_OK);
        ok.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){
        
		        EditText et = (EditText)findViewById(R.id.height_Edit);
		        double height = Double.parseDouble(et.getText().toString());
		        
		        String sex;
		        RadioButton rbt = (RadioButton)findViewById(R.id.Sex_Man);
		        if(rbt.isChecked()){
		        	sex = "M";
		        }else{
		        	sex = "F";
		        }
		        
		        Intent itent = new Intent();
		        itent.setClass(MainActivity.this, SecondActivity.class);
		        
		        Bundle bd = new Bundle();
		        bd.putDouble("height", height);
		        bd.putString("sex", sex);
		        
		        itent.putExtras(bd);
		        
		        //startActivity(itent);
		        startActivityForResult(itent,1550);
        	}
        });*/
        
        //edittext resource
        /*setContentView(R.layout.viewtest);
        
        imgbt = (ImageButton)findViewById(R.id.image_Button);
        genbtforimg = (Button)findViewById(R.id.imagebt_bt);
        
        imgbt.setOnFocusChangeListener(new ImageButton.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean isFocused) {
				// TODO Auto-generated method stub
				
				if(isFocused == true){
					imgbt.setImageResource(R.drawable.onfocus);
				}else{
					imgbt.setImageResource(R.drawable.lost);
				}
			}
        });
        
        imgbt.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgbt.setImageResource(R.drawable.click);
			}
        	
        });
        
        genbtforimg.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgbt.setImageResource(R.drawable.lost);
			}
        	
        });*/
        
        //toast
        /*setContentView(R.layout.viewtest);
        et_tost = (EditText)findViewById(R.id.EditText_Wish);
        bt_tost = (Button)findViewById(R.id.Button_Send);
        
        bt_tost.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Editable Str = et_tost.getText();
				
				Toast.makeText(MainActivity.this, "youor wish:"+Str.toString(),Toast.LENGTH_SHORT).show();
				et_tost.setText("");
			}
        	
        });
        
        Toast tost = new Toast(this);
        ImageView imgv = new ImageView(this);
        imgv.setImageResource(R.drawable.onfocus);
        
        tost.setView(imgv);
        tost.setDuration(Toast.LENGTH_LONG);
        tost.show();*/
        
        //Alert Dialog
        /*setContentView(R.layout.viewtest);
        AlertDialog.Builder mydialog = new AlertDialog.Builder(this);
        
        mydialog.setTitle("alert test");
        mydialog.setMessage("hello world");
        mydialog.show();*/
        
        //checkbox
        /*setContentView(R.layout.viewtest2);
        cb_mp4 = (CheckBox)findViewById(R.id.CheckBox_MP4);
        cb_book = (CheckBox)findViewById(R.id.CheckBox_book);
        cb_cd = (CheckBox)findViewById(R.id.CheckBox_musicCD);
        cb_show = (TextView)findViewById(R.id.tv_checkbox);
        
        cb_mp4.setOnCheckedChangeListener(checkBoxLisner);
        cb_book.setOnCheckedChangeListener(checkBoxLisner);
        cb_cd.setOnCheckedChangeListener(checkBoxLisner);*/
        
        //radio
        /*setContentView(R.layout.viewtest2);
        showrw = new AlertDialog.Builder(this);
        
        rboy = (RadioButton)findViewById(R.id.RadioButton_Boy);
        rgirl = (RadioButton)findViewById(R.id.RadioButton_Gril);
        a_bt = (Button)findViewById(R.id.answer_The_Q_button);
        c_bt = (Button)findViewById(R.id.clean_The_Q_button);
        cb_show = (TextView)findViewById(R.id.tv_checkbox);
        rg = (RadioGroup)findViewById(R.id.RadioGroup);
        boy_girl = new String[] { "Boy", "Girl","Boy", "Girl",
        		"Boy", "Girl","Boy", "Girl",
        		"Boy" };
        
        a_bt.setEnabled(false);
        c_bt.setEnabled(false);
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(rboy.isChecked()){
					cb_show.setText("boy");
				}else{
					cb_show.setText("girl");
				}
			}
        	
        });
        
        rboy.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				a_bt.setEnabled(true);
		        c_bt.setEnabled(true);
			}
        	
        });
        
        rgirl.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				a_bt.setEnabled(true);
		        c_bt.setEnabled(true);
			}
        	
        });
        
        a_bt.setOnClickListener(this);
        c_bt.setOnClickListener(this);*/
        
    }
    
    public void onClick(View v){
    	
    	if(v.getId() == R.id.answer_The_Q_button){
    		if(rboy.isChecked()){
    			checkAnwser("Boy");
    		} else if(rgirl.isChecked()){
    			checkAnwser("Girl");
    		}
    	}
    	
    	if(v.getId() == R.id.clean_The_Q_button){
    		rboy.setChecked(false);
    		rgirl.setChecked(false);
    		
    		a_bt.setEnabled(false);
    		c_bt.setEnabled(false);
    		cb_show.setText("");
    	}
    }
    
    public void checkAnwser(String str){
    	
    	Toast.makeText(this, "check answer...", Toast.LENGTH_LONG).show();
    	
    	Random random = new Random();
    	int index =  random.nextInt(9);
    	
    	if(boy_girl[index].equals(str)){
    		showrw.setIcon(R.drawable.right);
    		showrw.setTitle("Congratulation");
    		showrw.setPositiveButton("OK", null);
    		showrw.setMessage("your answer is right!").show();
    		Toast.makeText(this, boy_girl[index], Toast.LENGTH_LONG).show();
    	} else{
    		showrw.setIcon(R.drawable.wrong);
    		showrw.setTitle("Sorry");
    		showrw.setPositiveButton("Cancel", null);
    		
    		showrw.setMessage("your answer is wrong!").show();
    		Toast.makeText(this, boy_girl[index], Toast.LENGTH_LONG).show();
    	}
    	
    }
    
    private CheckBox.OnCheckedChangeListener checkBoxLisner = new CheckBox.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
			// TODO Auto-generated method stub
			if(cb_mp4.isChecked()||cb_book.isChecked()||cb_cd.isChecked()){
				if(cb_mp4.isChecked()){
					cb_show.setText("MP4"+"\n");
				}else{
					cb_show.setText("");
				}
				
				if(cb_book.isChecked()){
					cb_show.append("book"+"\n");
				}else{
					cb_show.append("");
				}
				
				if(cb_cd.isChecked()){
					cb_show.append("music cd"+"\n");
				}else{
					cb_show.append("");
				}
			}else{
				cb_show.setText("You did not select anything");
			}
		}
    	
    };

    public void switchToLayout2(){
    	setContentView(R.layout.viewtest);
        Button layout2_bt = (Button)findViewById(R.id.button_test1);
        layout2_bt.setOnClickListener(new OnClickListener(){
        				public void onClick(View v)
        				{
        					switchToLayout1();
        				}
        			}
        		);
    }
    
    public void switchToLayout1(){
    	setContentView(R.layout.activity_main);
        Button layout1_bt = (Button)findViewById(R.id.button_test2);
        layout1_bt.setOnClickListener(new OnClickListener(){
        				public void onClick(View v)
        				{
        					switchToLayout2();
        				}
        			}
        		);
    }
    
    private RadioButton manrbt;
    private RadioButton womanrbt;
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
    		Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	switch (resultCode) {
    	case RESULT_OK:
    	/* 取得来自Activity2 的数据，并显示于画面上*/
    	Bundle bunde = data.getExtras();
    	String sex = bunde.getString("sex");
    	double height = bunde.getDouble("height");
    	EditText et = (EditText)findViewById(R.id.height_Edit);
    	et.setText("" + height);
    	if (sex.equals("M")) {
    		manrbt = (RadioButton)findViewById(R.id.Sex_Man);
    		manrbt.setChecked(true);
    	} else {
    		womanrbt = (RadioButton)findViewById(R.id.Sex_Woman);
    		womanrbt.setChecked(true);
    	} 
    		break;
    	default:
    	break;
    	}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        m_exit = menu.add("exit");
        m_exit.setIcon(R.drawable.error);
        
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
