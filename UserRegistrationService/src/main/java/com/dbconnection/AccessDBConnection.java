package com.dbconnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class AccessDBConnection {

	    public static Connection db;
	    
	    public static Connection getDbCon() throws IOException {
	    	InputStream inputStream;
			//Reading values from th
	    	Properties props = new Properties();
			String propFileName = "db.properties";
			inputStream = Thread.currentThread()
				    .getContextClassLoader().getResourceAsStream(propFileName);
			System.out.println("Input Stream"+ inputStream);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	    	
			String driver = props.getProperty("jdbc.driver");
			Connection conn=null;
			try{   
			if (driver != null) {
			       Class.forName(driver).newInstance() ;
			   }

			   String url = props.getProperty("jdbc.url");
	            conn = (Connection)DriverManager.getConnection(url);
	        }
	        catch (Exception sqle) {
	            sqle.printStackTrace();
	        }
			return conn;
	    }
	}
	
