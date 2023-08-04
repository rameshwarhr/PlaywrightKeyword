package suit.customer.testcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelManager.DataUtil;
import ExcelManager.Excel_reader;
import Utility.DataProviders;

public class OpenAccountTest 
{
	@Test(dataProviderClass=DataProviders.class, dataProvider="bankManagerDP")
	public void openAccountTest(Hashtable<String,String>data)
	{
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return DataUtil.dataProvider("OpenAccountTest", new Excel_reader("C:\\Users\\user2\\Desktop\\playwright_Data.xlsx"));
//	}

}
