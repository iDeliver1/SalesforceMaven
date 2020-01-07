package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.util.Utility_Libraries;

public class CampaignsPage extends TestBase {

	@FindBy(xpath="//li[@id='Campaign_Tab']")
	WebElement CampginTab ;
	
	@FindBy(xpath="//a[@id='tryLexDialogX']")
	WebElement DialogueClose ;
	
	@FindBy(xpath="//input[@title='New']")
	WebElement New ;
	
	@FindBy(xpath="//input[@id='cpn1']")
	WebElement CampName ;
	
	@FindBy(xpath="//input[@id='cpn5']")
	WebElement StartDate ;
	
	@FindBy(xpath="//td[@id='bottomButtonRow']/input[@title='Save']")
	WebElement SaveBtn ;
	
	@FindBy(xpath="//td[@id='cpn16_ilecell']")
	WebElement UpdateIcon ;
	
	@FindBy(xpath="//input[@id='cpn16']")
	WebElement UpdateCheck ;
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='inlineEditSave']")
	WebElement UpdateSave ;
	
	
	
	public CampaignsPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateCampaignsPageTitle(){
		return driver.getTitle();
	}
	public String CreateCampign(String CampignName){
	String 	CampignName1 = CampignName+ Utility_Libraries.fTimestamp();	
		//try
//		{
//			CampginTab.click();
				try
				{
					//Close the popup window
					DialogueClose.isDisplayed();
					try{
						DialogueClose.click();
					}
					catch(Exception e){
						//Report
					}
				}
				catch(Exception f) {}
				New.click();
				try
				{
					CampName.isDisplayed();
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
				CampName.sendKeys(CampignName1);
				StartDate.sendKeys("01/06/2020");
				SaveBtn.click();
		
				try{
					UpdateIcon.isDisplayed();
					
					try{
						Actions objDbclck = new Actions(driver);
						objDbclck.moveToElement(UpdateIcon).doubleClick().build().perform();
						UpdateCheck.click();
						UpdateSave.click();
					}catch(Exception E){
						//Report
					}
				}catch(Exception E){
					//Report
				}
				return CampignName1;
		}
		
		

}
