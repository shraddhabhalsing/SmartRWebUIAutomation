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

public class SmartReporting_UnitTest extends Base {

	SmartReporting_MainPage mainPageObj=new SmartReporting_MainPage();

	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4,exLog5;

	SmartReporting_UnitTest(){
		super();
	}

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\sb\\qa\\reporting\\ExtentReportForUnitTesting.html");
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


	//Verify user is able to enter Source currency

	@Test(priority=2)
	@Parameters({"srcCurr"})
	public void enterConvertVal(String srcCurr){
		mainPageObj.convertVal(srcCurr);
		exLog1=exReport.createTest("Verify user can enter source currency ", "Conversion functionality Automation");
		if (!srcCurr.equals(" "))
		{
			exLog1.log(Status.PASS,"Source currency entered successfully") ;
		}
		else

		{
			exLog1.log(Status.FAIL,"Failed to enter source currency ") ;

		}
	}

	//Verify user is able to select currency from "from" dropdown


	@Test(priority=3)
	@Parameters({"fromval"})
	public void fromDrpdown(String fromval){
		Boolean fromdrpdownselstatus=mainPageObj.from(fromval);
		System.out.println(fromdrpdownselstatus);
		exLog2=exReport.createTest("Verify user can select currency conversion from", "Conversion functionality Automation");
		if (fromdrpdownselstatus)
		{
			exLog2.log(Status.PASS,"from conversion value selected successfully") ;
		}
		else

		{
			exLog2.log(Status.FAIL,"Failed to select from conversion value ") ;
		}

	}

	//Verify user is able to select currency from "To" dropdown
	
	@Test(priority=4)
	@Parameters({"toval"})
	public void toDrpdown(String toval){
		Boolean todrpdownselstatus=mainPageObj.to(toval);
		exLog3=exReport.createTest(" Verify user can select currency conversion to ", "Conversion functionality Automation");
		if (todrpdownselstatus)
		{
			exLog3.log(Status.PASS,"to conversion value selected successfully") ;
		}
		else

		{
			exLog3.log(Status.FAIL,"Failed to select to conversion value ") ;
		}

	}
	
	//Verify user is able to click on on conversion button and retrieve output currency
	@Test(priority=5)

	public void clickBtn(){
		mainPageObj.btn();
		String val=mainPageObj.opVal();
		exLog4=exReport.createTest("Verify output currency", "Conversion functionality Automation");
		if (!val.equals(" "))
		{
			exLog4.log(Status.PASS,"output currency displayed successfully") ;
		}
		else

		{
			exLog4.log(Status.FAIL,"Failed to display output currency ") ;
		}

	}
	
	
	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		driver.close();



	}
}
