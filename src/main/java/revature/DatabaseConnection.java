package revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static String url;
	
	protected Connection connection;
	
	private int port = 5432;
	
	private String databaseName = "postgres";
	
	private String databaseUser = "postgres";
	
	private String databasePassword = "password1234";
	
	private String host = "postgres82322.c0rmfsc1zrep.us-east-2.rds.amazonaws.com";
	
	DatabaseConnection()
	{
		try
		{
		//Class.forName("com.google.cloud.sql");
		
		url = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
		
			connection = DriverManager.getConnection(url, databaseUser, databasePassword);
			// System.out.println("Connection Success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Failure: " + url);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return connection;
	}
}
