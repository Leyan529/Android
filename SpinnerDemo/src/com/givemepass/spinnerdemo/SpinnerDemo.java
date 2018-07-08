package com.givemepass.spinnerdemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

public class SpinnerDemo extends Activity {
	/** Called when the activity is first created. */
	private Spinner sp_time, sp_alarm, sp_call, sp_oncall, sp_drop;
	private String[] lunch = { "雞腿飯", "魯肉飯", "排骨飯", "水餃", "陽春麵" };
	private String[] color = { "紅", "黃", "綠", "藍", "白" };
	private ArrayAdapter<String> lunchList, colorlist;
	private Context mContext;
	private ImageView imagetimer, imagealarm, imagecall, imageoncall,
			imagedroponcall;
	private int  cotimer=0,coalarm=1,cocall=2,cooncall=3,codrop=4;
	private int colortemp = 0;
	private LinearLayout  layoutcolor;
	private Switch	mSwitch,enableBluetoothButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this.getApplicationContext();
		mSwitch= (Switch) findViewById(R.id.switchcolor);
		enableBluetoothButton= (Switch) findViewById(R.id.enableBluetooth);
		layoutcolor = (LinearLayout) findViewById(R.id.layoutcolor);
		sp_time = (Spinner) findViewById(R.id.timerSpinner);
		sp_alarm = (Spinner) findViewById(R.id.alarmSpinner);
		sp_call = (Spinner) findViewById(R.id.callSpinner);
		sp_oncall = (Spinner) findViewById(R.id.oncallSpinner);
		sp_drop = (Spinner) findViewById(R.id.dropcallSpinner);
		lunchList = new ArrayAdapter<String>(SpinnerDemo.this,
				android.R.layout.simple_spinner_item, lunch);
		colorlist = new ArrayAdapter<String>(SpinnerDemo.this,
				android.R.layout.simple_spinner_item, color);
		imagetimer = (ImageView) findViewById(R.id.imagetimer);
		imagetimer.setBackgroundColor(Color.RED);
		imagealarm = (ImageView) findViewById(R.id.imagealarm);
		imagealarm.setBackgroundColor(Color.YELLOW);
		imagecall = (ImageView) findViewById(R.id.imagecall);
		imagecall.setBackgroundColor(Color.GREEN);
		imageoncall = (ImageView) findViewById(R.id.imageoncall);
		imageoncall.setBackgroundColor(Color.BLUE);
		imagedroponcall = (ImageView) findViewById(R.id.imagedroponcall);
		imagedroponcall.setBackgroundColor(Color.WHITE);
		
		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked) {  
					
					layoutcolor.setVisibility(View.VISIBLE);
                } else {  
                	
                	layoutcolor.setVisibility(View.GONE);}  
				
			}});
		
		sp_time.setAdapter(colorlist);
		sp_time.setSelection(0);
		sp_time.setOnItemSelectedListener(new OnItemSelectedListener() {
			boolean b = false;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (b) {
					
						colortemp = position;	cotimer=position;

						Toast.makeText(mContext, "你選的是" + color[position],
								Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							imagetimer.setBackgroundColor(Color.RED);
							break;
						case 1:
							imagetimer.setBackgroundColor(Color.YELLOW);
							break;
						case 2:
							imagetimer.setBackgroundColor(Color.GREEN);
							break;
						case 3:
							imagetimer.setBackgroundColor(Color.BLUE);
							break;
						case 4:
							imagetimer.setBackgroundColor(Color.WHITE);
							break;
						}

					 
				}
				b = true;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		sp_alarm.setAdapter(colorlist);
		sp_alarm.setSelection(1);
		sp_alarm.setOnItemSelectedListener(new OnItemSelectedListener() {
			boolean b = false;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (b) {
					colortemp = position;	coalarm=position;
					
						Toast.makeText(mContext, "你選的是" + color[position],
								Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							imagealarm.setBackgroundColor(Color.RED);
							break;
						case 1:
							imagealarm.setBackgroundColor(Color.YELLOW);
							break;
						case 2:
							imagealarm.setBackgroundColor(Color.GREEN);
							break;
						case 3:
							imagealarm.setBackgroundColor(Color.BLUE);
							break;
						case 4:
							imagealarm.setBackgroundColor(Color.WHITE);
							break;
						}
					
				
				}b = true;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		sp_call.setAdapter(colorlist);
		sp_call.setSelection(2);
		sp_call.setOnItemSelectedListener(new OnItemSelectedListener() {
			boolean b = false;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (b) {
					colortemp = position;	cocall=position;
					
						Toast.makeText(mContext, "你選的是" + color[position],
								Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							imagecall.setBackgroundColor(Color.RED);
							break;
						case 1:
							imagecall.setBackgroundColor(Color.YELLOW);
							break;
						case 2:
							imagecall.setBackgroundColor(Color.GREEN);
							break;
						case 3:
							imagecall.setBackgroundColor(Color.BLUE);
							break;
						case 4:
							imagecall.setBackgroundColor(Color.WHITE);
							break;
						}
					
				
				}b = true;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		sp_oncall.setAdapter(colorlist);
		sp_oncall.setSelection(3);
		sp_oncall.setOnItemSelectedListener(new OnItemSelectedListener() {
			boolean b = false;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (b) {
					colortemp = position;	cooncall=position;
					
						Toast.makeText(mContext, "你選的是" + color[position],
								Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							imageoncall.setBackgroundColor(Color.RED);
							break;
						case 1:
							imageoncall.setBackgroundColor(Color.YELLOW);
							break;
						case 2:
							imageoncall.setBackgroundColor(Color.GREEN);
							break;
						case 3:
							imageoncall.setBackgroundColor(Color.BLUE);
							break;
						case 4:
							imageoncall.setBackgroundColor(Color.WHITE);
							break;
						}
					
				
				}b = true;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		sp_drop.setAdapter(colorlist);
		sp_drop.setSelection(4);
		sp_drop.setOnItemSelectedListener(new OnItemSelectedListener() {
			boolean b = false;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (b) {
					colortemp = position;	codrop=position;

					
						Toast.makeText(mContext, "你選的是" + color[position],
								Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							imagedroponcall.setBackgroundColor(Color.RED);
							break;
						case 1:
							imagedroponcall.setBackgroundColor(Color.YELLOW);
							break;
						case 2:
							imagedroponcall.setBackgroundColor(Color.GREEN);
							break;
						case 3:
							imagedroponcall.setBackgroundColor(Color.BLUE);
							break;
						case 4:
							imagedroponcall.setBackgroundColor(Color.WHITE);
							break;
						}
					
				
				}b = true;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}
}