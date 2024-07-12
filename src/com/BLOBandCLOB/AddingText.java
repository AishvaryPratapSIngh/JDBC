package com.BLOBandCLOB;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddingText {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			ps = con.prepareStatement("UPDATE `employee` SET `intro`=? WHERE `id`= ?");
			System.out.println("Enter the is number");
			ps.setInt(2, sc.nextInt());
			FileReader fr = new FileReader("C:\\Users\\hp\\eclipse-workspace\\JDBC\\shiv.txt");
			ps.setCharacterStream(1, fr);
			System.out.println(ps.executeUpdate());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
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
