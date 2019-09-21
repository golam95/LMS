package com.lms.dao;

import java.sql.SQLException;
import com.lms.model.Bookorder;

public interface BookOrderDao {

	public void addOrder(Bookorder order) throws SQLException;

	public void updatebookorder(Bookorder update) throws SQLException;

	public boolean checkStatus(String id, String role) throws SQLException;
}
