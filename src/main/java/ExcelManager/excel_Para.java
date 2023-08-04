package ExcelManager;

public class excel_Para 
{
	public Object[][] gettestData()
	{
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
		Object [][] data=new Object[testRows][testCols];
		
		for(int rNum=dataStartRownum; rNum<(dataStartRownum+testRows);rNum++ ) {
			for(int cNum=0; cNum<testCols; cNum++) {
				
			//	System.out.println(excel.getCellData(sheetName, cNum, rNum));
				data[rNum-dataStartRownum][cNum]=excel.getCellData(sheetName, cNum, rNum);
			}
		}
		
		return data;
	}
	}


