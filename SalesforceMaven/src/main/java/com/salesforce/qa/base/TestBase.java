package com.salesforce.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.salesforce.qa.util.*;

public class TestBase  {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static WebDriver yopdriver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	static String Report_Folder_path = "C:\\Reporting\\Report"+Gernric_functions.fTimestamp();
	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/salesforce/"
					+ "qa/config/config.properties");
			//C:\Users\iDeliver36\workspace\SalesforceMavensrc\main\java\com\salesforceqa\config\config.properties
			//C:\Users\iDeliver36\workspace\SalesforceMaven\src\main\java\com\salesforce\qa\config
			//C:\Users\iDeliver36\workspace\TestClass\src\com\salesforceqa\config
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		//the Extent report will be created only once, no matter wherever we do initialization
			static {
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
			}

	//Initiating  Browser 
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/com/salesforce/qa/driver/chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/com/salesforce/qa/driver/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);		
		driver.get(prop.getProperty("url"));
	
		
}
	//Initiating YopMail
	public static void Yopinitialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/com/salesforce/qa/driver/chromedriver.exe");
			yopdriver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/com/salesforce/qa/driver/geckodriver.exe");	
			yopdriver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(yopdriver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		yopdriver = e_driver;
		
		yopdriver.manage().window().maximize();
		yopdriver.manage().deleteAllCookies();
		yopdriver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		yopdriver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);		
		yopdriver.get(prop.getProperty("yopurl"));
		
}
	//Logging method so that the same log is added in logger as well as syso
		public void log(String data) {
			
			log.info(data);
			Reporter.log(data);
		}
	
	
	//Getting TestName
	public void getReportname(String Reportname){
		Extent_reporter.CreateRoportname(Reportname,extent);
	}
	
	//Creating Report 
	public void Reporting(String Status,String StepName) throws Throwable{
		Extent_reporter.Report(Status, StepName);
		
	}
	
	
	//Closing Browser And Saving Report 
	public static void closeBrowser() {
		driver.close();
		extent.flush();
	}
}
