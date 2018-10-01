package SeleniumTestCases;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * This class contains test scenario for some functions used in Action class
 * such as drag & drop action, sliders - click & hold action.
 * 
 * @author Suvarna
 *
 */

class ActionsClassWorkFlowJUnitTestCase {
	/**
	 * This method handles some actions performed such as drag & drop, click & hold, resize the element, 
	 * actions on select radio button, check box.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	void ActionClassWorkFlow() throws InterruptedException {
		// Set the property for chrome driver
		System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");

		// Launch Chrome
		WebDriver driver = new ChromeDriver();

		// Action class
		Actions action = new Actions(driver);

		// Maximize window
		driver.manage().window().maximize();

		// Delete all the cookies
		driver.manage().deleteAllCookies();

		// Dynamic wait
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Enter URL
		driver.get("https://jqueryui.com/");

		dragnDrop(driver, action);

		resizable(driver, action);

		sliders(driver, action);

		menu(driver, action);

		checkBoxRadioButtton(driver);

		// Quit the browser
		driver.quit();

	}

	private static void checkBoxRadioButtton(WebDriver driver) throws InterruptedException {
		String actual;
		// click checkboxradio button
		driver.navigate().refresh();
		driver.findElement(By.linkText("Checkboxradio")).click();

		// Verify whether we are on correct page
		actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		assertTrue(actual.contains("Checkboxradio"));

		// Wait for 3 secs
		Thread.sleep(3000);

		// Switch to frame
		driver.switchTo().frame(0);

		// click on radio button
		WebElement select = driver.findElement(By.xpath("/html/body/div/fieldset[1]/label[2]/span[1]"));
		select.click();

		// Wait for 3 secs
		Thread.sleep(3000);

		// Select check boxes
		driver.findElement(By.xpath("/html/body/div/fieldset[2]/label[4]/span[1]")).click();
		driver.findElement(By.xpath("/html/body/div/fieldset[3]/label[2]/span[1]")).click();

		Thread.sleep(3000);
	}

	private static void menu(WebDriver driver, Actions action) throws InterruptedException {
		String actual;
		// Click on menu
		driver.navigate().back();
		driver.findElement(By.linkText("Menu")).click();

		// Verify whether we are on correct page
		actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		assertTrue(actual.contains("Menu"));

		// Switch to frame
		driver.switchTo().frame(0);

		// select music
		driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]")).click();
		
		// Wait for 2 secs
		Thread.sleep(2000);
	}

	private static void sliders(WebDriver driver, Actions action) throws InterruptedException {
		String actual;
		// Click on the slider
		driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[2]/ul/li[11]/a")).click();

		// Verify whether we are on correct page
		actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		assertTrue(actual.contains("Slider"));

		// dynamic wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Click on multiple sliders on right hand side
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/ul/li[4]/a")).click();

		// Wait for 3 secs
		Thread.sleep(3000);

		// Switch to frame there is only 1 frame so we can use frame(0)
		driver.switchTo().frame(0);

		// Perform actions on slider
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"master\"]/span"));
		action.clickAndHold(slider).moveByOffset(40, 0).release(slider).build().perform();

		// Wait for a secs
		Thread.sleep(1000);

		// Perform action on sliders
		slider = driver.findElement(By.xpath("//*[@id=\"eq\"]/span[1]/span"));
		action.clickAndHold(slider).moveByOffset(0, 30).release(slider).build().perform();

		// Wait for 1 secs
		Thread.sleep(1000);

		// Perform action on sliders
		slider = driver.findElement(By.xpath("//*[@id=\"eq\"]/span[2]/span"));
		action.clickAndHold(slider).moveByOffset(0, 40).release(slider).build().perform();

		// Wait for 1 secs
		Thread.sleep(1000);

		// Perform action on sliders
		slider = driver.findElement(By.xpath("//*[@id=\"eq\"]/span[4]/span"));
		action.clickAndHold(slider).moveByOffset(0, -30).release(slider).build().perform();

		// Wait for 1 secs
		Thread.sleep(1000);

		// Perform action on sliders
		slider = driver.findElement(By.xpath("//*[@id=\"eq\"]/span[5]/span"));
		action.clickAndHold(slider).moveByOffset(0, 20).release(slider).build().perform();

		// Wait for 2 secs
		Thread.sleep(2000);
	}

	private static void resizable(WebDriver driver, Actions action) throws InterruptedException {
		String actual;
		// Click on resizable on left
		driver.findElement(By.linkText("Resizable")).click();

		// Verify whether we are on correct page
		actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		assertTrue(actual.contains("Resizable"));

		// dynamic wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Switch to frame there is only 1 frame so we can use frame(0)
		driver.switchTo().frame(0);

		// find xpath for draggable webelement
		WebElement resize = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));

		// Wait for 2 secs
		Thread.sleep(2000);

		// Perform action click & hold
		action.clickAndHold(resize).moveByOffset(100, 50).release(resize).build().perform();

		// Wait for 2 secs
		Thread.sleep(2000);

		driver.navigate().back();
	}

	private static void dragnDrop(WebDriver driver, Actions action) throws InterruptedException {
		// Click on droppable webelement
		driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[1]/ul/li[2]/a")).click();

		// Verify whether we are on correct page
		String actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		assertTrue(actual.contains("Droppable"));

		// dynamic wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Switch to frame there is only 1 frame so we can use frame(0)
		driver.switchTo().frame(0);

		// Dragging 1 webelement and then dropping it to another place
		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));

		// Perform double click
		action.doubleClick(drag);

		// Wait for 2 secs
		Thread.sleep(2000);

		// Perform drag & drop action
		action.dragAndDrop(drag, drop).perform();

		// Wait for 2 sec
		Thread.sleep(2000);

		// Perform click & hold
		action.clickAndHold(drag).moveToElement(drag).release(drag).build().perform();

		// Wait for 2 secs
		Thread.sleep(2000);

		// refresh browser and navigate to previous page
		driver.navigate().refresh();
		driver.navigate().back();
	}
}
