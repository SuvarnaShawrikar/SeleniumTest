package test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
/**
 * This class explains two test scenarios for Headless Browser testing
 * one using Chrome Browser & another using HtmlUnitDriver.
 * 
 * @author Suvarna
 *
 */
class HeadlessJunitTest {
	/**
	 * This method explains concept of headless browser using ChromeOptions 
	 * 
	 * @throws InterruptedException
	 */

	@Test
	void headlessChromeTest() throws InterruptedException {
		// Chrome browser
				System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");

				// Headless Chrome Browser (Chrome version should be greater than 59 on mac &
				// greater than 60 on windows)
				// Give window-size= 1400,800
				ChromeOptions options = new ChromeOptions();
				options.addArguments("window-size= 1400,800");
				options.addArguments("headless");

				// Launch Chrome
				WebDriver driver = new ChromeDriver(options);

				// Dynamic wait
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Enter URL
				driver.get("https://www.freecrm.com/index.html");

				System.out.println("Login Page Title: " + driver.getTitle());
				assertTrue((driver.getTitle()).equals("#1 Free CRM software in the cloud for sales and service"));

				// Enter the credentials
				driver.findElement(By.name("username")).sendKeys("seltest");
				driver.findElement(By.name("password")).sendKeys("123456");
				// Wait for 3 sec
				Thread.sleep(3000);
				// Click login button
				driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/input")).click();

				System.out.println("Home Page Title: " + driver.getTitle());
				assertTrue((driver.getTitle()).equals("CRMPRO"));
				
				// Close browser
				driver.close();

			}
	/**
	 *  This method explains concept of headless browser using HtmlUnitDriver
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void headlessHtmlUnitDriverTest() throws InterruptedException {
		
				System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");

				// HtmlUnit Driver advantages
				// Testing is happening behind the scene - no browser is launch
				// Execution of test case is very fast which improves performance of test cases
				// Not suitable for action class - double click, mouse movement, drag & drop
				// This is also called Ghost driver or Headless browser
				
				// Launch HtmlUnitDriver
				WebDriver driver = new HtmlUnitDriver();

				// Maximize window
				driver.manage().window().maximize();
				// Delete all the cookies
				driver.manage().deleteAllCookies();
				// Dynamic wait
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Enter URL
				driver.get("https://www.freecrm.com/index.html");

				System.out.println("Before login Title is: " + driver.getTitle());
				assertTrue((driver.getTitle()).equals("#1 Free CRM software in the cloud for sales and service"));

				// Enter the credentials
				driver.findElement(By.name("username")).sendKeys("seltest");
				driver.findElement(By.name("password")).sendKeys("123456");
				// Wait for 3 sec
				Thread.sleep(3000);
				// Click login button ( dynamic xpath : "//input[@type='submit']")
				driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/input")).click();

				System.out.println("After login Title is: " + driver.getTitle());
				assertTrue((driver.getTitle()).equals("CRMPRO"));

				// close browser
				driver.close();
	}
	}

