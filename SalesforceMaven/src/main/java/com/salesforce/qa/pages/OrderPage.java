package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;

public class OrderPage extends TestBase {
String OrderNumber;

	@FindBy(xpath="//input[@name='new']")
	WebElement NewOrder;
	
	@FindBy(xpath="//input[@id='accid']")
	WebElement AccountNo;
	
	@FindBy(xpath="//input[@id='Contract']")
	WebElement ContractNo;
	
	@FindBy(xpath="//input[@id='EffectiveDate']")
	WebElement StartDate;
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='save']")
	WebElement Save;
	
	@FindBy(xpath="//h1[@class='noSecondHeader pageType']")
	WebElement OrderNo;
	
	
	public OrderPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateOrderPageTitle(){
		return driver.getTitle();
	}
	
	
	public String CreateOrder(String Account,String Campno) throws Throwable{
		NewOrder.click();
		try
		{
			AccountNo.sendKeys(Account);
			ContractNo.sendKeys(Campno);
			StartDate.sendKeys("01/07/2020");
			Save.click();
			
			OrderNumber=OrderNo.getText();
		}catch(Exception e){
			
		}
		
		if(OrderNumber.isEmpty()){
			Reporting("FAIL", "Unable to get Order Number ");
			log("Fail to get Order Number");
		}
		else
		{
			Reporting("PASS", "Order  number is  "+OrderNumber );
			log("Order Number - "+OrderNumber);
			return OrderNumber;
		}
		
		return null;
	}
}
