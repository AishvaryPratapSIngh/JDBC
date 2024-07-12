package com.ACID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetAutoCommitess {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		String query1 = "INSERT INTO `employee`(`id`,`name`,`email`,`department`,`salary`)"
				+ "VALUES(99,'ji','kk','k@hmail.com',8964)";
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			st = con.createStatement();
			int res = st.executeUpdate(query1);
			System.out.println(res);
			con.rollback();
//			con.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
