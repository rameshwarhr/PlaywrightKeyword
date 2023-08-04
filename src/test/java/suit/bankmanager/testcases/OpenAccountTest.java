package suit.bankmanager.testcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import Base.base;
import ExcelManager.DataUtil;
import ExcelManager.Excel_reader;
import Utility.Constants;
import Utility.DataProviders;

public class OpenAccountTest extends base {
	@Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {
		Excel_reader excel = new Excel_reader(Constants.excelpath);
		DataUtil.checkExecution("BankManagerSuite", "OpenAccountTest", data.get("Runmode"), excel);
		
		Browser browser = getBrowser(data.get("browser"));
		nevigate(browser, "https://www.way2automation.com/angularjs-protractor/banking/#/login");
		click("bankMBtn");
		Thread.sleep(500);
		click("openAccountBtn"); //fhjbjkh gjkgjhkgu Dollar
		select_dropdown("customer_dropdown", data.get("customer"));
		select_dropdown("currancy_dropdown", data.get("currency"));
		click("processBtn");
	}

}
