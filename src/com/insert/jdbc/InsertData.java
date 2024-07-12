package com.insert.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet res = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String query = "Select * from employee";
//		String query2 = "Delete from `employee` where salary<=10000";

		try {
			// Driver loaded
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
//			connection complete
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection complete");
			st = con.createStatement();
			res = st.executeQuery(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(con, st, res);
	}

	static void close(Connection con, Statement st, ResultSet res) {
		try {
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
