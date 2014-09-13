package com.why.profiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	/**
	 * 创建表
	 */
	private static String name = "SafeSports.db";// 表示数据库的名称
	private static int version = 2;// 表示数据库的版本号码

	public DbOpenHelper(Context context) {
		super(context, name, null, version);
	}

	// 当数据库创建的时候，是第一次被执行,完成对数据库的表的创建
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//支持的数据类型：整型数据，字符串类型，日期类型，二进制的数据类型，
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
