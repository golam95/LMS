package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lms.dao.UserDao;
import com.lms.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User u) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into user value(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getUserName());
			ps.setString(3, u.getUserEmail());
			ps.setString(4, u.getUserBatch());
			ps.setString(5, u.getUsergenerateId());
			ps.setString(6, u.getUserDate());
			ps.setString(7, u.getUserpassword());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean checkUser(String username) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from user where useremail='" + username + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(check_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checklogIn(String email, String password) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from user where useremail='" + email + "' and password='" + password + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(check_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
