package com.salesforce.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.salesforce.qa.base.TestBase;

public class Gernric_functions extends TestBase {
	static String Report_Folder_path = "C:\\Reporting\\Report"+fTimestamp();
	 public static String fTimestamp()
		{
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
			String time = dateFormat.format(now);
			return time.replace("-", "");
		}
		 
	   public static String fScreenReport() throws Throwable
		{
	    	File source_image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String Image_path = Report_Folder_path+"\\Screeshot"+fTimestamp()+".png";
			File Desti_image = new File(Image_path);
			FileUtils.copyFile(source_image,Desti_image);
			return ""+Desti_image;
		}

	   public static String fGetElement(String Keys,String FileName) throws Throwable
		{
			FileInputStream pagobj =null;
			Properties obj = new Properties();
				try
				{ 
					pagobj = new FileInputStream (System.getProperty("user.dir")+"\\src\\com\\SaleForce\\properties\\" + FileName + ".properties");
				}
				catch (Exception e) 
				{	
					System.out.println("File is not found");
				}
			obj.load(pagobj);
			return obj.getProperty(Keys);
		}
	   
	   public static String[] fVerifyvalue(String[] Array,ExtentTest logger)
	   {
		   for(int i=0;i<=Array.length-1;i++)
		   {
			   Assert.assertNotNull(Array[i].toString(), "Value is present");
			   //logger.log(LogStatus.PASS,Array[i].toString(), "Value is present");
		   }
		   return Array;
	   }
	   
	   public static String[] fVerifyInputvalue(String[] Array,ExtentTest logger)
	   {
		   for(int i=0;i<=Array.length-1;i++)
		   {
			   Assert.assertNotNull(Array[i].toString(), "Input parameter value : ");
			   //logger.log(LogStatus.PASS,"Data Load", "Input parameter value : "+Array[i].toString());
		   }
		   return Array;
	   }
	   
	  
		public String fGetCurrentDate()
		{
			Date date = new Date();  
		    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
		    String strDate = dateformat.format(date); 
		    return strDate;
		}
		
		public String fAddDate(String strDate, int Num, String Condition) throws ParseException
		{
			SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar calender = Calendar.getInstance();
			calender.setTime(dateformat.parse(strDate));
				if(Condition.equalsIgnoreCase("year"))
				{
					calender.add(Calendar.DAY_OF_YEAR, Num);
				}
				else if (Condition.equalsIgnoreCase("month")) 
				{
					calender.add(Calendar.MONTH, Num);
				}
				else if (Condition.equalsIgnoreCase("day")) 
				{
					calender.add(Calendar.DAY_OF_MONTH, Num);
				}
			String newDate = dateformat.format(calender.getTime());
			return newDate;
		}
		
		
		public static By fGetPOMvalue(String Locaters,String Keys,String FileName) throws Throwable
		{
			switch(Locaters.toLowerCase())
			{	
			case "xpath":
				By xpath = By.xpath(fGetElement(Keys, FileName));
				return xpath;		
			case "id":
				By id = By.xpath(fGetElement(Keys, FileName));
				return id;	
			case "name":
				By name = By.xpath(fGetElement(Keys, FileName));
				return name;
			case "class":
				By class_ = By.xpath(fGetElement(Keys, FileName));
				return class_;
			case "css":
				By Source = By.xpath(fGetElement(Keys, FileName));
				return Source;
			}
			return null;
		}
}
