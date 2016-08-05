package config;
import java.text.DecimalFormat;
import java.util.List;
/*This class has test methods for each test scenario defined in the excel sheet
 *This is used by the driver script to locate the elements
 *Object locators such as xpath, id can also be externalized in the excel sheet 
 */
import java.util.concurrent.TimeUnit;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.Utilities;


public class ActionKeys {
	public final static int numberofKits = 5;
	public final static int discount = 10;
	public static WebDriver driver;

	public static void openBrowser() {
		driver = new FirefoxDriver();
		deleteCookies();
	}

	public ActionKeys() {
	}
	
	public static void navigate() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(TestDataConstants.URL);
	}

	public static void click_Add_A_Kit() throws Exception {

		driver.findElement(By.xpath("//a[@class='js-add-kit']")).click();
		waitFor();

	}

	public static void verify_NoItems() {

		driver.findElement(By.xpath("//h2[contains(text(), 'There are currently no items in your order.')]"));

	}

	public static void waitFor() throws Exception {
		Thread.sleep(4000);
	}

	public static void click_Add_Another_Kit() {
		for (int i = 1; i < numberofKits; i++) {
			driver.findElement(By.xpath(".//img[@alt='Add another kit']")).click();
		}

	}

	public static void input_Name() throws Exception {
		int previd = 0;
		int currentid = 0, counter = 0;
		String name = null, inputidText = null;
		List<WebElement> allFormChildElements = driver.findElements(By.xpath("//div[@class='item-personalize']/*"));
		for (WebElement item : allFormChildElements) {
			if (item.getTagName().equals("input")) {
				inputidText = item.getAttribute("id");
				String numberOnly = inputidText.replaceAll("[^0-9]", "");
				currentid = Integer.parseInt(numberOnly);
				System.out.println("Text id and numeric id = " + inputidText + numberOnly);
				System.out.println("currentID = " + currentid);
				if (previd == 0 || currentid > previd) {
					name = Utilities.generateRandomString("Name" + (++counter), 100);
					item.sendKeys(name);
					waitFor();

				}
				previd = currentid;
			}
		}

	}

	public static void verify_Quantity() {

		driver.findElement(
				By.xpath("//div[contains(@class,'single quantity-number') and text()='" + numberofKits + "']"));

	}

	public static void verify_Discount() {

		driver.findElement(By.xpath("//div[contains(@class,'label') and text() = 'You Saved']"));
		driver.findElement(By.xpath("//div[contains(@class, 'price-display') and normalize-space(.)='$79.60']"));

	}

	public static void verify_Total() {
		double total = Utilities.calculateTotal();
		DecimalFormat df = new DecimalFormat("#.00");
		String price = df.format(total);
		driver.findElement(
				By.xpath("//div[contains(@class, 'price-display') and normalize-space(.)='$" + price + "']"));

	}

	// Shipping page
	public static void click_Submit_Button_To_Shipping() throws Exception {

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		waitFor();
		waitFor();

	}

	public static void input_FirstName() throws Exception {
		waitFor();
		driver.findElement(By.xpath(".//*[@id='id_first_name']")).sendKeys("Rajeswari");

	}

	public static void input_LastName() {

		driver.findElement(By.xpath(".//*[@id='id_last_name']")).sendKeys(TestDataConstants.Lastname);
	}

	public static void input_Address1() {

		driver.findElement(By.xpath(".//*[@id='id_address']")).sendKeys(TestDataConstants.Address1);
	}

	public static void input_Address2() {

		driver.findElement(By.xpath("//*[@id='id_address2']")).sendKeys(TestDataConstants.Address2);
	}

	public static void input_City() {

		driver.findElement(By.xpath(".//*[@id='id_city']")).sendKeys(TestDataConstants.City);

	}

	public static void input_State() {
		driver.findElement(By.xpath(".//*[@id='id_state']")).sendKeys(TestDataConstants.State);
	}

	public static void input_ZipCode() {
		driver.findElement(By.xpath(".//*[@id='id_postal_code']")).sendKeys(TestDataConstants.Zip);

	}

	public static void input_Email() {

		driver.findElement(By.xpath(".//*[@id='id_email']")).sendKeys(TestDataConstants.Email);
	}

	public static void input_Phone() {
		driver.findElement(By.xpath(".//*[@id='id_int_phone']")).sendKeys(TestDataConstants.Phone);

	}

	public static void click_Submit_Button_To_Address_Verification() throws Exception {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		waitFor();

	}

	//// We verified your shipping address. Click continue to proceed with your
	//// purchase.
	public static void click_Continue_Button_To_Payment_Page() throws Exception {
		driver.findElement(By.xpath(".//input[@class='button-continue']")).click();
		waitFor();
	}

	// Calculate order total including shipping charge
	public static void verify_Order() throws Exception {
		waitFor();
		double total = Utilities.calculateTotal() + TestDataConstants.Shipping;
		DecimalFormat df = new DecimalFormat("#.00");
		String price = df.format(total);// $945.35
		driver.findElement(By.xpath(".//strong[contains(@class,'payment-total')]"));
	}

	public static void close_Browser() {
		driver.close();
		driver.quit();

	}
	public static void deleteCookies(){
		 driver.manage().deleteAllCookies();
	}
}
