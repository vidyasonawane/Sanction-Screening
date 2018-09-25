package com.journaldev.spring.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {

public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Music\\sts-bundle\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8099/SpringFileUpload/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//*[@id=\"usernameid\"]")).sendKeys("citi");
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"passwordid\"]")).sendKeys("citi");
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(1000);
		
		//driver.switchTo().alert().accept();  //at the end
		Thread.sleep(2000);
		
		//Negative test :password wrong
		/*driver.findElement(By.xpath("//*[@id=\"passwordid\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"passwordid\"]")).sendKeys("hello");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(3000);*/
		
		//2nd page
		driver.findElement(By.xpath("/html/body/form/div/input[1]")).sendKeys("template.xlsx");
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(2000);
		
		String filepath = "D:/citiBridge/polling/f1.xlsx";
		WebElement chooseFile = driver.findElement(By.id("fileuploadid"));
		Thread.sleep(1000);
		chooseFile.sendKeys(filepath);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(1000);
			
		
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/form/div/input")).sendKeys("citi");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/form/div/button")).click();
		Thread.sleep(1000);
		
		
		driver.switchTo().alert().accept();
		
		
		driver.close();

		
	
	}

}
