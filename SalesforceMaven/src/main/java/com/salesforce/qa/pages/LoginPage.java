package com.salesforce.qa.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.salesforce.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	//Page Factory - OR:
		@FindBy(xpath="//img[@id='logo']")
		WebElement SalesForceLogo;
	
		@FindBy(xpath="//input[@id='username']")
		WebElement Username;
		
		@FindBy(xpath="//input[@id='password']")
		WebElement Password;
		
		@FindBy(xpath="//input[@id='Login']")
		WebElement Signin;
		
		@FindBy(xpath="//input[@id='emc']")
		WebElement OTPText;
		
		@FindBy(xpath="//input[@id='save']")
		WebElement Save;
		
		@FindBy(xpath="//div[@id='tabContainer']")
		WebElement Tab;
		
		@FindBy(xpath="//div[@id='error']")
		WebElement UserError;
		
		@FindBy(xpath="//input[@id='login']")
		WebElement YopUser;
		
		@FindBy(xpath="//input[@type='submit']")
		WebElement YopSubmit;
		
		@FindBy(xpath="//div[@id='mailmillieu']/div[2]")
		WebElement Message;
		
		
		
		//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validateSalesForceLogo(){
			return SalesForceLogo.isDisplayed();
		}
		
		
		public HomePage login(String usr, String pwd) 
		{
			Username.sendKeys(usr);
			Password.sendKeys(pwd);
			Signin.click();
			//JavascriptExecutor js = (JavascriptExecutor)driver;
			//js.executeScript("arguments[0].click();", Signin);					

			if(validateLoginPageTitle().contains("Verify Your Identity | Salesforce")){
				YopOTP objOTP = new YopOTP();
				String code = 		objOTP.getotp(usr);
				OTPText.sendKeys(code);
				Save.click();
				return new HomePage();
			}else{
				return new HomePage();
			}
			//return new HomePage();
		}
		
		

}
