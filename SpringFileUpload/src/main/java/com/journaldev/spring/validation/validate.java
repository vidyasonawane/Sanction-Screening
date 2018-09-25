package com.journaldev.spring.validation;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.journaldev.spring.database.database_class;
import com.journaldev.spring.screening.screening;
public class validate
{

	public static String[] validation(String user_id)
	{
		System.out.println("*************** In validation function *************** ");
		//FileReader fileReader = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		ZipOutputStream ZipOut = null;
		// InputStream fis = null;
		String[] status = new String[5];
		try
		{
			String transaction_id;
			File folder = new File("D:\\citiBridge\\polling\\tempFiles");
	  
			if (folder.isDirectory()) 
			{
				for (File file : folder.listFiles()) 
				{
					Transaction tx = new Transaction();
					StringBuilder error = new StringBuilder();
        	 
					System.out.println("=============New File================");
					InputStream ExcelFileToRead = new FileInputStream(file);
					XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
					XSSFSheet sheet = wb.getSheetAt(0);
     		
					XSSFRow row; 
					XSSFRow fieldNames; 
     				XSSFCell cell;

     				Iterator rows = sheet.rowIterator();
     				System.out.println("title row : ");
     		
     				if(rows.hasNext()) 
     				{
     					System.out.println("first row Field names : ");
     					fieldNames = (XSSFRow) rows.next(); 
     				}
     				
     				System.out.println("\nValue rows: ");
     				
     				while (rows.hasNext())
     				{
     					System.out.println("New row :");
	     			    			
     					int flag=0;
     					row=(XSSFRow) rows.next();
     					Iterator cells = row.cellIterator();
     			
     					while (cells.hasNext())
     					{
     						System.out.println("\n");
     						cell=(XSSFCell) cells.next();
     						System.out.println("Cell No : "+cell.getColumnIndex());

     						// Print the content on the console
     						System.out.println();
     						//System.out.print(cell+" ");
     						String cellValue = cell.getStringCellValue();
     						System.out.println(cellValue); //Read File cellValue By cellValue
     	           	  
     						switch(cell.getColumnIndex())
     						{
     						
     						case 0:
     							System.out.println("Alphanumeric : "+cellValue.matches("[A-Za-z0-9]+"));
     							System.out.println("Field length : "+cellValue.length());
     							transaction_id= cellValue.toString();
     							tx.setTransaction_id(cellValue);
     	           		  
     							if(cellValue.length()!=12)
     							{
     								flag=1;
     								error=error.append("invalid transaction Id");
     							}
     							break;
     	           		 
     						case 1:
     							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     							sdf.setLenient(false);
     							tx.setTx_date(cellValue);
     							try
     							{
     								Date d=sdf.parse(cellValue);
     								System.out.println(d+"	date");
     								System.out.println(cellValue+"	user input of date");
     							}
     							catch(Exception e)
     							{
     								System.out.println("Date is in wrong format");
     								flag=1;
     	           			  
     								error=error.append("invalid Date format");
     							}
     							break;
     	           		  
     						case 2:
	     	           		  System.out.println("Alphanumeric : "+cellValue.matches("[A-Za-z0-9 ]+"));//returns true or false value
	     	         		  System.out.println("Field length : "+cellValue.length());
	     	         		  
	     	         		  tx.setPayer_name(cellValue);
	     	         		  
	     	         		  if(cellValue.length()>35)
	     	         		  {
	     	         			  System.out.println("Invalid name");
	     	         			  flag=1;
	     	         			  error = error.append("invalid Payer name");
	     	         		  }
	     	         		  break;
     	           	             		  
	     	           	  case 3:
	     	           		  
	     	           		  System.out.println("Alphanumeric : "+cellValue.matches("[A-Za-z0-9]+"));//returns true or false value
	     	           		  System.out.println("Field length : "+cellValue.length());
	     	       		  
	     	           		  tx.setPayer_acc(cellValue);
	     	       		  
	     		       		  if(cellValue.length()>12)
	     		       		  {
	     		       			  System.out.println("Invalid account number");
	     		       			  flag=1;
	     		     			  error = error.append("invalid Payer account name");
	     		              }
	     		       		  break;
     	       		  
	     	           	case 4:
	     	           		System.out.println("Alphanumeric : "+cellValue.matches("[A-Za-z0-9 ]+"));//returns true or false value
	     	           		System.out.println("Field length : "+cellValue.length());
	     	           		tx.setPayee_name(cellValue);
	     	           		if(cellValue.length()>35)
	     	           		{
	     	     			  System.out.println("Invalid name");
	     	     			  flag=1;
	     	     			  error = error.append("invalid Payee account name");
	     	           		}
	     	           		break;
     	     		  
	     	           	  case 5:
	     	           		  System.out.println("Alphanumeric : "+cellValue.matches("[A-Za-z0-9]+"));//returns true or false value
	     	           		  System.out.println("Field length : "+cellValue.length());
	     	           		  tx.setPayee_acc(cellValue);
	     	           		  if(cellValue.length()>12)
	     	           		  {
	     	           			  System.out.println("Invalid account number");
	     	           			  flag=1;
	     	           			  error = error.append("invalid Payee account name");
	     	           	      }
	     	       		  break;
     	           		            	  
	     	           	  case 6:
	     	             		tx.setAmount(cellValue);
	
	     	             		cellValue.matches("[0-9.]+");
	     	           		    if(cellValue.length()==13 && (cellValue.indexOf(".")==10))
	     	           		    {
	     	           			  System.out.println("correct INR format");
	     	           		    }
	     		           		 else
	     		           		 {
	     		           			 System.out.println("incorrect INR format");
	     		
	     		           			 flag=1;
	     		           			 error=error.append("incorrect INR format");
	     		           		 }
	     		           		 break;
	     		           		 
     						}//end of switch
     						
     					}//end of inner while cell
     			     			
      			if(flag==0)
                {
               	 
                    
                    tx.setUser_id(user_id);
                    database_class db=new database_class();
                    db.insert_transaction(tx);
                    System.out.println("Pass validation");
                       
                    screening sc = new screening();
                   status[0]= sc.screenFinal(tx.getPayer_name(),tx.getPayee_name(), tx.getTransaction_id());//call 
                   status[1]=tx.getTransaction_id();
                   status[2]=tx.getPayer_name();
                   status[3]=tx.getPayee_name();
                   if(status[0].equals("fail :both"))
                   {
                	   status[4]="Transaction Failed due to Payer and Payee are in sanction list.";
                   }
                   if(status[0].equals("fail :payer"))
                   {
                	   status[4]="Transaction Failed due to Payer is in the sanction list.";
                   }
                   if(status[0].equals("fail :payee"))
                   {
                	   status[4]="Transaction Failed due to Payee is in the sanction list.";
                   }
                   if(status[0].equals("pass"))
                   {
                	   status[4]="Transaction Sanctioned";
                   }
                 
                }
                
                else
                {
               	 System.out.println("discard file");
                 	
                 	 System.out.println("Fail validation");
                 	 status[0]="Fail validation";
                 	 status[1]=tx.getTransaction_id();
                     status[2]=tx.getPayer_name();
                     status[3]=tx.getPayee_name();
                     status[4]="Transaction failed due to invalid data.";
                 	 System.out.println("/*/*/*/*/*");
             
                }
     		}//end of row while
     		
     		System.out.println("File Deleted : "+file.delete()); 
     		
           	}//end of for loop
		
		} //end of if
	  
	 }
    catch(Exception e) 
		{
 		e.printStackTrace();
		}
  
	return status;
}

}