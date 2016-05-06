package com.model;

public class Status {

	private int uniqueIdentifierId;
	private boolean flag;
	private String message;

	public Status(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public Status(int code, String message) {
		this.uniqueIdentifierId = code;
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public int getUniqueIdentifierId() {
		return uniqueIdentifierId;
	}

	public void setUniqueIdentifierId(int uniqueIdentifierId) {
		this.uniqueIdentifierId = uniqueIdentifierId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
