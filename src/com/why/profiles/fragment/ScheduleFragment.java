package com.why.profiles.fragment;

import com.why.profiles.adapter.ScheduleSectionListAdapter;
import com.why.profiles.model.ScheduleEntity;
import com.why.profiles.view.ExtendedCalendarView;
import com.why.profiles.adapter.ScheduleSectionListAdapter.HandleItemClickEvent;
import com.why.profiles.MainActivity.ScheduleClickListener;
import com.why.profiles.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ScheduleFragment extends Fragment implements HandleItemClickEvent,ScheduleClickListener{

	private View mView;
	private ScheduleEntity[] entries;
	ScheduleSectionListAdapter adapter;
	LinearLayout contentParent;
	LayoutInflater mInflater;
	ViewGroup mContainer;
	com.why.profiles.view.PinnedHeaderListView listView;
	ExtendedCalendarView calendarView;
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		loadListEntries();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = inflater;
		mContainer = container;
		//make list view mode the default mode
		mView = inflater.inflate(R.layout.fragment_schedule,container, false);
		
		contentParent = (LinearLayout)mView.findViewById(R.id.schedule_content_parent);
		
		listView = (com.why.profiles.view.PinnedHeaderListView)(inflater.inflate(R.layout.schedule_list_mode,contentParent)
				.findViewById(R.id.schedule_list_mode));
		
		adapter = new ScheduleSectionListAdapter(getActivity(),getActivity().getLayoutInflater(),entries,4,this);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);
		listView.setPinnedHeaderView(inflater.inflate(R.layout.schedule_section_fly, listView, false));
		listView.setOnItemClickListener(adapter);
		//ll.addView(listView);
		return mView;
	} 
	
	public void loadListEntries(){
		
		boolean newEntryFlag = false;
		
		if(entries == null){
			entries = new ScheduleEntity[30];
			for(int i = 0 ; i < 30 ; i++ ){
				ScheduleEntity n = null;
				if(i % 4 == 0){
					n = new ScheduleEntity(null,"今天","明天邻居要来帮忙修理厨房水槽，需要提前准备工具",1,0,1,"19:30",null,"ring","昨天20:15");
				}
				else if(i % 4 == 1){
					n = new ScheduleEntity(null,"明天","叫外卖",1,0,0,"19:30",null,"ring","昨天20:15");
				}
				else if(i % 4 == 2){
					n = new ScheduleEntity(null,"未来7天","购物清单",1,0,0,"19:30",null,"ring","昨天20:15");
				}
				else
					n = new ScheduleEntity(null,"以后","给花浇水",1,0,0,"19:30",null,"ring","昨天20:15");
				
				if(i % 4 == 0 && i >= 24)
					n.setStatus(0);
				
				entries[i] = n;
			}
		}
		else if(newEntryFlag){
			//load new data
		}
		
	}
	
	@Override
	public void whenItemClicked(View arg1, int position) {
		// TODO Auto-generated method stub
		
	}
	
	//switch to list view mode
	public void list_mode(View v){
		loadListEntries();
		if(mView == null)
			mView = mInflater.inflate(R.layout.fragment_schedule,mContainer, false);
		if(contentParent == null)
			contentParent = (LinearLayout) mView.findViewById(R.id.schedule_content_parent);
		
		contentParent.removeAllViews();
		
		listView = (com.why.profiles.view.PinnedHeaderListView)(mInflater.inflate(R.layout.schedule_list_mode,contentParent)
					.findViewById(R.id.schedule_list_mode));
		
		
		adapter = new ScheduleSectionListAdapter(getActivity(),getActivity().getLayoutInflater(),entries,4,this);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);
		listView.setPinnedHeaderView(mInflater.inflate(R.layout.schedule_section_fly, listView, false));
		listView.setOnItemClickListener(adapter);
	}
	
	//switch to calendar mode
	public void calendar_mode(View v){
		if(mView == null)
			mView = mInflater.inflate(R.layout.fragment_schedule,mContainer, false);
		if(contentParent == null)
			contentParent = (LinearLayout) mView.findViewById(R.id.schedule_content_parent);
		
		contentParent.removeAllViews();
		
		calendarView = (ExtendedCalendarView)(mInflater.inflate(R.layout.schedule_calendar_mode, contentParent)
					.findViewById(R.id.schedule_calendar));
		
	}
	
	//switch to grid mode
	public void grid_mode(View v){
		
	}
	
	//add new alert
	public void add_alert(View v){
		
	}
}
