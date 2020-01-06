package com.salesforce.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xdgf.usermodel.section.CharacterSection;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Utility_Libraries {
	
  static String Report_Folder_path = "C:\\Reporting\\Report"+Utility_Libraries.fTimestamp();
  static String Report_Excel_path = Report_Folder_path+"\\Excelreport"+Utility_Libraries.fTimestamp()+".xls";
  ExtentHtmlReporter htmlReporter;
  static ExtentReports extent;
  
  public static WebDriver fgetBrowser(String strBrowserName, WebDriver driver)
	{
		switch(strBrowserName.toLowerCase())
		{	
		//launch in firefox
		case "firefox":
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\Drivers\\geckodrivers.exe");
		driver=new FirefoxDriver();
		break;
		//------------------------------------------------
		//launch in chrome
		case "chrome":
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
		break;
		//------------------------------------------------		
		//launch in internetexplore
		case "internetexplore":
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\driver\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		
		case "ie":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\driver\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		//------------------------------------------------
		default:
			System.out.println("Driver is not found "+strBrowserName);
		}
		return driver;
	}
	
	public ExtentReports fReport()
	{	
		   ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Report_Folder_path + "\\testReport.html");
	       extent = new ExtentReports();
	       extent.attachReporter(htmlReporter);
	       extent.setSystemInfo("OS", "OS");
	       extent.setSystemInfo("Browser", "browser");
	       //htmlReporter.config().setChartVisibilityOnOpen(true);
	       htmlReporter.config().setDocumentTitle("Extent Report Demo");
	       htmlReporter.config().setReportName("Test Report");
	       //htmlReporter.config().setTestViewChartLocation(CharacterSection.TOP);
	       htmlReporter.config().setTheme(Theme.STANDARD);
	       return extent;
	}
	
   public static String fTimestamp()
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String time = dateFormat.format(now);
		return time.replace("-", "");
	}
	 
   public static String fScreenReport(WebDriver scrdriver) throws Throwable
	{
    	File source_image = ((TakesScreenshot)scrdriver).getScreenshotAs(OutputType.FILE);
		String Image_path = Report_Folder_path+"\\Screeshot"+Utility_Libraries.fTimestamp()+".png";
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
			By xpath = By.xpath(Utility_Libraries.fGetElement(Keys, FileName));
			return xpath;		
		case "id":
			By id = By.xpath(Utility_Libraries.fGetElement(Keys, FileName));
			return id;	
		case "name":
			By name = By.xpath(Utility_Libraries.fGetElement(Keys, FileName));
			return name;
		case "class":
			By class_ = By.xpath(Utility_Libraries.fGetElement(Keys, FileName));
			return class_;
		case "css":
			By Source = By.xpath(Utility_Libraries.fGetElement(Keys, FileName));
			return Source;
		}
		return null;
	}
}
