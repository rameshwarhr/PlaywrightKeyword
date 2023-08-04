package suit.bankmanager.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import Base.base;
import ExcelManager.DataUtil;
import ExcelManager.Excel_reader;
import Utility.Constants;
import Utility.DataProviders;

public class AddCustomerTest extends base
{
	@Test(dataProviderClass=DataProviders.class, dataProvider="bankManagerDP")
	public void addCustomerTest(Hashtable<String, String>data) throws InterruptedException
	{
		//System.out.println(data.get("firstname"));
		
	Excel_reader excel=new Excel_reader(Constants.excelpath);
	DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);
	
	Browser browser=getBrowser(data.get("browser"));
	nevigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
	Thread.sleep(500);
	click("bankMBtn");
	Thread.sleep(500);
	click("addCBtn");
	Thread.sleep(1000);
	type("firstname", data.get("firstname"));
	Thread.sleep(1000);
	type("lastname", data.get("lastname"));
	Thread.sleep(1000);
	type("postcode", data.get("postcode"));
	Thread.sleep(1000);
	click("addBtn");

		
	}

	
	
	
	
	


	
	
	
	
	
	
	
	
	
}
