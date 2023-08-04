package ExtentManager;

import java.text.SimpleDateFormat;
import java.nio.file.Paths;
import java.sql.Timestamp;

import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;

import Base.base;

public class extentManager 
{

	public static String filename;
	static Date date = new Date();  
    static Timestamp ts=new Timestamp(date.getTime());  
    static SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");  
   // System.out.println(formatter.format(ts));     
	
    private static String reportFileName = "Test-Automaton-Report "+timestamp.format(ts)+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
	
	private static ExtentReports extent;
	
	public static ExtentReports creatInstance() {
		ExtentSparkReporter htmlReporter =new ExtentSparkReporter(reportFileLocation);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Result");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester", "Rameshwar Rathod");
		extent.setSystemInfo("Project", "Playwright");
		extent.setSystemInfo("Operating System", "Windows");
		
		return extent;
	}


	public static void getScreenshot()
	{
		Date d=new Date();
		filename=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	base.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./Screenshot/"+filename)));
	}
}
