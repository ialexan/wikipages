package wiki.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDb {

	Connection connected;

	// Each Class will use this class to connect to the database
	// so that you change database name in one place
	public ConnectDb(){
		String database = "jdbc:mysql://<host>:3306/wiki";
		String username = "ialexan";  
		String password = "*******";


		try
		{
			connected = DriverManager.getConnection( database, username, password );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
	}

	public Connection getConnected() {
		return connected;
	}

}
