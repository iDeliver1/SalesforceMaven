package com.salesforce.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Extent_reporter {
	static String Report_Folder_path = "C:\\Reporting\\Report"+Gernric_functions.fTimestamp();
  static String Report_Excel_path = Report_Folder_path+"\\Excelreport"+Gernric_functions.fTimestamp()+".xls";
  ExtentHtmlReporter htmlReporter;
  static ExtentReports extent;
  static ExtentTest logger;
  
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
	
	public static ExtentReports fReport()
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

	public static ExtentTest CreateRoportname(String Step_details){
			logger = extent.createTest(Step_details);
		return logger;
	}
	
	public static void Report(String Status,String Detail) throws Throwable{
	
		if(Status.equalsIgnoreCase("PASS")){
			logger.pass(Detail);
		}
		else{
			logger.fail(Detail);
			extent.flush();
			System.exit(0);
		}
}
	public static void flushextent(){
		extent.flush();
	}
	
	public static void validation(String StepName,String Actual,String Expected) throws Throwable{
		
		try{
			Assert.assertEquals(Actual, Expected);
			Report("Pass", StepName+" is succcessful &  page is open" +Expected);
			}catch(AssertionError e){ 
				Report("Fail",StepName+ " is unsuccessful because "+e);
			}
	
	}
}
