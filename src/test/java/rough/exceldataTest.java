package rough;

import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelManager.Excel_reader;

public class exceldataTest 
{
	public static String path="C:\\Users\\user2\\Desktop\\playwright_Data.xlsx";
	Excel_reader excel=new Excel_reader(path);
	
	@Test(dataProvider="dataProvider")
	public void getdata(Map<String, String >data)
	{
		System.out.println(data);
	}
	
	
	@DataProvider(name="dataProvider")
	public  Object[][]dataprovide() throws InvalidFormatException
	{
		Object[][]bb=excel.getSheetData("Sheet2");
		return bb;
	}

}
