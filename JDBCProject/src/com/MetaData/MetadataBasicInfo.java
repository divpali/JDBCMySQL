package com.MetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class MetadataBasicInfo {

	public static void main(String[] args) throws SQLException{
		
		String dburl = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String pass = "Goodjob@10";
		
		Connection myCon = null;
		
		try{
			
			//get the connection
			
			myCon = DriverManager.getConnection(dburl,user,pass);
			
			//get the metadata
			DatabaseMetaData dbm = myCon.getMetaData();
			
			//get info about metadata
			System.out.println("Product Name : "+dbm.getDatabaseProductName()); 
			System.out.println("Product Version : "+dbm.getDatabaseProductVersion());
			System.out.println("Driver Name : "+dbm.getDriverName());
			System.out.println("Driver version : "+dbm.getDriverVersion());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(myCon);
		}

	}

	private static void close(Connection myCon) throws SQLException{
		if(myCon!=null){
			myCon.close();
		}
	}

}
