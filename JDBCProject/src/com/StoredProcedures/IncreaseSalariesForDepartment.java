package com.StoredProcedures;

/*
 * here we call a stored procedure using callable statement and make changes in setting parameters in query of using prepared statement. 
 */

import java.sql.*;

public class IncreaseSalariesForDepartment {

	public static void main(String[] args) throws SQLException {
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "Goodjob@10";		
		
		Connection myCon = null;
		CallableStatement myStmt = null;
		
		//get a connection
		myCon = DriverManager.getConnection(dburl,user,password);
		
		String theDepartment = "Engineering";
		int theIncreaseAmount = 10000;
		
		//show salaries before
		System.out.println("Show Before salaries\n");
		showDetails(myCon,theDepartment);
		
		//prepare stored procedure call
		myStmt = myCon.prepareCall("{call increase_salaries_for_department(?, ?)}");
		
		//set parameters
		myStmt.setString(1,theDepartment);
		myStmt.setDouble(2,theIncreaseAmount);
		
		//call stored procedure
		myStmt.executeQuery();
		
		//show salaries after
		System.out.println("");
		System.out.println("\nShow after salaries\n");
		showDetails(myCon,theDepartment);
		
		
		
	}

	private static void showDetails(Connection myCon, String theDepartment) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			//prepare statement
			ps=myCon.prepareStatement("select * from employees where department=?");
			
			ps.setString(1,theDepartment);
			
			//execute sql query
			rs=ps.executeQuery();
				
			//process result set
			while(rs.next()){
				String ln=rs.getString("last_name");
				String fn=rs.getString("first_name");
				String dpt=rs.getString("department");
				Double sal=rs.getDouble("salary");
				
				System.out.println(ln+" "+fn+" "+dpt+" "+sal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null){
				ps.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
	}

}
