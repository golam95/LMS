package com.lms.model;

public class User {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String userBatch;
	private String userRole;
	private String usergenerateId;
	private String userDate;
	private String userpassword;

	public User() {
	}

	public User(int userId, String userName, String userEmail, String userBatch, String userRole, String usergenerateId,
			String userDate, String userpassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userBatch = userBatch;
		this.userRole = userRole;
		this.usergenerateId = usergenerateId;
		this.userDate = userDate;
		this.userpassword = userpassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBatch() {
		return userBatch;
	}

	public void setUserBatch(String userBatch) {
		this.userBatch = userBatch;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUsergenerateId() {
		return usergenerateId;
	}

	public void setUsergenerateId(String usergenerateId) {
		this.usergenerateId = usergenerateId;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

}
