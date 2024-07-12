package com.userInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdatesSalarys {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet res = null;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String query1 = " select * from employee";
		String query = "Update `employee` set `salary` = `salary`+ ? where `department` = ?";
		int salary = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("before updation");
			display(con, res, query1, st);
			System.out.println("How much salary you want to increase = ");
			try {
				salary = sc.nextInt();
			} catch (InputMismatchException e1) {
				System.out.println("Enter the integer value ");
			}

			System.out.println("Enter the department of slary you wantr to increase");
			String derpatment = sc.next();
			ps = con.prepareStatement(query);
			ps.setInt(1, salary);
			ps.setString(2, derpatment);
			int k = ps.executeUpdate();
			System.out.println(k);
			System.out.println("After updation");
			display(con, res, query1, st);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(con, ps, sc, res);
	}

	public static void close(Connection con, PreparedStatement ps, Scanner sc, ResultSet res) {
		try {
			ps.close();
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

	static void display(Connection con, ResultSet res, String query, Statement st) {
		try {
			st = con.createStatement();
			res = st.executeQuery(query);
			System.out.println("--------------------------------------------------");
			// Print table rows
			while (res.next()) {
				System.out.printf("|%-3d|%-10s|%-10s|%-10.2f%n", res.getInt("id"), res.getString("name"),
						res.getString("department"), res.getDouble("salary"));
			}
			System.out.println("-------------------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
