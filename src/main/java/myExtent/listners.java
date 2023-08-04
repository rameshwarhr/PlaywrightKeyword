package myExtent;

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

import ExtentManager.extentManager;

public class listners implements ITestListener {

	static ExtentReports extent = ExtentReporterNG.setExtent();
	ExtentTest test;

	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
//public static	ThreadLocal<ExtentTest>extentTest=new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stu

		test = extent
				.createTest(result.getTestClass().getName() + "  @TestCase:- " + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.PASS, MarkupHelper.createLabel("Test Passed :-
		// "+result.getMethod().getMethodName()+"-:-"+result.getMethod().getRealClass(),
		// ExtentColor.GREEN));

		String methodName = result.getMethod().getMethodName();
		String logtext = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " : -PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		extentTest.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		ExtentReporterNG.getScreenshot();
		
		String methodName = result.getMethod().getMethodName();
		String logtext = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + "FAILED" + "</b>";
		extentTest.get().fail(result.getThrowable());
		extentTest.get().fail("<b><font color=red>" + "Screenshot of Failure" + "</front></b>,br>",
				MediaEntityBuilder.createScreenCaptureFromPath(extentManager.filename).build());
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.SKIP, MarkupHelper.createLabel("Test Skip :-
		// "+result.getMethod().getMethodName()+"-:-"+result.getMethod().getRealClass(),
		// ExtentColor.ORANGE));

		String methodName = result.getMethod().getMethodName();
		String logtext = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + ":- SKIPPED" + "</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		extentTest.get().skip(m);
		;
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
