package com.why.profiles.adapter;

import java.util.Arrays;

import android.widget.SectionIndexer;

public class ScheduleSectionIndexer implements SectionIndexer{
	private final String[] sections;
	private final int[] positions;
	private final int lastIndex;
	public ScheduleSectionIndexer(String[] sc,int[] count){
		//first we need to check the parameters !
		if(sc==null||count==null){
			throw new NullPointerException();
		}
		int c = count.length;
		if (sc.length != c) {
			throw new IllegalArgumentException(
					"The sections and counts arrays must have the same length");
		}
		this.sections = sc;
		this.positions = new int[c];
		int position = 0;
		for(int i=0;i<c;i++){
			positions[i] = position;
			position += count[i];
		}
		this.lastIndex = position;
	}
	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub
		if(sectionIndex<0||sectionIndex>=sections.length){
			return -1;
		}
		return this.positions[sectionIndex];
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		if(position<0||position>=lastIndex){
			return -1;
		}
		int index = Arrays.binarySearch(positions, position);
		return index >= 0 ? index : -index - 2; 
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return sections;
	}

}
