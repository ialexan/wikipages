package wiki.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDb {

    Connection connected;

    // Each Class will use this class to connect to the database
    // so that you change database name in one place
    public ConnectDb(){
        String database = "jdbc:mysql://ec2-174-129-139-107.compute-1.amazonaws.com:3306/wiki";
        String username = "ialexanwiki";  
        String password = "3Z!xRqZ*";
//        
//        String database = "jdbc:mysql://192.168.1.116:3306/cs320stu26";
//        String username = "cs320stu26";  
//        String password = "3Z!xRvY*";
        
        
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
