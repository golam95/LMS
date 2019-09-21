package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lms.dao.BookOrderDao;
import com.lms.model.Bookorder;

public class BookOrderDaoImpl implements BookOrderDao {

	@Override
	public void addOrder(Bookorder order) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into bookorder value(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, order.getOrderId());
			ps.setString(2, order.getBooKId());
			ps.setString(3, order.getUserCode());
			ps.setString(4, order.getDate());
			ps.setString(5, order.getRole());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updatebookorder(Bookorder update) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update bookorder set role=? where id=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, update.getRole());
			ps.setInt(2, update.getOrderId());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkStatus(String id, String role) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from bookorder where id='" + id + "' and role='" + role + "'";
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
