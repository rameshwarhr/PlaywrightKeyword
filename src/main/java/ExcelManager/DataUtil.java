package ExcelManager;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import Utility.Constants;

public class DataUtil 
{

	public static void checkExecution(String testSuiteName, String testCaseName,String dataRunMode, Excel_reader excel)
	{
		if(!isSuiteRunnable(testSuiteName)) {
			throw new SkipException("Skipping the test :- "+testCaseName+" as the Runmode of Test Case :- "+testCaseName+" is No");
		}
		
		if(!isTestRunnable(testCaseName, excel)) {
			throw new SkipException("Skipping the test :- "+testCaseName+" as the Runmode of Test Case :- "+testCaseName+" is No");
		}
		
		if(dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)) {
			throw new SkipException("Skipping the test :- "+testCaseName+" as the Runmode of Test Case  is No");
		}
		
	}
	public static boolean isSuiteRunnable(String SuiteName)
	{
		Excel_reader excel=new Excel_reader(Constants.SUITE_XL);
		int rows=excel.getRowCount(Constants.SUIT_SHEET);
		
		for(int rowNum=2; rowNum<rows; rowNum++)
		{
			String data=excel.getCellData(Constants.SUIT_SHEET, Constants.SUITENAME_COL, rowNum);
			if(data.equals(SuiteName)) {
				
				String runmode=excel.getCellData(Constants.SUIT_SHEET, Constants.RUNMODE_COL , rowNum);
				if(runmode.equals(Constants.RUNMODE_YES))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public static boolean isTestRunnable(String testCaseName, Excel_reader excel)
	{
		int rows=excel.getRowCount(Constants.TESTCASE_SHEET);
		
		for(int rowNum=2; rowNum<=rows; rowNum++)
		{
			String data=excel.getCellData(Constants.TESTCASE_SHEET, Constants.TESTCASE_COL, rowNum);
			
			if(data.equals(testCaseName))
			{
				String runmode=excel.getCellData(Constants.TESTCASE_SHEET, Constants.RUNMODE_COL, rowNum);
				if(runmode.equals(Constants.RUNMODE_YES))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	@DataProvider(name="dataProvider")
	public static Object[][] dataProvider(String testcase,Excel_reader excel) {
		
		 excel=new Excel_reader(Constants.excelpath);
		String sheetName="TestData";
		int rows=excel.getRowCount(sheetName);
		System.out.println("Total Rows:-  "+rows);
		
		String TestName=testcase; // AddCustomerTest  OpenAccountTest
				
		
		//Find The test case Start Now
		
		int testCaseRowNum=1;
		for(testCaseRowNum=1; testCaseRowNum<rows; testCaseRowNum++)
		{
			String testCaseName=excel.getCellData(sheetName, 0, testCaseRowNum);
			if(testCaseName.equalsIgnoreCase(TestName))
			   break;
			
		}
		System.out.println("Test Case Start From row Num:- "+testCaseRowNum);
		
		
		
		// Checking total Rows in Test Case
		
		int dataStartRownum=testCaseRowNum+2;
		
		int testRows=0;
		
		while(!excel.getCellData(sheetName, 0, dataStartRownum+testRows).equals("")) {
			testRows++;
		}
		System.out.println("Total Rows of Data are :- "+testRows);
		
		
		//Checking Total Columns in Test Case
		
		int colStartColNum = testCaseRowNum+1;
		int testCols=0;
		
		while(!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {
			testCols++;
		}
		System.out.println("Total Column of Data are: -"+testCols);
		
		
		// Printing data
		Object [][] data=new Object[testRows][1];
		
		int i=0;
		for(int rNum=dataStartRownum; rNum<(dataStartRownum+testRows);rNum++ ) {
			
			Hashtable<String ,String>table=new Hashtable<String, String>();
			
			for(int cNum=0; cNum<testCols; cNum++) {
				
			//	System.out.println(excel.getCellData(sheetName, cNum, rNum));
				String testdata=excel.getCellData(sheetName, cNum, rNum);
				String colName=excel.getCellData(sheetName, cNum, colStartColNum);
				
				table.put(colName, testdata);
			}
			data[i][0]=table;
			i++;
		}
		
		return data;
	}


}
