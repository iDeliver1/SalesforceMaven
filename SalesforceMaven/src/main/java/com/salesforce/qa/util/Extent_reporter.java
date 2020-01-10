package com.salesforce.qa.util;


import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.salesforce.qa.base.TestBase;


public class Extent_reporter extends TestBase {
  static ExtentTest logger;
  

//Getting TestName
	public static ExtentTest CreateRoportname(String Step_details,ExtentReports extent1){

		logger = extent1.createTest(Step_details);
		return logger;
		
	}
	
	
//Reporting for Pass & Fail Event 
	public static void Report(String Status1,String Detail) throws Throwable{
	
		if(Status1.equalsIgnoreCase("PASS")){
			logger.log(Status.PASS, Detail);
			logger.addScreenCaptureFromPath(Gernric_functions.fScreenReport());
		}
		else{
			logger.log(Status.FAIL, Detail);
			closeBrowser();
		}
}
	
	//Validation for actual and expected 
	public static void validation(String StepName,String Actual,String Expected) throws Throwable{
		
		try{
			Assert.assertEquals(Actual, Expected);
			Report("PASS", StepName+" is succcessful &  page is open " +Expected);
			}catch(AssertionError e){ 
				Report("FAIL",StepName+ " is unsuccessful because "+e);
			}
	
	}
}
