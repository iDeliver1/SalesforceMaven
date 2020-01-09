package com.salesforce.qa.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
		
		
		public HomePage login(String usr, String pwd) throws Throwable 
		{
			Username.sendKeys(usr);
			Password.sendKeys(pwd);
			Signin.click();	
		
			if(validateLoginPageTitle().contains("Verify Your Identity | Salesforce")){
				YopOTP objOTP = new YopOTP();
				String code = 		objOTP.getotp(usr);
				OTPText.sendKeys(code);
				Save.click();
				Reporting("Pass", "Yotp is "+code);
				return new HomePage();

			}else{
				Reporting("Fail", "Unable to get Yotp ");
				return new HomePage();
			}
			//return new HomePage();
		}
		
		

}
