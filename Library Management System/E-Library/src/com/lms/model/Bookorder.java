package com.lms.model;

public class Bookorder {

	private int orderId;
	private String booKId;
	private String userCode;
	private String date;
	private String role;

	public Bookorder() {

	}

	public Bookorder(int orderId, String booKId, String userCode, String date, String role) {
		super();
		this.orderId = orderId;
		this.booKId = booKId;
		this.userCode = userCode;
		this.date = date;
		this.role = role;
	}

	public Bookorder(String role, int orderId) {
		this.role = role;
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getBooKId() {
		return booKId;
	}

	public void setBooKId(String booKId) {
		this.booKId = booKId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
