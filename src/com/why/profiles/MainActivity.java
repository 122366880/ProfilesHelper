package com.why.profiles;


import com.why.profiles.common.DummyTabContent;
import com.why.profiles.fragment.LocationFragment;
import com.why.profiles.fragment.OperateFragment;
import com.why.profiles.fragment.ProfilesFragment;
import com.why.profiles.fragment.ScheduleFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * @author Michael.HAN
 *
 * 2014-1-20
 */
public class MainActivity extends FragmentActivity{

	private DisplayMetrics dm;
	private int tab_width,tab_height;
	private TabHost mTabHost;
	private TabWidget tabWidget;
	private FragmentTransaction mFragmentTransaction;
	private RelativeLayout mTabIndicator_profiles, mTabIndicator_location,
	mTabIndicator_schedule,mTabIndicator_operate;
	private ProfilesFragment profilesFragment;
	private ScheduleFragment scheduleFragment;
	private LocationFragment locationFragment;
	private OperateFragment operateFragment;
	private FragmentManager mFragmentManager = getSupportFragmentManager();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findTabView();
		mTabHost.setup();
		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {

				mFragmentTransaction = mFragmentManager.beginTransaction();
				mFragmentTransaction.setCustomAnimations(
		                R.anim.push_left_in,
		                R.anim.push_left_out,
		                R.anim.push_left_in,
		                R.anim.push_left_out);
				mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				// TODO Auto-generated method stub
				if (profilesFragment != null) {
					mFragmentTransaction.hide(profilesFragment);
				}
				if (scheduleFragment != null) {
					mFragmentTransaction.hide(scheduleFragment);
				}
				if (locationFragment != null) {
					mFragmentTransaction.hide(locationFragment);
				}
				if (operateFragment != null) {
					mFragmentTransaction.hide(operateFragment);
				}

				
				if (tabId.equalsIgnoreCase("Profiles")) {
					attachTabProfiles();
				} else if (tabId.equalsIgnoreCase("Schedule")) {
					attachTabSchedule();
				} else if (tabId.equalsIgnoreCase("Location")) {
					attachTabLocation();
				} else if (tabId.equalsIgnoreCase("Operate")) {
					attachTabOperate();
				}
				//增加执行的动画效果 动画不能用我们的Tween动画，后面会有代码
				
				
				// 执行Fragment事务（添加。移除，替换fragment）
				mFragmentTransaction.commit();
			}
		};
		mTabHost.setOnTabChangedListener(tabChangeListener);
		initTab();

		int flag;
		try{
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();
			flag =(int)bundle.getInt("flag");
		}catch (Exception  e) {
			flag = 0;
		}
		Log.i("", "---------->>"+flag);
		mTabHost.setCurrentTab(flag);
		
	}

	private void findTabView() {

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		tab_width = dm.widthPixels/4;
		tab_height = dm.heightPixels/10;
		Log.i("", ""+tab_width+"----------"+tab_height);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		LinearLayout layout = (LinearLayout) mTabHost.getChildAt(0);
		tabWidget = (TabWidget) layout.getChildAt(1);
		tabWidget.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);

		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams
				(dm.widthPixels/6, dm.heightPixels/25);
		param.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		param.topMargin=10;

		mTabIndicator_profiles = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator, tabWidget, false);
		mTabIndicator_profiles.setLayoutParams(new LayoutParams(tab_width, tab_height));
//		mTabIndicator_profiles.setBackgroundResource(R.drawable.tab_selected_profiles);
		mTabIndicator_profiles.setBackgroundResource(R.drawable.tab_selected_item_bg);
		ImageView ivTab1 = (ImageView) mTabIndicator_profiles.getChildAt(0);
		ivTab1.setLayoutParams(param);
		ivTab1.setImageResource(R.drawable.tab_edu_bg);
		TextView tvTab1 = (TextView) mTabIndicator_profiles.getChildAt(1);
		tvTab1.setText("情景设置");
		if(dm.heightPixels/52<20){
			tvTab1.setTextSize(dm.heightPixels/52);
		}else{
			tvTab1.setTextSize(20);
		}


		mTabIndicator_schedule = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator, tabWidget, false);
		mTabIndicator_schedule.setLayoutParams(new LayoutParams(tab_width, tab_height));
