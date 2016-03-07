package com.MetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class SchemaInfo {

	public static void main(String[] args) throws SQLException{
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String pass = "Goodjob@10";
		
		Connection myCon = null;
		ResultSet myRs = null;
		
		try{
			myCon = DriverManager.getConnection(dburl,user,pass);
			DatabaseMetaData dbm = myCon.getMetaData();
			
			//get list of tables
			System.out.println("List of  tables");
			System.out.println("--------------------------------------------");
			
			myRs = dbm.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(myRs.next()){
				System.out.println(myRs.getString("TABLE_NAME"));
			}
			
			//get list of columns
			System.out.println("List of  columns");
			System.out.println("--------------------------------------------");
			
			myRs = dbm.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			while(myRs.next()){
				System.out.println(myRs.getString("COLUMN_NAME"));
			}
			
		}catch(Exception e){
			
		}finally{
			close(myCon,myRs);
		}
		

	}

	private static void close(Connection myCon, ResultSet myRs) throws SQLException{
		if(myCon!=null){
			myCon.close();
		}
		if(myRs!=null){
			myRs.close();
		}
	}

}
