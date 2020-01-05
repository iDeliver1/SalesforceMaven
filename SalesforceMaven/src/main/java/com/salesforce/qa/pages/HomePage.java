package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.qa.base.TestBase;

public class HomePage extends TestBase{
		
	@FindBy(xpath="//img[@id='phHeaderLogoImage']")
	WebElement SalesForceHomeLogo;
	
	@FindBy(xpath="//li[@id='home_Tab']//a[contains(text(),'Home')]")
	WebElement HomeLink;
	
	@FindBy(xpath= "//li[@id='Lead_Tab']//a[contains(text(),'Leads')]")
	WebElement LeadsLink;
	
	@FindBy(xpath= "//li[@id='Account_Tab']//a[contains(text(),'Accounts')]")
	WebElement AccountsLink;
	
	@FindBy(xpath= "//li[@id='Contact_Tab']//a[contains(text(),'Contacts')]")
	WebElement ContactsLink;
	
	@FindBy(xpath= "//li[@id='Opportunity_Tab']//a[contains(text(),'Opportunities')]")
	WebElement OppurtunitiesLink;
	
	@FindBy(xpath= "//li[@id='Order_Tab']//a[contains(text(),'Orders')]")
	WebElement OrderLink;
	
	@FindBy(xpath= "//a[contains(text(),'Logout')]")
	WebElement LogoutLink;
	
	
	//Initializing the Page Objects:
			public HomePage(){
				super();
				PageFactory.initElements(driver, this);
			}
			
			//Actions:
			public String validateHomePageTitle(){
				return driver.getTitle();
			}
			
			public boolean verifyCorrectUserName(){
				return SalesForceHomeLogo.isDisplayed();
			}
			
			public LeadsPage clickOnLeadsLink(){
				LeadsLink.click();
				return new LeadsPage();
			}
			
			public AccountsPage clickOnAccountsLink(){
				AccountsLink.click();
				return new AccountsPage();
			}
			
			public ContactsPage clickOnContactsLink(){
				ContactsLink.click();
				return new ContactsPage();
			}
			
			public OppurtunitiesPage clickOnOppurtunitiesLink(){
				OppurtunitiesLink.click();
				return new OppurtunitiesPage();
			}
			
			public OrderPage clickOnOrderLink(){
				OrderLink.click();
				return new OrderPage();
			}
			public LogoutPage clickOnLogoutLink(){
				LogoutLink.click();
				return new LogoutPage();
			}
			

}
