package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	//Contact page label
	@FindBy(xpath="//h1[@class='pageType']")
	WebElement Contactlabel;
	
	@FindBy(xpath="//a[@class='brandPrimaryFgr']")
	WebElement ContactLink;
	
	@FindBy(xpath="//input[@name='new']")
	WebElement NewContactButton;
	
	@FindBy(xpath="//input[@id='name_firstcon2']")
	WebElement FirstName;
	
	@FindBy(xpath="//input[@id='name_lastcon2']")
	WebElement LastName;
	
	@FindBy(xpath="//input[@id='con4']")
	WebElement AccountName;
	
	@FindBy(xpath="//textarea[@id='con19street']")
	WebElement Address;
	
	@FindBy(xpath=" //textarea[@id='con20']")
	WebElement Description;
	
	@FindBy(xpath="//td[@id='bottomButtonRow']//input[@name='save']")
	WebElement SaveButton;
	
	//Initializing the Page Objects:
	public ContactsPage(){
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateContactPageTitle(){
		return driver.getTitle();
	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(ContactLink).build().perform();
		NewContactButton.click();
	}
		

}
