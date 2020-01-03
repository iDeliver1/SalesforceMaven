package com.salesforce.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.qa.pages.HomePage;
import com.salesforce.qa.pages.LoginPage;

import org.testng.*;

import com.salesforce.qa.base.TestBase;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homepage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization("url");
		loginPage = new LoginPage();
		//homepage = new HomePage();
		
	}
	
	@Test
	//@DataProvider[]
	public void LoginTest(){
		
		Assert.assertEquals(true, loginPage.validateSalesForceLogo());
		
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	//	homepage.validateHomePageTitle();
		String home = homepage.validateHomePageTitle();
		Assert.assertEquals("Home Page ~ Sales Force - Developer Edition", homepage.validateHomePageTitle());
	}
	
	

}
