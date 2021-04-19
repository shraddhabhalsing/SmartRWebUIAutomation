package com.sb.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.sb.qa.base.Base;
import com.sb.qa.page.SmartReporting_MainPage;
import com.sb.qa.util.CommonUtil;

public class SmartReportingEndToEndTest extends Base {

	SmartReporting_MainPage mainPageObj=new SmartReporting_MainPage();

	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog2;
	int cntConversionList=0;

	SmartReportingEndToEndTest(){
		super();
	}

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\sb\\qa\\reporting\\ExtentReportEndToEndTest.html");
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);

	}

	// Launch URL

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		mainPageObj.initializeWebElement();
		exLog=exReport.createTest("Verify user can launch URL", "Conversion functionality Automation");
		if (pageTitle.equals("Currency converter"))
		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}

	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}


	//Verify end to end functionality of currency conversion

	@Test(priority=2,dataProvider="passSheet")
	public void enterConvertVal(String conVal,String frmVal,String toVal ){
		mainPageObj.convertVal(conVal);
		mainPageObj.from(frmVal);
		mainPageObj.to(toVal);
		mainPageObj.btn();
		mainPageObj.opVal();
		SmartReporting_MainPage.converttxtfld.clear();

	}


	//verify history of the last 10 currency conversions that were performed
	@Test(priority=3)

	public void currencyConversionHistory(){
		
		Boolean chkHist=mainPageObj.conversionList();
		exLog=exReport.createTest("Verify history of the last 10 currency conversions", "Conversion functionality Automation");
		if (chkHist)
		{
			exLog.log(Status.PASS,"History of the last 10 currency conversions displayed successfully") ;
		}
		else

		{
			exLog.log(Status.FAIL,"Failed to display history of the last 10 currency conversions ") ;
		}

	}



	//Close browser

	@AfterTest
	public void closeBrowser() {
		mainPageObj.conversionList();
		exReport.flush();
		driver.close();



	}
}
