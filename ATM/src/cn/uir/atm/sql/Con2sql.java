package cn.uir.atm.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Con2sql 
{
	public static Connection con2sql()
	{
		Connection conn=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "0710");

			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	

}



