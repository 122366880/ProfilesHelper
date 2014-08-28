package com.why.profiles.fragment;

import java.util.ArrayList;
import java.util.List;

import com.why.profiles.R;
import com.why.profiles.adapter.ViewPagerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class ProfilesFragment extends Fragment{

	private View mView;
	List<View> mViews = new ArrayList<View>();  
	String[] mTitles = {"页面1", "页面2", "页面3"};  
	ViewPagerAdapter pagerAdapter;  
	PagerTabStrip pagerTabStrip;  
	PagerTitleStrip pagerTitleStrip;  
	ViewPager viewPager;  
	View view1, view2, view3;  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_profile,container, false);

		viewPager = (ViewPager)mView.findViewById(R.id.viewpager);  
		pagerTabStrip = (PagerTabStrip)mView.findViewById(R.id.pagertab);  
		LayoutInflater flater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		view1 = flater.inflate(R.layout.fragment_operate, null);  
		view2 = flater.inflate(R.layout.fragment_location, null);  
		view3 = flater.inflate(R.layout.fragment_schedule, null);  
		mViews.add(view1);  
		mViews.add(view2);  
		mViews.add(view3);  
		pagerAdapter = new ViewPagerAdapter(mViews, mTitles);  
		viewPager.setAdapter(pagerAdapter);  
		viewPager.setCurrentItem(1);  
		//设置pagerTabStrip  
		pagerTabStrip.setTabIndicatorColor(Color.RED);  
		pagerTabStrip.setTextColor(Color.BLUE);  
		pagerTabStrip.setClickable(false);  
		pagerTabStrip.setTextSpacing(10);  
		pagerTabStrip.setBackgroundColor(Color.GRAY);  
		pagerTabStrip.setDrawFullUnderline(true);  
		/*      pagerTitleStrip.setTextColor(Color.RED); 
        pagerTitleStrip.setTextSpacing(40);*/  

		//ViewPager滑动监听  
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {  

			@Override  
			public void onPageSelected(int arg0) {  
				// TODO Auto-generated method stub  
			}  

			@Override  
			public void onPageScrolled(int arg0, float arg1, int arg2) {  
				// TODO Auto-generated method stub  

			}  

			@Override  
			public void onPageScrollStateChanged(int arg0) {  
				// TODO Auto-generated method stub  

			}  
		});  
		return mView;
	}
}
