package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforce.qa.base.TestBase;

public class LeadsPage extends TestBase {
	@FindBy(xpath="//li[@id='Lead_Tab']")
	WebElement LeadTab ;
	
	@FindBy(xpath="//a[@id='tryLexDialogX']")
	WebElement DialogeClose;
	
	@FindBy(xpath="//input[@name='new']")
	WebElement NewButton;
	
	@FindBy(xpath="//input[@id='name_firstlea2']")
	WebElement FName ;
	
	@FindBy(xpath="//input[@id='name_lastlea2']")
	WebElement LName;
	
	@FindBy(xpath="//input[@id='lea20']")
	WebElement Campaign;
	
	@FindBy(xpath="//input[@id='lea3']")
	WebElement CompanyName;
	
	@FindBy(xpath="//select[@id='lea13']")
	WebElement Status 	;
	
	@FindBy(xpath="//td[@id='bottomButtonRow']/input[@title='Save']")
	WebElement Save;
	
	@FindBy(xpath="//div[@class='textBlock']/h2")
	WebElement HeadName;
	
	
	public LeadsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateLeadsPageTitle(){
		return driver.getTitle();
	}
		
	public void CreateLeads(String FNName, String LNName, String CompanyNName, String SStatus, String CCampaign){
		//try
		//{
			//LeadTab.click();
				try
				{
				
					//Close the popup window
					DialogeClose.click();
				}
				catch(Exception f) {}
				NewButton.click();
				try
				{
					FName.isDisplayed();
					//-----------------------------Reporter
					//Utility_Object.fReportpass("Create Lead", "Create Lead page is open successfully", logger, driver);
					//------------------------------------
				}
				catch(Exception e)
				{
					//-----------------------------Reporter
					//Utility_Object.fReportfail("Create Lead", "Error :" + e +" Create Lead page is not open successfully", logger, driver, Extndreport);
					//------------------------------------
				}
				FName.sendKeys(FNName);
				LName.sendKeys(LNName);
				Campaign.sendKeys(CCampaign);
				CompanyName.sendKeys(CompanyNName);
			Select Status1_ = new Select(  Status);
			Status1_.selectByValue(SStatus);
			Save.click();
			//WebDriverWait wait = new WebDriverWait(driver,6);
			//wait.until(ExpectedConditions.presenceOfElementLocated((By) HeadName));
			String LeadName = HeadName.getText();
				if(LeadName.contains(FNName.concat(" " + LNName)))
				{
					//-----------------------------Reporter
					//Utility_Object.fReportpass("Lead create", "Lead is successfully created", logger, driver);
					//------------------------------------
				}
				else
				{
					
					//-----------------------------Reporter
					//Utility_Object.fReportfail("Lead create", "Lead is not successfully created", logger, driver, Extndreport);
					//------------------------------------
				}
		}
		//catch(Exception E)
		//{
			//-----------------------------Reporter
			//System.out.println(E);
			//------------------------------------
		//}
	//}

}