//		mTabIndicator_schedule.setBackgroundResource(R.drawable.tab_selected_schedule);
		mTabIndicator_schedule.setBackgroundResource(R.drawable.tab_selected_item_bg);
		ImageView ivTab2 = (ImageView) mTabIndicator_schedule.getChildAt(0);
		ivTab2.setLayoutParams(param);
		ivTab2.setImageResource(R.drawable.tab_card_bg);
		TextView tvTab2 = (TextView) mTabIndicator_schedule.getChildAt(1);
		tvTab2.setText("日程安排");
		if(dm.heightPixels/52<20){
			tvTab2.setTextSize(dm.heightPixels/52);
		}else{
			tvTab2.setTextSize(20);
		}

		mTabIndicator_location = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator, tabWidget, false);
		mTabIndicator_location.setLayoutParams(new LayoutParams(tab_width, tab_height));
//		mTabIndicator_location.setBackgroundResource(R.drawable.tab_selected_location);
		mTabIndicator_location.setBackgroundResource(R.drawable.tab_selected_item_bg);
		ImageView ivTab3 = (ImageView) mTabIndicator_location.getChildAt(0);
		ivTab3.setLayoutParams(param);
		ivTab3.setImageResource(R.drawable.tab_life_bg);
		TextView tvTab3 = (TextView) mTabIndicator_location.getChildAt(1);
		tvTab3.setText("定位信息");
		if(dm.heightPixels/52<20){
			tvTab3.setTextSize(dm.heightPixels/52);
		}else{
			tvTab3.setTextSize(20);
		}


		mTabIndicator_operate = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_indicator, tabWidget, false);
		mTabIndicator_operate.setLayoutParams(new LayoutParams(tab_width, tab_height));
//		mTabIndicator_operate.setBackgroundResource(R.drawable.tab_selected_operate);
		mTabIndicator_operate.setBackgroundResource(R.drawable.tab_selected_item_bg);
		ImageView ivTab4 = (ImageView) mTabIndicator_operate.getChildAt(0);
		ivTab4.setLayoutParams(param);
		ivTab4.setImageResource(R.drawable.tab_other_bg);
		TextView tvTab4 = (TextView) mTabIndicator_operate.getChildAt(1);
		tvTab4.setText("智能操作");
		if(dm.heightPixels/52<20){
			tvTab4.setTextSize(dm.heightPixels/52);
		}else{
			tvTab4.setTextSize(20);
		}

	}

	private void attachTabProfiles() {

		if (profilesFragment == null) {
			profilesFragment = new ProfilesFragment();
			mFragmentTransaction.add(R.id.realtabcontent, profilesFragment, "Profile");
		} else {
			mFragmentTransaction.show(profilesFragment);
		}

	}

	private void attachTabSchedule() {

		if (scheduleFragment == null) {
			scheduleFragment = new ScheduleFragment();
			mFragmentTransaction.add(R.id.realtabcontent, scheduleFragment, "Schedule");
		} else {
			mFragmentTransaction.show(scheduleFragment);
		}
	}

	private void attachTabLocation() {

		if (locationFragment == null) {
			locationFragment = new LocationFragment();
			mFragmentTransaction.add(R.id.realtabcontent, locationFragment, "Location");
		} else {
			mFragmentTransaction.show(locationFragment);
		}
	}

	private void attachTabOperate() {

		if (operateFragment == null) {
			operateFragment = new OperateFragment();
			mFragmentTransaction.add(R.id.realtabcontent, operateFragment, "Operate");
		} else {
			mFragmentTransaction.show(operateFragment);
		}
	}


	/**
	 * 初始化选项卡 <br>
	 */
	private void initTab() {

		TabHost.TabSpec tSpecProfiles = mTabHost.newTabSpec("Profiles");
		tSpecProfiles.setIndicator(mTabIndicator_profiles);
		tSpecProfiles.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpecProfiles);

		TabHost.TabSpec tSpecSchedule = mTabHost.newTabSpec("Schedule");
		tSpecSchedule.setIndicator(mTabIndicator_schedule);
		tSpecSchedule.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpecSchedule);

		TabHost.TabSpec tSpecLocation = mTabHost.newTabSpec("Location");
		tSpecLocation.setIndicator(mTabIndicator_location);
		tSpecLocation.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpecLocation);

		TabHost.TabSpec tSpecOperate = mTabHost.newTabSpec("Operate");
		tSpecOperate.setIndicator(mTabIndicator_operate);
		tSpecOperate.setContent(new DummyTabContent(getBaseContext()));
		mTabHost.addTab(tSpecOperate);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("LOG", "onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("LOG", "onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	 
}

