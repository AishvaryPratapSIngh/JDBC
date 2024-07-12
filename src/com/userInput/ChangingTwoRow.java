package com.userInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ChangingTwoRow {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;

		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String sender = null;
		String receiver = null;
		int amount = 0;
		String query = "UPDATE employee SET salary = CASE " + "WHEN name = ? THEN salary - ? "
				+ "WHEN name = ? THEN salary + ? " + "END " + "WHERE name IN (?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Enter the name of Sender:");
			sender = sc.next();
			System.out.println("Enter the name of the Receiver:");
			receiver = sc.next();
			System.out.println("Enter the amount:");
			amount = sc.nextInt();

			// Prepare and execute the update statement
			ps = con.prepareStatement(query);
			ps.setString(1, sender);
			ps.setInt(2, amount);
			ps.setString(3, receiver);
			ps.setInt(4, amount);
			ps.setString(5, sender);
			ps.setString(6, receiver);
			ps.addBatch();
			int[] arr = ps.executeBatch();
			System.out.println(" done" + Arrays.toString(arr));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		closeResources(ps, con, sc);
	}

	static void closeResources(PreparedStatement ps, Connection con, Scanner sc) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sc != null)
			sc.close();
	}
}
