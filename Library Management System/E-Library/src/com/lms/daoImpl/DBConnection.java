package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import com.lms.util.DBIntializer;

public class DBConnection {
	public static Connection getConnecttion() {
		Connection cons = null;
		try {
			Class.forName(DBIntializer.DRIVER);
			cons = DriverManager.getConnection(DBIntializer.CON_STRING, DBIntializer.USERNAME, DBIntializer.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cons;
	}

	public static void main(String[] args) {
		System.out.println(getConnecttion());
	}

}
