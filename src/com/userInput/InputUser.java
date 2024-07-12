package com.userInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InputUser {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		ResultSet res = null;
		PreparedStatement pt = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String query = "select * from `employee` where `department`=? ";
		System.out.println("Enter the department you want the data ");
		String k = sc.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			con = DriverManager.getConnection(url, username, password);
			pt = con.prepareStatement(query);
			pt.setString(1, k);
			res = pt.executeQuery();
			while (res.next()) {
				System.out.println("ID: " + res.getInt("id"));
				System.out.println("Name: " + res.getString("name"));
				System.out.println("Department: " + res.getString("department"));
				System.out.println("Salary: " + res.getDouble("salary"));
				System.out.println("----------------------");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(con, res, pt, sc);

	}

	static void close(Connection con, ResultSet res, PreparedStatement pt, Scanner sc) {
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
		try {
			pt.close();
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
