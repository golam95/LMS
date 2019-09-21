package com.lms.dao;

import java.sql.SQLException;
import com.lms.model.User;

public interface UserDao {

	public void addUser(User u) throws SQLException;

	public boolean checkUser(String email) throws SQLException;

	public boolean checklogIn(String email,String password) throws SQLException;
}
