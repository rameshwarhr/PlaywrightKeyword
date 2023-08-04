package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import Base.base;

public class BankManagerLoginTest extends base
{
	
	@Test
public void loginBankManager()
{
		Browser browser=getBrowser("chrome");
		nevigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		click("bankMBtn");
	//	click("addCBtn");
		boolean value=isEementPresent("addCBtn");
		Assert.assertTrue(value,"Bank Manager not Loged");
//	
}

}
