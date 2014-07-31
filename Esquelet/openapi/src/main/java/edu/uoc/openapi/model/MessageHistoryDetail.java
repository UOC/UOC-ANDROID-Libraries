package edu.uoc.openapi.model;

public class MessageHistoryDetail {
	private String action;
	private String date;
	private String name;
	private String time;

    public MessageHistoryDetail(){

    }

    public MessageHistoryDetail(String action, String date, String name, String time) {
        this.action = action;
        this.date = date;
        this.name = name;
        this.time = time;
    }

    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}