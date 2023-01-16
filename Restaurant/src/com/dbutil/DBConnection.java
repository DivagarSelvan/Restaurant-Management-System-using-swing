package com.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection con = null;
	public DBConnection() {
		
	}
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant","root","diva");
		}
		catch(ClassNotFoundException clse) {
			clse.printStackTrace();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return con;	
	}
}
