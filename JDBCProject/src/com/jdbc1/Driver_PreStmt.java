package com.jdbc1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Driver_PreStmt {

	public static void main(String[] args) throws SQLException{
		
		Connection myCon = null;
		PreparedStatement ps = null;
		ResultSet myRs = null;
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "Goodjob@10";
		
		//get a connection
		myCon = DriverManager.getConnection(dburl,user,password);
		
		//prepare statement
		ps = myCon.prepareStatement("select * from employees where salary > ? and department = ?");
		
		ps.setString(1, "60000.00");
		ps.setString(2, "HR");
		
		
		//reuse the prepared statement
		
		ps.setString(1, "60000.00");
		ps.setString(2, "Engineering");
		
		//get result
		myRs = ps.executeQuery();
		
		//display resultset
		while(myRs.next()){
			System.out.println(myRs.getString("salary")+" "+myRs.getString("department"));
		}
	}

}
