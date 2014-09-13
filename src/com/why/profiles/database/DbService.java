package com.why.profiles.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

public interface DbService {
	
public boolean createList(ContentValues values);
	
	public boolean addList(ContentValues values,String listname);
	
	public boolean addLoginlist(ContentValues values, String tablename);

	public boolean deleteList(String whereClause, String[] whereArgs,String listname);

	public boolean updateList(ContentValues values, String whereClause,
			String[] whereArgs,String listname);
	
	public boolean updateLogintag(ContentValues values, String string,
			String[] strings, String tablename);
	
	public boolean updateLoginName(ContentValues values, String string,
			String[] strings, String tablename);
	
	public Map<String, String> viewList(String selection,
			String[] selectionArgs,String listname);

	public ArrayList<HashMap<String, Object>> listListMaps(String selection,
			String[] selectionArgs,String listname);

}
