package com.bruce.model;

import java.util.Date;

public class Log {
	
	private long id;
	
	private String type;
	
	private String detail;
	
	private Date time;
	
	private String username;

	public Log(){}

	public Log(String type, String detail, Date time, String user) {
		super();
		this.type = type;
		this.detail = detail;
		this.time = time;
		this.username = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", type=" + type + ", detail=" + detail
				+ ", time=" + time + ", user=" + username + "]";
	}
	
}
