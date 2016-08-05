package executionDriver;

import java.io.File;
import java.lang.reflect.Method;
import config.ActionKeys;
import utilities.Utilities;
/*This code launches firefox driver and opens the website using Firefox browser
 *It adds 5 kits to shopping cart by using 5 unique names
 *Automated using Selenium Webdriver + Java
 *Automated by: Rajeswari J
 *Date: 06/19/2016
 */

public class Driver {

	public static ActionKeys actionKeys;
	public static String sActionKeyword;
	public static Method method[];

	// Here we are instantiating a new object of class 'ActionKeywords'
	public Driver() throws NoSuchMethodException, SecurityException {
		actionKeys = new ActionKeys();
		method = actionKeys.getClass().getMethods();
		System.out.println("Method[0] = " + method[0]);
	}

	public static void main(String[] args) throws Exception {
		//String excelPath = "/Users/rajej/Eclipseworkspace/SeleniumTrain/WebTestFramework23/src/dataSource/DataSheet.xlsx";
		String filePath = new File("").getAbsolutePath();
		String excelPath =filePath.concat("/src/dataSource/DataSheet.xlsx");
		System.out.println("File Path = "+excelPath);
		Utilities.setExcelFile(excelPath, "TestSteps");
		Driver startEngine = new Driver();
		for (int iRow = 1; iRow <= 23; iRow++) {
			// This to get the value of column Action Keyword from the excel
			sActionKeyword = Utilities.getCellData(iRow, 3);
			System.out.println("Action Keys = " + sActionKeyword);
			startEngine.execute_Actions();

		}
	}

	private static void execute_Actions() throws Exception {
		System.out.println("method[] length = " + method.length);
		for (int i = 0; i < method.length; i++) {
			if (method[i].getName().equals(sActionKeyword)) {
				method[i].invoke(actionKeys);
				break;
			}
		}

	}

}
