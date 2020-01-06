package com.salesforce.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.salesforce.qa.util.*;

public class TestBase {

	public static WebDriver driver;
	public static WebDriver yopdriver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
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
}
