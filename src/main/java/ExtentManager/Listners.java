package ExtentManager;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;





public class Listners implements ITestListener,ISuiteListener
{
	private static ExtentReports extent= extentManager.creatInstance();
	public static ExtentTest test;
	
	public static ThreadLocal<ExtentTest>extentTest=new ThreadLocal<ExtentTest>();
	
	public static ExtentTest getExtent()
	{
		return extentTest.get();
	}
	
	public void onTestStart(ITestResult result) {  
	
		test=extent.createTest(result.getTestClass().getName()+"  @TestCase:- "+result.getMethod().getMethodName());
		extentTest.set(test);
	}  
	  
 
	public void onTestSuccess(ITestResult result) {  
	
		String methodName=result.getMethod().getMethodName();
		String logtext="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+" : -PASSED"+"</b>";
		Markup m= MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
	//	extentTest.get().pass(m);
		getExtent().pass(m);
		
	  
	}  
 
	public void onTestFailure(ITestResult result) {  
	
		extentManager.getScreenshot();
		
		String methodName=result.getMethod().getMethodName();
		String logtext="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+"FAILED"+"</b>";
		getExtent().log(Status.FAIL,result.getThrowable());
		getExtent().fail("<b><font color=red>"+"Screenshot of Failure"+"</front></b>,br>",
				MediaEntityBuilder.createScreenCaptureFromPath(extentManager.filename).build());
		Markup m= MarkupHelper.createLabel(logtext, ExtentColor.RED);
		getExtent().log(Status.FAIL, m);
		
	}  
	  
	 
	public void onTestSkipped(ITestResult result) {  
		String methodName=result.getMethod().getMethodName();
		String logtext="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+":- SKIPPED"+"</b>";
		Markup m= MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		getExtent().skip(m);;
		
	}  
	  
	  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	// TODO Auto-generated method stub  
	System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
	}  
	  
 
	public void onFinish(ITestContext context) {  
	// TODO Auto-generated method stub 
		extent.flush();
	}  

}
