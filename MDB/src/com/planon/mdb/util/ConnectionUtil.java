package com.planon.mdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static String url = "jdbc:ucanaccess://" + "C:/Users/saveer/Desktop/MDB/Caliber.mdb"
			+ ";newdatabaseversion=V2016";
	private static Connection connection;

	public static Connection openConnection() throws SQLException {

		connection = DriverManager.getConnection(url);
		return connection;

	}
}
