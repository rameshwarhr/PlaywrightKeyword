package rough;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelManager.Excel_reader;

public class TestParameterization {

	@Test(dataProvider="dataProvider")
	public void test(Hashtable<String,String>data)
	{
		System.out.println(data.get("Runmode")+"------"+data.get("customer")+"-------"+data.get("currency"));
	}
	
	@DataProvider(name="dataProvider")
	public Object[][] dataProvider() {
		
		Excel_reader excel=new Excel_reader("C:\\Users\\user2\\Desktop\\playwright_Data.xlsx");
		String sheetName="TestData";
		int rows=excel.getRowCount(sheetName);
		System.out.println("Total Rows:-  "+rows);
		
		String TestName="OpenAccountTest"; // AddCustomerTest  OpenAccountTest
				
		
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
