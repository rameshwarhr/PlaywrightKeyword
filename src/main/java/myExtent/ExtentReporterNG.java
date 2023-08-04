package myExtent;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;

import Base.base;

public class ExtentReporterNG 
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
	

	    public static ExtentReports extent;
	  public static  ExtentSparkReporter report;
		
		public static ExtentReports setExtent()
		{
			report=new ExtentSparkReporter(reportFileLocation);
			report.config().setDocumentTitle(reportFileName);
			report.config().setReportName("CRA Automation Report");
			report.config().setTheme(Theme.DARK);
			
			extent=new ExtentReports();
			extent.attachReporter(report);
			
			// Adding system information / environment ifo
					extent.setSystemInfo("Window Ip:", "172.19.66.58");
					extent.setSystemInfo("OS:", "Window");
					extent.setSystemInfo("QA Name:", "Rameshwar Rathod");
					extent.setSystemInfo("Project:", "PLAYWRIGHT");
					
					
					return extent;
		}

		
		
		
		public static void getScreenshot()
		{
			Date d=new Date();
			filename=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		base.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./Screenshot/"+filename)));
		}
		
		
		
		
}
