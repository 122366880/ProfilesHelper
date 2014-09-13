package com.why.profiles.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.content.ContentValues;
import android.content.Context;


public class DbOperate {

	private Context context;
	private Map<String,String> map;

	public DbOperate(Context context){
		this.context = context;
	}

	//�������ݿ�mylist.db	
	public void createListDb() {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		boolean flag = service.createList(values);
		System.out.println("createList------------------->>" + flag);
	}

	public void addUserLoginlist(int id,String userNum,String userPassword, String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("id", id);
		values.put("usernum", userNum);
		values.put("userpassword", userPassword);
		boolean flag = service.addLoginlist(values,tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}

	public void updateUserLoginName(int id,String userName,String tablename){
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("username", userName);
		boolean flag = service.updateLoginName(values, " id = ? ",
				new String[]{""+id},tablename);
		System.out.println("update"+tablename+"--->>" + flag);
	}

	public Map<String,String> viewUserMap(int id,String tablename){
		DbService service = new DbDataLead(context);
		map = service.viewList(" id = ? ", new String[]{""+id},tablename);
		return map;
	}

	//���sportsId��ʱ��
	public void addSportsId(String sportsId,String dateTime,String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("begintime", dateTime);
		values.put("sportsid", sportsId);
		boolean flag = service.addList(values,tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}


	//����ٶȡ�λ�á�����ʱ��
	public void addVelocity(String sportsId, String listVelocity, String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("velocity", listVelocity);

		boolean flag = service.updateList(values, " sportsid = ? ", 
				new String[]{""+sportsId},tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}

	public void addLocation(String sportsId, String listLocation, String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("location", listLocation);
		boolean flag = service.updateList(values, " sportsid = ? ", 
				new String[]{sportsId},tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}

	public void addEndTime(String sportsId, String endTime, String useTime, 
			String mDistance,String mCalorie, String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("endtime", endTime);
		values.put("usetime", useTime);
		values.put("distance", mDistance);
		values.put("calorie", mCalorie);
		boolean flag = service.updateList(values, " sportsid = ? ", 
				new String[]{sportsId},tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}


	public void addBaiduLocation(String sportsId, String listLocation,String tablename) {
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("baidulocation", listLocation);
		boolean flag = service.updateList(values, " sporstid = ? ", 
				new String[]{sportsId},tablename);
		System.out.println("addList"+tablename+"--->>" + flag);
	}

	public void deleteList(int id,String tablename) {
		DbService service = new DbDataLead(context);
		// delete from message where id = ?
		//������ where�ؼ���
		boolean flag = service.deleteList(" id = ? ", new String[] {""+id},tablename);
		System.out.println("deleteList"+tablename+"--->>" + flag);
	}

	public void deleteSportsDataList(String sportsId,String tablename) {
		DbService service = new DbDataLead(context);
		// delete from message where id = ?
		//������ where�ؼ���
		boolean flag = service.deleteList(" sportsid = ? ",
				new String[] {sportsId},tablename);
		System.out.println("deleteList"+tablename+"--->>" + flag);
	}
	//	public void deleteName(String username,String tablename) {
	//		DbService service = new DbDataLead(context);
	//		// delete from message where id = ?
	//		//������ where�ؼ���
	//		boolean flag = service.deleteList(" username = ? ", new String[] {username},tablename);
	//		System.out.println("deleteList"+tablename+"--->>" + flag);
	//	}

	public void updateList(int id,String content,String tablename){
		DbService service = new DbDataLead(context);
		ContentValues values = new ContentValues();// ����map������
		values.put("json", content);
		boolean flag = service.updateList(values, " id = ? ", new String[]{""+id},tablename);
		System.out.println("update"+tablename+"--->>" + flag);
	}



	public Map<String,String> viewSportsDataMap(String sportsId,String tablename){
		DbService service = new DbDataLead(context);
		map = service.viewList(" sportsid = ? ", new String[]{sportsId},tablename);
		return map;
	}


	public ArrayList<HashMap<String, Object>> viewAllList(String tablename){
		DbService service = new DbDataLead(context);
		//select * from message
		ArrayList<HashMap<String, Object>> list = service.listListMaps(null, null, tablename);
		return list;
	}	 
	public ArrayList<HashMap<String, Object>> viewSportsDataList(String userNum, String tablename){
		DbService service = new DbDataLead(context);
		//select * from message
		ArrayList<HashMap<String, Object>> list = service.listListMaps(" usernum = ? ",
				new String[]{userNum},tablename);
		return list;
	}	 
	

}

