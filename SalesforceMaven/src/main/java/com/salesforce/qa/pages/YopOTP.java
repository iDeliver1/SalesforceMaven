package com.salesforce.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.salesforce.qa.base.TestBase;

public class YopOTP  extends TestBase {

	
	@FindBy(xpath="//input[@id='login']")
	WebElement yopuser;
	
	@FindBy(xpath="//input[@class='sbut']")
	WebElement yopbtn;
	
	@FindBy(xpath="//div[@id='mailmillieu']/div[2]")
	WebElement yopotp;
	
	
	public YopOTP() {
		Yopinitialization();
		
	}

	public String getotp(String username){
		PageFactory.initElements(yopdriver, this);
		yopuser.sendKeys(username);
		yopbtn.click();
		yopdriver.switchTo().frame(2);
		String code = yopotp.getText();
		code = code.substring(290, 316).replaceAll("[^0-9]", "");
		yopdriver.close();
		return code;
	}
	
}
