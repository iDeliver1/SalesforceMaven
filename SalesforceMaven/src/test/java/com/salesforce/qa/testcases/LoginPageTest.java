package com.salesforce.qa.testcases;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.salesforce.qa.pages.CampaignsPage;
import com.salesforce.qa.pages.ContractsPage;
import com.salesforce.qa.pages.HomePage;
import com.salesforce.qa.pages.LeadsPage;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.pages.OrderPage;
import com.salesforce.qa.util.Extent_reporter;
import com.salesforce.qa.util.TestUtil;
import com.salesforce.qa.base.TestBase;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homepage;
	LeadsPage leadspage;
	CampaignsPage campaignspage;
	ContractsPage contractpage;
	OrderPage orderpage;
	String CampName,Account,OrderNo,Contract;
	
	public LoginPageTest(){
		super();
	}
	
	//Initializing
	@BeforeTest
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
	}
	
	
	//Login Test
	@Test(priority=1)
	public void LoginTest() throws Throwable{
		log("-----------------------"+new Object(){}.getClass().getEnclosingMethod().getName()+"--------------");
		
		getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
		
		Extent_reporter.validation("Login Page Validation",homepage.validateHomePageTitle(), "Home Page ~ Salesforce - Developer Edition");
	}
	
	//Campaign Test
	@Test(priority=2)
	public void CampaignsTest() throws Throwable{
		log("-----------------------"+new Object(){}.getClass().getEnclosingMethod().getName()+"--------------");
		getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		
		campaignspage = homepage.clickOnCampaignsLink();
		
		Extent_reporter.validation("Campaigns Page Validation",campaignspage.validateCampaignsPageTitle(), "Campaigns: Home ~ Salesforce - Developer Edition");
		
		CampName = campaignspage.CreateCampign(prop.getProperty("campigan"));		
	}
	
	//Lead Test
	@Test(priority=3)
	public void LeadTest() throws Throwable{
		log("-----------------------"+new Object(){}.getClass().getEnclosingMethod().getName()+"--------------");
		getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		
		Thread.sleep(2000);
		
		leadspage = homepage.clickOnLeadsLink();
		
		Extent_reporter.validation("Leads Page Validation",leadspage.validateLeadsPageTitle(), "Leads: Home ~ Salesforce - Developer Edition");
		
		Account =	leadspage.CreateLeads("Aviraj", "Red", "iDeliver", "Open - Not Contacted", CampName);		
	}
	
	
	//Contract Test
	@Test(priority=4)
	public void ContratTest() throws Throwable {
		log("-----------------------"+new Object(){}.getClass().getEnclosingMethod().getName()+"--------------");
		getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS) ;
		
		contractpage = homepage.clickOnContractLink();
		
		Extent_reporter.validation("Contract Page Validation",contractpage.validateContratsPageTitle(), "Contracts: Home ~ Salesforce - Developer Edition");
		
		Contract= contractpage.CreateContact(Account);
	}
	
	//Order Test
	@Test(priority=5)
	public void OrderTest() throws Throwable{
		log("-----------------------"+new Object(){}.getClass().getEnclosingMethod().getName()+"--------------");
		getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS) ;
		
		orderpage = homepage.clickOnOrderLink();
		
		Extent_reporter.validation("Order Page Validation",orderpage.validateOrderPageTitle(), "Orders: Home ~ Salesforce - Developer Edition");
		
		OrderNo=orderpage.CreateOrder(Account, Contract);
	}
	
	//Closing Browser and saving report 
	@AfterTest
	public void Flush()
	{
		closeBrowser();
	}

}
