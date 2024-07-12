package com.userInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class InsertionPreparedStatement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		PreparedStatement ps = null;
//		Statement st = null;
//		ResultSet res = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String query1 = "SELECT * FROM `employee`";
		String query2 = "INSERT INTO `employee`(`id`,`name`,`email`,`department`,`salary`) VALUES (?,?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			con = DriverManager.getConnection(url, username, password);

			// Display data before insertion
			System.out.println("Before the insertion");
			displayResults(con, query1);

			// Prepare statement for batch insertion
			ps = con.prepareStatement(query2);
			String choice;
			do {
				System.out.println("Enter the id number of the employee");
				int id = sc.nextInt();
				sc.nextLine(); // consume newline
				ps.setInt(1, id);

				System.out.println("Enter the name of the employee");
				String name = sc.nextLine();
				ps.setString(2, name);

				System.out.println("Enter the email of the employee");
				String email = sc.nextLine();
				ps.setString(3, email);

				System.out.println("Enter the department of the employee");
				String department = sc.nextLine();
				ps.setString(4, department);

				System.out.println("Enter the salary of the employee");
				int salary = sc.nextInt();
				ps.setInt(5, salary);

				// Add to batch
				ps.addBatch();

				System.out.println("Do you want to enter more data? (yes/no)");
				sc.nextLine(); // consume newline
				choice = sc.nextLine();
			} while (choice.equalsIgnoreCase("yes"));

			// Execute batch
			int[] results = ps.executeBatch();
			System.out.println("Batch execution results: " + Arrays.toString(results));

			// Display data after insertion
			System.out.println("After the insertion");
			displayResults(con, query1);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			closeResources(con, ps, sc);
		}
	}

	static void displayResults(Connection con, String query) {
		try (Statement st = con.createStatement(); ResultSet res = st.executeQuery(query)) {
			while (res.next()) {
				System.out.printf("|%-3d|%-10s|%-20s|%-10s|%-10.2f%n", res.getInt("id"), res.getString("name"),
						res.getString("email"), res.getString("department"), res.getDouble("salary"));
			}
			System.out.println("------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void closeResources(Connection con, PreparedStatement ps, Scanner sc) {
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
