package com.capgemini.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {

	public static Connection connectdb()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Krishna$97");
			return conn;
		}
		catch(Exception e)
		{
			return null;
		}
	}


}
