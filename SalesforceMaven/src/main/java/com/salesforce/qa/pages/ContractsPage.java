package com.salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.qa.base.TestBase;

public class ContractsPage extends TestBase {
String ContractNumber;

@FindBy(xpath="//input[@name='new']")
WebElement NewBtn;

@FindBy(xpath=" //input[@id='ctrc7']")
WebElement AccountNumber;

@FindBy(xpath=" //input[@id='ctrc5']")
WebElement StartDate;

@FindBy(xpath="//input[@id='ctrc40']")
WebElement Month;

@FindBy(xpath="//td[@id='topButtonRow']//input[@name='save']")
WebElement Save;

@FindBy(xpath="//h2[@class='pageDescription']")
WebElement ContractNo;

	public ContractsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateContratsPageTitle(){
		return driver.getTitle();
	}
	
	
	public String CreateContact(String Account) throws Throwable{
		NewBtn.click();
		try{
		AccountNumber.sendKeys(Account);
		StartDate.sendKeys("01/07/2020");
		Month.sendKeys("10");
		Save.click();
		ContractNumber = ContractNo.getText();
		}catch(Exception e){
			//Report
		}
		if(ContractNumber.isEmpty()){
			Reporting("FAIL", "Unable to get Contract Number ");
			log("Fail to get Contract Number");
		}
		else
		{
			Reporting("PASS", "Contract  number is  "+ContractNumber);
			log("Contract Number - "+ContractNumber);
			return ContractNumber;
		}
		
		return null;
	}

}
