package com.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Allinfo {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cs = null;
		ResultSet res = null;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			cs = con.prepareCall("{call all_info(?)}");
			System.out.println("Enter the salary ");
			int salary = sc.nextInt();
			cs.setInt(1, salary);
			cs.execute();
			res = cs.getResultSet();
			while (res.next()) {
				System.out.println(res.getInt("id") + " " + res.getString("name") + " " + res.getString("department")
						+ " " + res.getInt("salary"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				cs.close();
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

			sc.close();

		}

	}

}
