package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * This class explains the work flow of freecrm.com, it creates the two new
 * contacts, one using contact tab and other using quick contact. After that it
 * verify the contacts are present or not by taking screenshot and then delete
 * all the contacts created.
 * 
 * @author Suvarna
 *
 */

class NewContactWorkFlowJunitTestCase {
		/**
		 * This method creates two new contacts one using contact tab and other using
		 * quick contact. Then verify the contacts are present or not by taking
		 * screenshot and then delete all the contacts created.
		 * 
		 * @param args
		 * @throws InterruptedException
		 * @throws IOException
		 */
		@Test
		void newContactWorkFlow()throws InterruptedException, IOException {

			WebDriver driver = invokeBrowser();
			login(driver);

			// Switch to main panel and verify whether we are on correct page by checking
			// the title
			String str = driver.switchTo().frame("mainpanel").getTitle();
			assertTrue(str.contains("CRMPRO"));

			Actions action;
			WebElement web;
			createNewContactByTabContact(driver);
			createNewContactByQuickCreate(driver);

			// Select contacts using action
			action = new Actions(driver);
			web = driver.findElement(By.linkText("CONTACTS"));
			action.moveToElement(web).click().build().perform();

			// Check the new user added
			String newuser = driver.findElement(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr[4]/td[2]/a")).getText();
			assertTrue(newuser.contains("Sam Todd"));

			deleteAllRecords(driver);

			// Click on log out button
			driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]/td[1]/div/table/tbody/tr/td[4]/a")).click();

			// close the browser
			driver.quit();
		
	}
		// This method invokes the browser
		private static WebDriver invokeBrowser() {
			// Chrome browser
			System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");
			// Launch Chrome
			WebDriver driver = new ChromeDriver();
			// Maximize window
			driver.manage().window().maximize();
			// Delete all the cookies
			driver.manage().deleteAllCookies();
			// Dynamic wait
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Enter URL
			driver.get("https://www.freecrm.com/index.html");
			return driver;
		}

		// This method enters the credentials on freecrm.com and click on login button
		private static void login(WebDriver driver) throws InterruptedException {
			// Enter the credentials
			driver.findElement(By.name("username")).sendKeys("seltest");
			driver.findElement(By.name("password")).sendKeys("123456");
			// Wait for 3 sec
			Thread.sleep(3000);
			// Click login button
			driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/input")).click();
		}

		// This method create new contact using contact tab
		private static void createNewContactByTabContact(WebDriver driver) throws IOException {
			// Select contacts using action
			Actions action = new Actions(driver);
			WebElement web = driver.findElement(By.linkText("CONTACTS"));
			action.moveToElement(web).moveToElement(driver.findElement(By.xpath("//*[@id=\"navmenu\"]/ul/li[4]/ul/li[1]")))
					.click().build().perform();

			// Add new user
			driver.findElement(By.id("first_name")).sendKeys("Sam");
			driver.findElement(By.id("surname")).sendKeys("Todd");

			// Take screenshot and store it as a file format
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Copy the screenshot to desired location using copy file method
			FileUtils.copyFile(scr, new File(
					"/Users/Suvarna/eclipse-selenium-workspace/SeleniumSessions/src/SeleniumTestCases/new-user-created.png"));

			// Create and add more users
			driver.findElement(By.name("addmore")).click();

			// Check whether the contact is created or not
			String msg = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr/td"))
					.getText();
			assertTrue(msg.contains("Contact created"));
		}

		// This method create new contact using quick create
		private static void createNewContactByQuickCreate(WebDriver driver) {

			driver.findElement(By.cssSelector("#navMenu > ul > li:nth-child(3) > a")).click();

			// Create new user
			driver.findElement(By.name("company_name")).sendKeys("ABC Corporations");
			driver.findElement(By.name("contact_first_name")).sendKeys("Tom");
			driver.findElement(By.name("contact_surname")).sendKeys("Hiks");

			// Click on create
			driver.findElement(By.xpath("//*[@id=\"quickCreateForm\"]/form/table/tbody/tr[1]/td/input[1]")).click();

			// close the alert
			driver.findElement(By.xpath("//*[@id=\"ibox_footer_wrapper\"]/a")).click();

			driver.navigate().refresh();

			driver.switchTo().frame("mainpanel");
		}

		// This method delete all the records
		private static void deleteAllRecords(WebDriver driver) throws InterruptedException {

			driver.findElement(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr[3]/td[1]/input")).click();
			Select sel = new Select(driver.findElement(By.name("do_action")));
			sel.selectByVisibleText("Delete Checked");

			// Wait for 2 sec
			Thread.sleep(2000);

			// Click on do button
			driver.findElement(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr[2]/td[2]/input")).click();

			// Switch to alert
			Alert alert = driver.switchTo().alert();
			String text = alert.getText();
			assertTrue(text.equals(
					"Are you sure you want to delete all selected contacts? \n" + "You cannot undo this operation!"));

			// wait for 2 secs
			Thread.sleep(2000);
						
			// Click on ok button
			alert.accept();

			// wait for 2 secs
			Thread.sleep(2000);
		}

		 

}
