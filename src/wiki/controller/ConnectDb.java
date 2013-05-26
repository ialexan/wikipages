package wiki.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {

	Connection connected;

	// Each Class will use this class to connect to the database
	// so that you change database name in one place
	public ConnectDb() {
		String database = "jdbc:mysql://ip-10-194-175-125:3306/wiki"; // 174.129.139.107
																	// to
																	// connect
																	// from
																	// laptop
		String username = "ialexan";
		String password = "3#oSmf0m";

		try {
			connected = DriverManager.getConnection(database, username,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnected() {
		return connected;
	}

}
