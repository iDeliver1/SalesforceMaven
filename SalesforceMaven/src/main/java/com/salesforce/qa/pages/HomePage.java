package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//li[@id='home_Tab']//a[contains(text(),'Home')]")
	WebElement SalesForceHomeLink;
	
	//Initializing the Page Objects:
			public HomePage(){
				super();
				PageFactory.initElements(driver, this);
			}
			
			//Actions:
			public String validateHomePageTitle(){
				return driver.getTitle();
			}
			
			

}
