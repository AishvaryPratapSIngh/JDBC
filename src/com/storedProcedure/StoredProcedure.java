package com.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class StoredProcedure {
	public static void main(String[] args) {
		Connection con = null;
		Scanner sc = new Scanner(System.in);
		CallableStatement callable = null;
		String classes = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		try {
			Class.forName(classes);
			con = DriverManager.getConnection(url, username, password);
			callable = con.prepareCall("{call emp(?,?)}");
			System.out.println("Enter the department");
			String dept = sc.next();
			callable.setString(1, dept);
			callable.registerOutParameter(2, Types.INTEGER);
			callable.execute();
			int res = callable.getInt(2);
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				callable.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			sc.close();
		}

	}

}
