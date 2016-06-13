package com.training.seleniumclass;
/*This code snippet automates logging into gmail with the userid and password then sign out
 *This code demonstrates the use of implicit and explicit waits. Implicit wait in line 20 to wait for a specific time
 *Demonstrates Explicit wait using WebDriverWait 
 *It tells the Web Driver to wait for certain condition OR maximum time to wait before throwing "ElementNotVisibleException" exception.
 *
 */
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailAutomation {
	 public static void main(String[] args) throws InterruptedException {
	 
	 WebDriver driver= new FirefoxDriver();
	 driver.get("http://www.gmail.com/");
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 driver.findElement(By.id("Email")).sendKeys("Your UserID");
	 WebElement element=driver.findElement(By.id("Email"));
	 WebDriverWait wait=new WebDriverWait(driver,10);
	//WebElement element1=wait.until(ExpectedConditions.elementToBeClickable(By.id("Email")));
	 WebElement element1=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
	 System.out.println("Element wait unitl wait" +element1.getAttribute("id"));
	 String value=element.getAttribute("value");
	 System.out.println("Value entered for EmailID = "+value);
	 if(value.equals("Your UserID"))
	    System.out.println("TextBox value match");
	 else
	    System.out.println("Value mismatch");
	 WebElement next = driver.findElement(By.id("next"));
	 next.click();
	 driver.findElement(By.id("Passwd")).sendKeys("Your Password");
	 driver.findElement(By.id("signIn")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
	 Thread.sleep(10000);
	 driver.findElement(By.id("gb_71")).click();
	 driver.quit();
	 
	 }
}
	 
