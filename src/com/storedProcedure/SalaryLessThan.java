package com.storedProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class SalaryLessThan {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement callst = null;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		int res = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			callst = con.prepareCall("{call salaryes(?)}");
			System.out.println("Enter the salary ");
			int sal = sc.nextInt();
			callst.setInt(1, sal);
			callst.registerOutParameter(1, Types.INTEGER);
			callst.execute();
			res = callst.getInt(1);
			System.out.println("Number of employees with salary <= " + sal + ": " + res);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				callst.close();
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
