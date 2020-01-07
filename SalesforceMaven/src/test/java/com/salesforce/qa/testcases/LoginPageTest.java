package com.salesforce.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.salesforce.qa.pages.CampaignsPage;
import com.salesforce.qa.pages.HomePage;
import com.salesforce.qa.pages.LeadsPage;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.util.Utility_Libraries;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.salesforce.qa.base.TestBase;

public class LoginPageTest extends TestBase{
	
	ExtentReports extent;
	ExtentTest logger;
	LoginPage loginPage;
	HomePage homepage;
	LeadsPage leadspage;
	CampaignsPage campaignspage;
	Utility_Libraries utilityobject = new Utility_Libraries();
	String CampName;
	public LoginPageTest(){
		super();
	}
	
	@BeforeTest
	public void setUp(){
		initialization();
		extent = utilityobject.fReport();
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void LoginTest(){
		
		logger = extent.createTest("Login");
		logger.log(Status.PASS, "Login ");
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));		
			if(homepage.validateHomePageTitle().contains("Home Page ~ Salesforce - Developer Edition"))
			{
				logger.log(Status.PASS, "Login Succesfull Home page is open "+homepage.validateHomePageTitle());
			}
			else
			{
				logger.log(Status.FAIL, "Login Succesfull Home page is not open ");
			}
	}
	
	
	@Test(priority=2)
	public void CampaignsTest(){
		
		logger = extent.createTest("CampaignsTest");
		logger.log(Status.PASS, "CreateCampaigns");
		campaignspage = homepage.clickOnCampaignsLink();
			if(campaignspage.validateCampaignsPageTitle().contains("Campaigns: Home ~ Salesforce - Developer Edition"))
			{
				logger.log(Status.PASS, "campaigns page is open "+ campaignspage.validateCampaignsPageTitle());
			}
			else
			{
				logger.log(Status.FAIL, "campaignspage page is not open ");
			}
			CampName = 		campaignspage.CreateCampign(prop.getProperty("campigan"));
				
	}
	
	
	@Test(priority=3)
	public void LeadTest(){
		
		logger = extent.createTest("CreateLeads");
		logger.log(Status.PASS, "CreateLeads");
		leadspage = homepage.clickOnLeadsLink();
			if(leadspage.validateLeadsPageTitle().contains("Leads: Home ~ Salesforce - Developer Edition"))
			{
				logger.log(Status.PASS, "Leads page is open "+ leadspage.validateLeadsPageTitle());
			}
			else
			{
				logger.log(Status.FAIL, "Leads page is not open ");
			}
		leadspage.CreateLeads("Aviraj", "Lall", "iDeliver", "Open - Not Contacted", CampName);		
	}
	
	@AfterMethod
	public void Flush()
	{
		extent.flush();
	}

}
