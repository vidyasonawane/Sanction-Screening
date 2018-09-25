package com.journaldev.spring.database;

import java.sql.*;
import java.util.ArrayList;

import com.journaldev.spring.validation.Transaction;
public class database_class
{
	public ArrayList conn()
	{
		
		String s;
		ArrayList<String> Al=new ArrayList<String>();
		try 
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
				  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","system","root");  
			//step3 create the statement object  
			Statement stmt=null;
			stmt=con.createStatement(); 
				 
			//step4 execute query
			ResultSet rs1=stmt.executeQuery("select name from sanctionlist");  
			
			while(rs1.next())
			{
				s=rs1.getString("NAME");
				System.out.println(s);
				Al.add(s);
			}
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return Al;
	}
	
	public void insert_transaction(Transaction tx) 
	{
		System.out.println("inserting");
		try
		{
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
		
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
						
			System.out.println("Connection established");
			
			String query = "INSERT INTO TRANSAC VALUES (?,to_date(?, 'dd/mm/yyyy'),?,?,?,?,?,?,?)";
				  			PreparedStatement ps = con.prepareStatement(query);
						  			
			ps.setString(1, tx.getTransaction_id());
			ps.setString(2, tx.getTx_date() );
			ps.setString(3, tx.getPayer_name());
			ps.setString(4, tx.getPayer_acc());
			ps.setString(5, tx.getPayee_name());
			ps.setString(6, tx.getPayee_acc());
			ps.setString(7, tx.getAmount());
			ps.setString(8, tx.getUser_id());
			ps.setString(9, "Screening in progress");
		
			ps.execute();
			ps.close();
			
			con.close(); 
							
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("In catch block of transaction table");
		}
	}
	
	public void insert_sactionstatus_transaction(String TID, String screenStatus) 
	{
		System.out.println("status"+screenStatus);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			System.out.println("Connection established");
		
			String updateTableSQL = "UPDATE TRANSAC SET SCREENSTATUS = ? "
	                  + " WHERE TRANSACTION_ID = ?";
			PreparedStatement ps1 = con.prepareStatement(updateTableSQL);
			
			ps1.setString(1, screenStatus);
			ps1.setString(2, TID);
			
			ps1.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			
			if (ps1 != null) 
			{
				ps1.close();
			}
			con.close(); 
						
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}


