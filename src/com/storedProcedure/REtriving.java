package com.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//Retrieving the data using the strored producer
public class REtriving {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement callablest = null;
		ResultSet res = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			callablest = con.prepareCall("{call emp2}");
			res = callablest.executeQuery();
			while (res.next()) {
				System.out.println(res.getInt("id") + " " + res.getString("name") + " " + res.getString("department")
						+ " " + res.getInt("salary"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
