package com.ACID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ACIPDApplying {
	static Connection con = null;
	static PreparedStatement ps = null;
	static String url = "jdbc:mysql://localhost:3306/jdbc";
	static String username = "root";
	static String password = "aish0305";
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			transaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void transaction() throws SQLException {
		System.out.println("Enter the sender name");
		String name = sc.nextLine();
		System.out.println("Enter the reciver name");
		String resiver = sc.nextLine();
		System.out.println("Enter the amount");
		int amount = sc.nextInt();
		sc.nextLine();
		int n1 = update(-amount, name);
		int n2 = update(amount, resiver);
		confirm(n1, n2);
	}

	public static int update(int amount, String name) throws SQLException {
		String query = "UPDATE employee1 SET `salary` = `salary`+? WHERE `name`=?";
		ps = con.prepareStatement(query);
		ps.setInt(1, amount);
		ps.setString(2, name);
		return ps.executeUpdate();

	}

	public static void confirm(int n1, int n2) throws SQLException {
		if (n1 == n2) {
			System.out.println("Did you want to send the amount (yes/no)");
			String ask = sc.next();
			if (ask.equalsIgnoreCase("yes")) {
				con.commit();
				System.out.println("Successful");
			} else {
				con.rollback();
				System.out.println("Transaction Cancel");
			}
		} else {
			con.rollback();
			System.out.println("Failure");
		}
	}

}
