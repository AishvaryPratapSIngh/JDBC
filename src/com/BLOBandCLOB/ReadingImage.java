package com.BLOBandCLOB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadingImage {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FileOutputStream fos = null;

		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "aish0305";
		String filePath = "C:\\Users\\hp\\eclipse-workspace\\JDBC\\Images\\Untitled.jpg";

		try {
			// 1. Establish a connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			// 2. Prepare the SQL statement to retrieve the BLOB data
			String sql = "SELECT `dp` FROM employee WHERE `id` = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1); // Assuming you're fetching the image for employee with id 1

			// 3. Execute the query
			rs = ps.executeQuery();

			// 4. Process the result set
			if (rs.next()) {
				// Retrieve the BLOB data
				InputStream is = rs.getBinaryStream("dp");
				fos = new FileOutputStream(filePath);

				// Write the BLOB data to a file
				byte[] buffer = new byte[1024];
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				System.out.println("Image has been written to: " + filePath);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (fos != null)
					fos.close();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
