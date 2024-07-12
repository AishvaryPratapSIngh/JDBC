package com.userInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpadateUsinguser {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet res = null;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			// Prompt user for input
			System.out.println("What column do you want to update? (name, email, department, salary)");
			String col = sc.next();

			System.out.println("Enter the new value for column '" + col + "': ");
			String newValue = sc.next();

			System.out.println("Enter the ID of the employee to update: ");
			int id = sc.nextInt();

			// Prepare the SQL query based on user input
			String query = "UPDATE employee SET " + col + " = ? WHERE id = ?";
			ps = con.prepareStatement(query);

			// Set parameters based on column type
			if (col.equalsIgnoreCase("salary")) {
				ps.setInt(1, Integer.parseInt(newValue));
			} else {
				ps.setString(1, newValue);
			}
			ps.setInt(2, id);

			// Execute the update
			int rowsUpdated = ps.executeUpdate();
			System.out.println(rowsUpdated + " record(s) updated successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				st.close();
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
