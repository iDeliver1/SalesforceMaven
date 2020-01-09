package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.util.Gernric_functions;

public class LeadsPage extends TestBase {
	String AccountHeaderName;
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
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='convert']")
	WebElement Convertbtn;
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='convert']")
	WebElement OppurnityName;
	
	@FindBy(xpath="//select[@id='accid']")
	WebElement Account;
	
	@FindBy(xpath="//input[@id='tsk5_fu']")
	WebElement Subject;
	
	@FindBy(xpath="//select[@id='tsk12_fu']")
	WebElement StatusConvert;
	
	@FindBy(xpath="//td[@id='acc2_ilecell']")
	WebElement UpdateClick;
	
	@FindBy(xpath=" //td[@id='topButtonRow']//input[@name='inlineEditSave']")
	WebElement UpdateSave;
	
	@FindBy(xpath=" //td[@id='topButtonRow']//input[@name='save']")
	WebElement ConvertSave;
	
	@FindBy(xpath=" //input[@id='acc2']")
	WebElement AccountName;
	
	@FindBy(xpath=" //h2[@class='topName']")
	WebElement AccountHeader;
	
	
	public LeadsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateLeadsPageTitle(){
		return driver.getTitle();
	}
		
	public String CreateLeads(String FNName, String LNName, String CompanyNName, String SStatus, String CCampaign) throws Throwable{
	
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
				
				String CompAcc = CompanyNName+ Gernric_functions.fTimestamp();
				CompanyName.sendKeys(CompAcc);
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
				
						try{
							Convertbtn.click();
							Subject.sendKeys("Other");
							ConvertSave.click();
							 AccountHeaderName = AccountHeader.getText();
							
							if(AccountHeaderName.contains(CompAcc))
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
							
						}catch (Exception D){
							//Report
						}
						
						if(AccountHeaderName.isEmpty()){
							Reporting("FAIL", "Unable to get Account Number" );
							
						}
						else
						{
							Reporting("PASS", "Account number is  "+AccountHeaderName);
							return AccountHeaderName;
						}
					
				return null;
				
		}
		

}
