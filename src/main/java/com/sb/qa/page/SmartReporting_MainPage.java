package com.sb.qa.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sb.qa.base.Base;

public class SmartReporting_MainPage extends Base {

	// Find elements at run time

	@FindBy(xpath="//div[@class='CurrencyConverter-inputs']//child::div[1]//div[@class='CurrencyInput']//input")
	public static
	WebElement converttxtfld;

	@FindBy(xpath="//label[contains(text(),'from')]//following::select[1]")
	WebElement fromDrpdn;

	@FindBy(xpath="//label[contains(text(),'to')]//following::select[1]")
	WebElement toDrpdn;

	@FindBy(xpath="//button[@class='CurrencyConverter-button']")
	WebElement calcbtn;

	@FindBy(xpath="//div[@class='CurrencyConverter-inputs']//child::div[5]//input")
	WebElement opValtxtfld;

	@FindBys(@FindBy(xpath="//ul[@class='ConversionHistory-list']//li"))
	List <WebElement> verifyList;
	
	ArrayList<String> ls;
	
	


	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}

	//Method to enter source currency

	public void convertVal(String conVal){	
		try
		{
			converttxtfld.sendKeys(conVal);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Method to enter Conversion "from" text
	public Boolean from(String fromval){

		Boolean fromValselstat=false;

		try
		{
			Select sfrom=new Select(fromDrpdn);
			sfrom.selectByVisibleText(fromval);
			if (!sfrom.equals(" "))
				fromValselstat=true;
			else
				fromValselstat=false;	



		}catch(Exception e)
		{
			System.out.println(e);
		}
		return fromValselstat;
	}

	//Method to enter conversion "to" text
	public Boolean to(String toVal){


		Boolean toValselstat=false;
		Select sfrom=new Select(toDrpdn);
		sfrom.selectByVisibleText(toVal);
		if (!sfrom.equals(" "))
			toValselstat=true;
		else
			toValselstat=false;
		return toValselstat;	

	}

	//Method to click on conversion button
	public void btn (){

		calcbtn.click(); 

	}

	//Method to get output currency

	public String opVal (){

		return opValtxtfld.getAttribute("value");


	}
   
	//Method to verify history of the last 10 currency conversions that were performed
	public Boolean conversionList (){

		Boolean valList=false;
		ls=new ArrayList<String>();
		
		for (WebElement e: verifyList)
		{
			ls.add(e.getText());
		
		}
		
		if (ls.size()==10)
			
			valList=true;
		
		else
			
			valList=false;
			
		
		
			return valList;


	}

}
