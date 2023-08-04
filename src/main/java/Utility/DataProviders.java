package Utility;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import ExcelManager.DataUtil;
import ExcelManager.Excel_reader;

public class DataProviders 
{
	
	

	@DataProvider(name="bankManagerDP")
	public static Object[][] getDataSuit1(Method m) {

		System.out.println(m.getName());
		Excel_reader excel=new Excel_reader(Constants.excelpath);
		String testcase=m.getName();
		return DataUtil.dataProvider(testcase, excel);
	}
	
	
	
	
	@DataProvider(name="customerDP")
	public static Object[][] getDataSuit2(Method m) {

		System.out.println(m.getName());
		Excel_reader excel=new Excel_reader(Constants.excelpath);
		String testcase=m.getName();
		return DataUtil.dataProvider(testcase, excel);
	}

}
