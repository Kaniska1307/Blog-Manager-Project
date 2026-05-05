package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* This class is used to show the login page content
*
* @author Abinaya R
* @since 28-10-2022 - initial draft
* @version 1.1
*/

public class DBConnection {
	public Connection connect() throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver"); // step1 load the driver class
		// step2 create the connection object
		Connection con = null;// step2 create the connection object
		try {
			//connection establishment 
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + "(DESCRIPTION =" + "(ADDRESS =" + "(PROTOCOL = TCP)"
							+ "(HOST = 192.168.1.12)" + "(PORT = 1521)" + ")" + "(CONNECT_DATA ="
							+ "(SERVER = dedicated)" + "(SERVICE_NAME = domdev.hotcourses.co.in)" + ")" + ")",
					"HOT_ACADEMY", "ha11marks");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return con;

	}
}