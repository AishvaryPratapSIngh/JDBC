package com.tap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Launch {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet res = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String query = "select * from jdbc.employee";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.createStatement();
			res = st.executeQuery(query);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		result(res);
		close(con, st, res);
	}

	static void result(ResultSet res) {
		try {
			while (res.next()) {
				System.out.println(res.getInt("id") + " " + res.getString("name") + " " + res.getString("department")
						+ " " + res.getInt("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void close(Connection con, Statement st, ResultSet res) {
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
