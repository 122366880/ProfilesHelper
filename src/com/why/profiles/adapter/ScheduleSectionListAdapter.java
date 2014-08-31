package com.why.profiles.adapter;


import com.why.profiles.R;
import com.why.profiles.model.ScheduleEntity;
import com.why.profiles.view.PinnedHeaderListView;
import com.why.profiles.view.PinnedHeaderListView.*;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;



public class ScheduleSectionListAdapter implements ListAdapter , OnItemClickListener , PinnedHeaderAdapter , SectionIndexer , OnScrollListener{


	LayoutInflater inflater;
	ScheduleSectionIndexer indexer;
	String[] sections;
	int[] itemCount;
	int itemNum;
	int sectionNum;
	ScheduleEntity[] originDataSet ;
	ScheduleEntity[] finalDataSet ;
	ScheduleAdapter arrayAdapter ;
	HandleItemClickEvent itemClickListener;
	class ScheduleAdapter extends ArrayAdapter<ScheduleEntity>{

		public ScheduleAdapter(Context context, int resource, ScheduleEntity[] objects) {
			
			super(context, resource, objects);
		}
		
	}
	
	//!!!! this interface is used to pass the information of clicked item
	public interface HandleItemClickEvent{
		public void whenItemClicked(View arg1,int position);
	}
	
	
	public ScheduleSectionListAdapter(Context context,LayoutInflater li,ScheduleEntity[] data,int sectionNum,HandleItemClickEvent clickListener ){
		this.inflater = li;
		this.sectionNum = sectionNum;
		this.itemClickListener = clickListener;
		itemNum = data.length;
		originDataSet = data;
		finalDataSet = new ScheduleEntity[itemNum];
		initSections();
		int layout = -1;
		layout = R.layout.schedule_list_item;
		
		
			
		if(layout != -1){
			
			arrayAdapter = new ScheduleAdapter(context,layout,finalDataSet);
		}
		
		
	}
	
	//!!!! handle items in arrayAdapter and initial relative variables
	public void initSections(){
		if(originDataSet != null&& originDataSet.length != 0){
			int sectionCount = -1;
			boolean flag = false;
			int temp = -1;
			int[] pos = new int[this.sectionNum];//  var pos is used to sort items in originDataSet
			
			
			sections = new String[this.sectionNum];
			itemCount = new int[this.sectionNum];
			for(int i = 0 ; i < sectionNum ; i ++){
				sections[i] = "Section " + i;
				itemCount[i] = 0;
			}
			//search items in arrayAdapter and proper organise them 
			for(int i = 0 ; i < itemNum ; i++){
				flag = false;
				temp = -1;
				ScheduleEntity n = originDataSet[i];
				//perform linear search to find out which section current item belongs to  
				for(int j = 0 ; j <= sectionCount ; j++){
					if(sections[j].equals(n.getDate())){
						flag = true;
						temp = j;
						break;
					}
				}
				if(flag){
					itemCount[temp] ++;
				
					continue;
				}
				else{
					sections[++sectionCount] = n.getDate();
					itemCount[sectionCount] = 1;
					
				}
			}
			pos[0] = 0;
			for(int i = 1;i < sectionNum;i++){
				pos[i] = pos[i-1] + itemCount[i-1];
			}
			//!!!! sort items by section 
			
			for(int i = 0 ; i < itemNum ; i++){
				for(int j = 0 ; j < sectionNum ; j++ ){
					if(sections[j].equals(originDataSet[i].getDate())){
						finalDataSet[pos[j]++] = originDataSet[i];
						break;
					}
				}
			}
			//!!!! since the first row is banner picture , so the real position is "position - 1" 
			indexer = new ScheduleSectionIndexer(sections,itemCount);
		}
		else{
			//!!!! maybe it is the first to run this app , show sth else to notify the user
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayAdapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return arrayAdapter.getItemId(position);
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return arrayAdapter.getItemViewType(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.schedule_list_item, null);

		}
		int realPosition = position ;
		ScheduleEntity n = arrayAdapter.getItem(position);
		if(n != null && view != null){
			int time_layout = -1;
			int setup_layout = -1;
			int section_layout = -1;
			time_layout = R.id.schedule_entry_timestamp;
			setup_layout = R.id.schedule_entry_setup;
			//banner_layout = R.id.custom_list_banner;
			section_layout = R.id.schedule_date_section;
			TextView preview = (TextView)view.findViewById(R.id.schedule_entry_preview);
			preview.setText(n.getDetail());
			
			
			
			if(time_layout != -1){
				TextView timestamp = (TextView) view.findViewById(time_layout);
				timestamp.setText(n.getTime());
			}
			if(setup_layout != -1){
				TextView source = (TextView)view.findViewById(setup_layout);
				source.setText(n.getSetupTime());
			}
			
			if(section_layout != -1 ){
				TextView section = (TextView)view.findViewById(section_layout);
			
				//!!!! show the section title !!!
				boolean tp = false;
				for(int i = 0 ; i < sectionNum; i++){
					if(getPositionForSection(i) == realPosition){
						section.setText(n.getDate());
						section.setVisibility(View.VISIBLE);
						tp = true;
						break;
					}
				}
				if(!tp){
					section.setVisibility(View.GONE);
						
				}
			}
			ImageView icon = (ImageView) view.findViewById(R.id.schedule_status_icon);
			if(n.getStatus() == 0){
				//normal 
				icon.setImageResource(R.drawable.schedule_res_1);
				
			}
			else if(n.getStatus() == 1){
				icon.setImageResource(R.drawable.schedule_res_6);
			}
		}

		return view;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return arrayAdapter.getViewTypeCount();
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return arrayAdapter.hasStableIds();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return arrayAdapter.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return arrayAdapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return arrayAdapter.isEnabled(position);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		this.itemClickListener.whenItemClicked(arg1,position);
	}

	@Override
	public int getPinnedHeaderState(int position) {
		// TODO Auto-generated method stub
		int realPosition = position ;// 这里没什么别的意思，主要是通讯录中，listview中第一个显示的是"所有联系人"(it means the title)，
		// 不是真实的数据，所以会-1,这里偷懒，直接把后面的去掉，还有其他有类似的地方，原因一样
		if (indexer == null) {
		return PINNED_HEADER_GONE;
		}
		if (realPosition < 0) {
		return PINNED_HEADER_GONE;
		}
		
		int section = getSectionForPosition(realPosition);// 得到此item所在的分组位置
		int nextSectionPosition = getPositionForSection(section + 1);// 得到下一个分组的位置
		if (nextSectionPosition != -1
		&& realPosition == nextSectionPosition - 1) {
		return PINNED_HEADER_PUSHED_UP;
		}
		return PINNED_HEADER_VISIBLE;
	}

	@Override
	public void configurePinnedHeader(View header, int position, int alpha) {
		// TODO Auto-generated method stub
			int realPosition = position;
			int section = getSectionForPosition(realPosition);
			int header_layout = -1;
			String title = (String) indexer.getSections()[section];
			header_layout = R.id.schedule_section_fly;
			
			if(header_layout != -1){
				TextView sectionFly = (TextView)header.findViewById(header_layout);
				if(position == 0){
					sectionFly.setBackgroundResource(R.color.schedule_section_fly_transparent);
					
					sectionFly.setText("");
				}
				else{
					sectionFly.setBackgroundResource(R.color.schedule_section_fly);
					sectionFly.setText(title);
				}
			}
			
				
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub
		return indexer.getPositionForSection(sectionIndex);
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return indexer.getSectionForPosition(position);
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return indexer.getSections();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

}

