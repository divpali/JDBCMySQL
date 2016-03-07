package com.example;

import java.sql.*;

import javax.sql.*;

/*
 *  @author divya
 */
public class JDBCTest {

	public static void main(String[] args) throws SQLException {
		
		Connection myCon = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "Goodjob@10";
		
		try{
			//Get a connection to database
			myCon = DriverManager.getConnection(dburl, user, password);
			
			System.out.println("database connection successful");
			
			//create a statement
			myStmt = myCon.createStatement();
			
			//execute sql query and store result in resultset
			myRs = myStmt.executeQuery("select * from employees");
			
			//process result set
			while(myRs.next()){
				System.out.println(myRs.getString("first_name") +" "+myRs.getString("last_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(myCon != null){
				myCon.close();
			}if(myStmt != null){
				myStmt.close();
			}if(myRs != null){
				myRs.close();
			}
		}
		

	}

}
