package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import ExtentManager.Listners;
import Utility.elementUtil;
import myExtent.listners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame.WaitForSelectorOptions;
import com.microsoft.playwright.Page;

public class base {
	private Playwright playwright;
	public Browser browser;
	public Page page;
	private static Properties or = new Properties();
//	private static Properties config =new Properties();

	private static FileInputStream fis;
	private Logger log = Logger.getLogger(this.getClass());
	private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
	private static ThreadLocal<Browser> br = new ThreadLocal<>();
	private static ThreadLocal<Page> pg = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return pw.get();
	}

	public static Browser getBrowser() {
		return br.get();
	}

	public static Page getPage() {
		return pg.get();
	}

	@BeforeSuite()
	public void setUp() {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("Test Execution Started !!!");

		try {
			fis = new FileInputStream("./src/test/resources/properties/or.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			or.load(fis);
			log.info("or Properties file is loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void click(String locatorKey) {
		try {
			getPage().locator(or.getProperty(locatorKey)).click();
			log.info("clicking an element :" + locatorKey);
			// listners.extentTest.get().info("clicking on an Element :- "+locatorKey);
			Listners.getExtent().info("clicking on an Element :- " + locatorKey);
		} catch (Throwable t) {
			log.error("Error While Clicking on an Element :- " + t.getMessage());
			Listners.getExtent().fail("Error While clicking on an Element :- " + locatorKey);

			Assert.fail(t.getMessage());
		}
	}

	public void type(String locatorKey, String value) {
		try {
			getPage().locator(or.getProperty(locatorKey)).fill(value);
			log.info("Sending Value in the Textfield :" + locatorKey + " @Values- " + value);
			Listners.getExtent().info("Sending Value in the Textfield :- " + locatorKey + " @Values- " + value);
		} catch (Throwable t) {
			log.error("Error While Sending Value in the Textfield ::- " + t.getMessage());
			Listners.getExtent().fail("Error While Sending Value in the Textfield :- " + locatorKey);
			Assert.fail(t.getMessage());
		}
	}

	public boolean isEementPresent(String locatorKey) {

		try {
			getPage().waitForSelector(or.getProperty(locatorKey),
					new com.microsoft.playwright.Page.WaitForSelectorOptions().setTimeout(2000));
			getPage().isVisible(or.getProperty(locatorKey));
			log.info("Finding Element :" + locatorKey);
			Listners.getExtent().info("Finding Element :- " + locatorKey);
			return true;

		} catch (Throwable t) {

			Listners.getExtent().fail("Error while Finding an Element :- " + locatorKey);
			return false;
		}
	}

	
	public void select_dropdown(String locatorKey, String value)
	{
		try {
			getPage().selectOption(or.getProperty(locatorKey), new SelectOption().setLabel(value));
			log.info("Selecting an Element :" + locatorKey + " @Values- " + value);
			Listners.getExtent().info("Selecting an Element :- " + locatorKey + " @Values- " + value);
		}catch(Throwable t) {
			log.error("Error While Selecting dropdown Value ::- " + t.getMessage());
			Listners.getExtent().fail("Error While Selecting dropdown Valu :- " + locatorKey);
			Assert.fail(t.getMessage());
		}
	}
	
	
	public Browser getBrowser(String browsrName) {
		playwright = Playwright.create();
		pw.set(playwright);

		switch (browsrName) {
		case "chrome":
			log.info("Launching Chrome browser");
			return getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

		case "headless":
			log.info("Launching Headless Mode");
			return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

		case "firefox":
			log.info("Launching FireFox browser");
			return getPlaywright().firefox()
					.launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));

		default:
			throw new IllegalArgumentException();
		}

	}

	public void nevigate(Browser browser, String url) {
		this.browser = browser;
		br.set(browser);

		page = getBrowser().newPage();
		pg.set(page);
		getPage().navigate(url);
		log.info("Navigated to :-" + url);

		//Alert Acept Code
		elementUtil.acceptAlert();

	}

	@AfterMethod
	public void quit() {
		if(getPage() != null) {
		getBrowser().close();
		getPage().close();
	}
	}

	@AfterSuite
	public void quitPlaywright() {
		getPlaywright().close();
	}

}
