package com.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class UpdateData {

	static Connection myCon=null;
	static Statement myStmt=null;
	static ResultSet myRs=null;
	
	static String dburl = "jdbc:mysql://localhost:3306/demo";
	static String user = "root";
	static String password = "Goodjob@10";	
	
	public static void main(String[] args) throws SQLException{
		
		
		try {
			// Get a connection
			myCon = DriverManager.getConnection(dburl, user, password);
			
			myStmt = myCon.createStatement();
			
			System.out.println("Update employees tables");
			System.out.println(" ");
			
			//executeUpdate --> for sql update,inserts and deletes (DML operations)
			
			//helper method to print employee info before update
			System.out.println("Before the update");
			before(myCon,"Divya","Paliwal");
			
			System.out.println(" ");
			
			System.out.println("After the update");
			int rowseffected = myStmt.executeUpdate("update employees "+
			"set email = 'divyapaliwal000@gmail.com'"+
			"where last_name = 'Paliwal' and first_name = 'Divya'");
			
			//verify with the result set
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			System.out.println("After the update");
			while(myRs.next()){
				System.out.println(myRs.getString("last_name")+" "+myRs.getString("first_name")+" "+myRs.getString("email"));
				
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

	private static void before(Connection myCon, String string1, String string2) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			//prepared statement
			stmt = myCon.prepareStatement("select last_name, first_name, email from employees where last_name = ? and first_name = ?");
			
			stmt.setString(1, string2);
			stmt.setString(2, string1);
			
			//execute sql query
			rs = stmt.executeQuery();
			
			//Process result set
			while(rs.next()){
				String ln=rs.getString("last_name");
				String rn=rs.getString("first_name");
				String e=rs.getString("email");
				
				System.out.println(ln+" "+rn+" "+e);
			}	
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		close(stmt,rs);
	}

}

	private static void close(PreparedStatement stmt, ResultSet rs) throws SQLException {
		if(stmt!=null){
			stmt.close();
			
		}if(rs!=null){
			rs.close();
		}
		
	}

}