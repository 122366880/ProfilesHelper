package com.why.profiles.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.SimpleAdapter;

public class ScheduleFragment extends Fragment implements HandleItemClickEvent,ScheduleClickListener{

	private View mView;
	private ScheduleEntity[] listEntries;
	private List<Map<String,String>> gridEntries;
	ScheduleSectionListAdapter adapter;
	LinearLayout contentParent;
	LayoutInflater mInflater;
	ViewGroup mContainer;
	com.why.profiles.view.PinnedHeaderListView listView;
	ExtendedCalendarView calendarView;
//	com.origamilabs.library.views.StaggeredGridView gridView;
	SimpleAdapter gridAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		loadListEntries(1);
		
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
		
		adapter = new ScheduleSectionListAdapter(getActivity(),getActivity().getLayoutInflater(),listEntries,4,this);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);
		listView.setPinnedHeaderView(inflater.inflate(R.layout.schedule_section_fly, listView, false));
		listView.setOnItemClickListener(adapter);
		//ll.addView(listView);
		return mView;
	} 
	
	public void loadListEntries(int mode){
		
		boolean newEntryFlag = false;
		
		//load data for list mode
		if(mode == 1){
			if(listEntries == null){
				listEntries = new ScheduleEntity[30];
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
					
					listEntries[i] = n;
				}
			}
			else if(newEntryFlag){
				//load new data
			}
		}
		else if(mode == 2){
			//prepare data for calendar mode 
		}
		else if(mode == 3){
			//prepare data for grid mode
			if(gridEntries == null){
				gridEntries = new ArrayList<Map<String,String>>();
				
				for(int i = 0;i < 20;i ++){
					Map<String,String> m = new HashMap<String,String>();
					m.put("schedule_grid_item_time", "明天  19:30");
					m.put("schedule_grid_item_content", "帮邻居购买面条、酱油、生姜");
					m.put("schedule_grid_item_setup", "昨天 20:23");
					
					gridEntries.add(m);
				}
			}
			else if(newEntryFlag){
				//load new data
			}
		}
		
		
	}
	
	@Override
	public void whenItemClicked(View arg1, int position) {
		// TODO Auto-generated method stub
		
	}
	
	//switch to list view mode
	public void list_mode(View v){
		loadListEntries(1);
		if(mView == null)
			mView = mInflater.inflate(R.layout.fragment_schedule,mContainer, false);
		if(contentParent == null)
			contentParent = (LinearLayout) mView.findViewById(R.id.schedule_content_parent);
		
		contentParent.removeAllViews();
		
		listView = (com.why.profiles.view.PinnedHeaderListView)(mInflater.inflate(R.layout.schedule_list_mode,contentParent)
					.findViewById(R.id.schedule_list_mode));
		
		
		adapter = new ScheduleSectionListAdapter(getActivity(),getActivity().getLayoutInflater(),listEntries,4,this);
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
		if(mView == null)
			mView = mInflater.inflate(R.layout.fragment_schedule,mContainer, false);
		if(contentParent == null)
			contentParent = (LinearLayout) mView.findViewById(R.id.schedule_content_parent);
		
		contentParent.removeAllViews();
//		gridView = (com.origamilabs.library.views.StaggeredGridView)(mInflater.inflate(R.layout.schedule_grid_mode, contentParent));
//		gridView.setColumnCount(2);
//		gridAdapter = new SimpleAdapter(getActivity(), gridEntries, R.layout.schedule_grid_mode_item,
//				new String[]{"schedule_grid_item_time","schedule_grid_item_content","schedule_grid_item_setup"}
//				,new int[]{R.id.schedule_grid_item_time,R.id.schedule_grid_item_content,R.id.schedule_grid_item_setup});
//		gridView.setAdapter(gridAdapter);
	}
	
	//add new alert
	public void add_alert(View v){
		
	}
}
