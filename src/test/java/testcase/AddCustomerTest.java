package testcase;

import java.util.Hashtable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import Base.base;
import ExcelManager.Excel_reader;
import Utility.DataProviders;

public class AddCustomerTest extends base {

	@Test(dataProviderClass=DataProviders.class, dataProvider="bankManagerDP")
	public void addCustomerTest(Hashtable<String,String>data) throws InterruptedException
	{
		Browser browser=getBrowser(data.get("browser"));
		nevigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		Thread.sleep(1000);
		click("bankMBtn");
		Thread.sleep(1000);
		click("addCBtn");
		Thread.sleep(2000);
		type("firstname", data.get("firstname"));
		Thread.sleep(2000);
		type("lastname", data.get("lastname"));
		Thread.sleep(2000);
		type("postcode", data.get("postcode"));
		Thread.sleep(2000);
		click("addBtn");
		
	}

}
