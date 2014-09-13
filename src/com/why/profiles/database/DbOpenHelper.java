package com.why.profiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	/**
	 * ������
	 */
	private static String name = "SafeSports.db";// ��ʾ���ݿ������
	private static int version = 2;// ��ʾ���ݿ�İ汾����

	public DbOpenHelper(Context context) {
		super(context, name, null, version);
	}

	// �����ݿⴴ����ʱ���ǵ�һ�α�ִ��,��ɶ����ݿ�ı�Ĵ���
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//֧�ֵ��������ͣ��������ݣ��ַ������ͣ��������ͣ������Ƶ��������ͣ�
		String sql1 = "create table sportsdatalist(sportsid text primary key ," +
				"usernum text,velocity text,location text,begintime text,endtime text," +
				"usetime text,distance text,calorie text)";
		String sql2 = "create table userlist(id integer primary key autoincrement," +
				"userpassword text,usernum text,username text)";
		db.execSQL(sql1);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
