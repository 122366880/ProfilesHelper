package com.why.profiles.model;

public class ScheduleEntity {

	private String addName;
	private String date;
	private String detail;
	private int onTime;
	private int repeate;
	private int status;
	private String time;
	private String type;
	private String alertType;
	private String setupTime;
	public String getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getAddName() {
		return addName;
	}
	public String getDate() {
		return date;
	}
	public String getDetail() {
		return detail;
	}
	public int getOnTime() {
		return onTime;
	}
	public int getRepeate() {
		return repeate;
	}
	public int getStatus() {
		return status;
	}
	public String getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setOnTime(int onTime) {
		this.onTime = onTime;
	}
	public void setRepeate(int repeate) {
		this.repeate = repeate;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ScheduleEntity(String addName, String date, String detail,
			int onTime, int repeate, int status, String time, String type,
			String alertType, String setupTime) {
		this.addName = addName;
		this.date = date;
		this.detail = detail;
		this.onTime = onTime;
		this.repeate = repeate;
		this.status = status;
		this.time = time;
		this.type = type;
		this.alertType = alertType;
		this.setupTime = setupTime;
	}
	
	
	
}
