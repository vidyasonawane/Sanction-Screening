package com.journaldev.spring.screening;

import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.journaldev.spring.database.database_class;

public class screening
{
	database_class oracleconn=new database_class();
    ArrayList Sl=new ArrayList();//sanction list
    String sanction_name;
    int exact=0;
    
    int flag=0,flag3=0;
    String match;
    float threshold=(float) 0.15;
    float min=(float) 1.0;
    float ratio;
    int count=0;
    String scr_status=null;

    public String screenFinal(String s1, String s2, String txid)
    {
       	database_class o1=new database_class();
		ArrayList<String> Sl=o1.conn();
		int fpayee=0,fpayer=0;
		float min = (float) 1.0;
		String match = null;
		for(String s : Sl) 
		{
			StringUtils.lowerCase(s1);	//payer name
			StringUtils.lowerCase(s); //name from sanction list
			System.out.println(s1+ " " +s);
			
			int l1=s1.length();
			int ls=s.length();
			
			int div=Math.max(l1,ls);
			int distance = StringUtils.getLevenshteinDistance(s1, s);
			System.out.println("DISTANCE: " + distance);
			
			if(distance==0.0)
		     {
				System.out.println("EXACT MATCH FOUND IN SACTION LIST and SCREENING FAIL");
				System.out.println("screen table entry");
				fpayer=1;
				break;
		     }
			
			ratio=(float)distance/div; 	 
		    System.out.println("Ratio:"+ratio);
		    System.out.println();
		    if(ratio<min)
		    {
			    	 min=ratio;
			    	 match=s;
			}
			
		}
		
		 if(min<=threshold) //threshold is 0.15 , false positive
		 {
					fpayer=1;
		 }
		
		min = 1;
		match = null;
		for(String s : Sl) 
		{
			s2=StringUtils.lowerCase(s2);	//payer name
			s=StringUtils.lowerCase(s); //name from sanction list
			System.out.println(s2+ " " +s);
			
			int l2=s2.length();
			int ls=s.length();
			
			int div=Math.max(l2,ls);
			int distance = StringUtils.getLevenshteinDistance(s2, s);
			System.out.println("DISTANCE: " + distance);
			
			if(distance==0.0)
		     {
		    	 	System.out.println("EXACT MATCH FOUND IN SACTION LIST and SCREENING FAIL");
		    	 	fpayee=1;
					break;
		     }
			
			ratio=(float)distance/div; 
		    	 
		    System.out.println("Ratio:"+ratio);
		    System.out.println();
		    if(ratio<min)
		    {
		    	min=ratio;
			    match=s;
			}
			
		}
		
		if(min<=threshold) //threshold is 0.15 false positive
		 {
					fpayee=1;
		
		 }
		 if(fpayer==1 && fpayee==1)
		 {
			 o1.insert_sactionstatus_transaction(txid, "fail :both");
			 return "fail :both";
		 }
		 else if(fpayer==1 )
		 {
			 o1.insert_sactionstatus_transaction(txid, "fail :payer");
			 return "fail :payer";
		 }
		 else if(fpayee==1 )
		 {
			 o1.insert_sactionstatus_transaction(txid, "fail :payee");
			 return "fail :payee";
		 }
		else {
		 System.out.println("NOT IN SANCTION LIST\n SCREENING PASS");
		
		    	o1.insert_sactionstatus_transaction(txid,"pass");
		    	 return "pass";
		    
		}
		
	}
	public void setSanctionList(ArrayList al) {
		// TODO Auto-generated method stub
		System.out.println(al);
		Sl.addAll(al);
	}
	
}
