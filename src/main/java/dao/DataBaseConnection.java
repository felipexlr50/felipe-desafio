package dao;

import java.sql.*;

public class DataBaseConnection {
	
	private final String DB_URL = "jdbc:mysql://localhost/desafio";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String USER = "root";
	private final String PASS = "";
	 
	 
	 public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
			
			try {
				Class.forName(DRIVER);
				return DriverManager.getConnection(DB_URL,USER,PASS);			
			} catch(ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	 
	

}
