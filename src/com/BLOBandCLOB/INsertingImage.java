package com.BLOBandCLOB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class INsertingImage {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);
		String query = "UPDATE `employee` SET `dp`=? where `id` = ?";
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			ps = con.prepareStatement(query);
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\hp\\eclipse-workspace\\JDBC\\Images\\WhatsApp Image 2024-05-20 at 3.56.34 PM.jpeg");
			System.out.println("Enter the id in which you want to inser the image");
			int id = sc.nextInt();
			ps.setInt(2, id);
			ps.setBinaryStream(1, fis);
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
