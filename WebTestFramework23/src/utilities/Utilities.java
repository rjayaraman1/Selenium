package utilities;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*This class contains common utility methods for the framework
 * genarateMyNumber() - Generates unique random number
 * genarateRandomString() - Creates unique String with a random number suffix
 * setExcelFile() - Opens the Excel file and sends excel path and sheet names to excel utility
 * calculateTotal() - Calculates total based on the item price, discount and quantity
 */
public class Utilities {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public static int generateMyNumber() {
		int aNumber = 0;
		aNumber = (int) ((Math.random() * 9000000) + 10);
		return aNumber;
	}

	public static String generateRandomString(String prefix, int number) {
		StringBuffer ret = new StringBuffer(prefix);
		ret.append(generateMyNumber());
		return ret.toString();

	}

	// Sets the File path,opens the Excel file, sending Excel Path and sheetName
	// as parameters to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	// This method is to read the test data from the Excel cell and parameters
	// are Row number,column number
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String CellData = Cell.getStringCellValue();
		return CellData;
	}

	public static double calculateTotal() {

		double itemprice = 199.0;
		double total = 0;
		int discount = 10;
		int qty = 4;
		total = itemprice + (qty * itemprice) - (qty * itemprice * discount / 100);
		return total;
	}
	
}
