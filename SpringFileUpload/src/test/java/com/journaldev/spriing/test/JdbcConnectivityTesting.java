package com.journaldev.spriing.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JdbcConnectivityTesting {
	
	String s;
	ArrayList<String> Al=new ArrayList<String>();
	Statement stmt=null;
	Connection con=null;

	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("@Before");
		
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
		//step2 create  the connection object  
		con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","system","root");  
		//"jdbc:oracle:thin:@localhost:1521:xe","system","vidya");   //test case fail
		
		//step3 create the statement object  
		stmt=con.createStatement(); 
		
	
	}
	

	@Test
	public void test() {
		
		try 
		{
			//step4 execute query
			ResultSet rs1=stmt.executeQuery("select name from sanctionlist");  
			
			while(rs1.next())
			{
				s=rs1.getString("NAME");
				System.out.println(s);
				Al.add(s);
			}
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	}


	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
		stmt.close();
		con.close();
		
	}
	
	

}
