package com.journaldev.spring.controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.journaldev.spring.validation.validate;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getMovie() {

		//model.addAttribute("movie", name);
		return "list1";
	}

	@RequestMapping(value="/validation", method = RequestMethod.GET)
	public String validationcontroller(@RequestParam("uname")String uname, ModelMap model)
	{
		validate v=new validate();
		String[] screenstatus=v.validation(uname);
		System.out.println("status:"+screenstatus[0]);
		System.out.println("Id:"+screenstatus[1]);
		System.out.println("Payer Name:"+screenstatus[2]);
		System.out.println("Payee Name:"+screenstatus[3]);
		model.addAttribute("status",screenstatus[0]);
		model.addAttribute("id",screenstatus[1]);
		model.addAttribute("payername",screenstatus[2]);
		model.addAttribute("payeename",screenstatus[3]);
		model.addAttribute("Reason",screenstatus[4]);
		return "screenstatus";
	
	}
			
	@RequestMapping(value="/Login", method = RequestMethod.GET)
	public String login(@RequestParam("uname")String uname,@RequestParam("pass")String pass, ModelMap model)
	{
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try 
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

			String sql1 = "Select * from LOGIN where USERID=?and PASSWORD=?";

			stmt = con.prepareStatement(sql1);
			stmt.setString(1, uname);
			stmt.setString(2, pass);

			System.out.println(stmt);
			ResultSet rs2 = stmt.executeQuery();

			if (rs2!=null && rs2.next())
			{
				model.addAttribute("movie", "Logged In: "+uname);
				return "upload";
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(stmt != null) 
			{
				try 
				{
					stmt.close();
				} 
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) 
			{
				try 
				{
					con.close();
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			model.addAttribute("movie", "login Unsuccessfull");
			return "list1";
	}
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file,ModelMap model)
	{
		if (!file.isEmpty())
		{
			try 
			{
				byte[] bytes = file.getBytes();
				File dir=new File("D:\\citiBridge\\polling\\tempFiles");

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				model.addAttribute("movie", "You successfully uploaded file :"+name);
				return "uploadstatus";
			}
			catch (Exception e)
			{
				model.addAttribute("movie", "You failed to upload " + name + " => " + e.getMessage());
				return "uploadstatus";
			}
		} 
		else 
		{
			model.addAttribute("movie", "You failed to upload " + name + " because the file was empty.");
			return "uploadstatus";
		}
		
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
//	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
//	public	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
//			@RequestParam("file") MultipartFile[] files) {
//
//		if (files.length != names.length)
//			return "Mandatory information missing";
//
//		String message = "";
//		for (int i = 0; i < files.length; i++) {
//			MultipartFile file = files[i];
//			String name = names[i];
//			try {
//				byte[] bytes = file.getBytes();
//
//				// Creating the directory to store file
//				/*String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "tmpFiles");
//				if (!dir.exists())
//					dir.mkdirs();*/
//				File dir=new File("D:\\citiBridge\\polling\\tempFiles");
//				// Create the file on server
//				File serverFile = new File(dir.getAbsolutePath()
//						+ File.separator + name);
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//
//				logger.info("Server File Location="
//						+ serverFile.getAbsolutePath());
//
//				message = message + "You successfully uploaded file=" + name
//						+ "<br />";
//			} catch (Exception e) {
//				return "You failed to upload " + name + " => " + e.getMessage();
//			}
//		}
//		return message;
//	}
	
	
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files)
	{
		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) 
		{
			MultipartFile file = files[i];
			String name = names[i];
			try 
			{
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
