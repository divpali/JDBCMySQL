package com.jdbc1;

import java.sql.*;

import javax.sql.*;


public class InsertData {
	
	public static void main(String[] args) throws SQLException {
		
		Connection myCon=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "Goodjob@10";
		
		try {
			// Get a connection
			myCon = DriverManager.getConnection(dburl, user, password);
			
			myStmt = myCon.createStatement();
			
			System.out.println("Insert employees");
			
			//executeUpdate --> for sql update,inserts and deletes (DML operations)
			
			int rowseffected = myStmt.executeUpdate("insert into employees"+"(last_name,first_name,email,department,salary)"+"values"+"('Paliwal','Divya','div.pali@gmail.com','engineering','40000.00')");
			
			//verify with the result set
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			
			while(myRs.next()){
				System.out.println(myRs.getString("last_name")+" "+myRs.getString("first_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(myCon!=null){
				myCon.close();
			}
			if(myStmt!=null){
				myStmt.close();
			}
			if(myRs!=null){
				myRs.close();
			}
		}

	}

}
