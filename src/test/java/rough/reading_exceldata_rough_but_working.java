package rough;

import org.testng.annotations.Test;

import ExcelManager.Excel_reader;

public class reading_exceldata_rough_but_working 
{
	public static String path="C:\\Users\\user2\\Desktop\\playwright_Data.xlsx";
	Excel_reader excel=new Excel_reader(path);
	public static String sheetName="TestData";
	@Test
	public void excel_data()
	{
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
		
		for(int rNum=dataStartRownum; rNum<(dataStartRownum+testRows);rNum++ ) {
			for(int cNum=0; cNum<testCols; cNum++) {
				
				System.out.println(excel.getCellData(sheetName, cNum, rNum));
			}
		}
	}

}
