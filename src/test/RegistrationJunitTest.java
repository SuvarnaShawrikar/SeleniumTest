package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class contains two test scenarios one for Positive Registration Work flow and
 * one for Negative Registration Work flow
 * 
 * @author Suvarna
 *
 */
class RegistrationJunitTest {

	@Test
	/**
	 * Registration Method - Implement test for Registration and then call Login
	 * Method to check successful login.
	 */
	void positiveRegistrationTestCase() {

		// Invoke the web browser and navigating to the website.
		System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://react-redux-registration-login-example.stackblitz.io/login");

		// Wait for the element to load.
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Find the xpath for register and click on it.
		WebElement register = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/a"));
		register.click();

		// Define variables for registration form.
		String fname = "Sara";
		String lname = "Sana";
		String uname = "sana";
		String pw = "sana123";

		// Enter Firstname, Lastname, Username & password on registration form.
		WebElement firstname = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[1]/input"));
		firstname.sendKeys(fname);
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[2]/input"));
		lastname.sendKeys(lname);
		WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/input"));
		username.sendKeys(uname);
		WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[4]/input"));
		password.sendKeys(pw);

		// Wait for the elements to load.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Click on the registration button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// Check whether the message on the registration was successful or not.
		String actualString = driver.findElement(By.cssSelector("#app > div > div > div > div.alert.alert-success"))
				.getText();
		assertTrue(actualString.contains("Registration successful"));

		// Call the login method.
		login(driver, uname, pw, fname, lname);

	}

	/**
	 * Login Method - Enters correct Credentials check if the user’s first name is
	 * correctly displayed once logged in (i.e. Hi FirstName!) and then check if
	 * their full name appears under the “All registered users” list.
	 * 
	 * @param driver
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 */
	void login(WebDriver driver, String username, String password, String firstname, String lastname) {

		// Wait for element to load.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Enter username and password inside appropriate form.
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[1]/input")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[2]/input")).sendKeys(password);

		// Click on login button.
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/button")).click();

		// Assert whether Hi FirstName! is displayed properly.
		String hellomsg = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/h1")).getText();
		assertTrue(hellomsg.contains(firstname));

		// Check if their fullname appears under the “All registered users” list.
		String reguser = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/ul/li")).getText();
		assertTrue(reguser.contains(firstname + " " + lastname));

		// Close the Browser
		driver.close();
	}

	@Test
	/**
	 * Test whether all mandatory fields are showing appropriate error messages when
	 * left blank.
	 */
	void negativeRegistrationTestCase() {
		// Invoke the web browser and navigating to the website.
		System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://react-redux-registration-login-example.stackblitz.io/login");

		// Wait for the element to load.
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Find the xpath for register and click on it.
		WebElement register = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/a"));
		register.click();

		// Wait for the elements to load.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Click on the registration button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// display the message.
		checkFirstNameError(driver);
		checkLastNameError(driver);
		checkUserNameError(driver);
		checkPasswordError(driver);

		// Define variables for registration form.
		String fname = "fghjk";
		String lname = "dfghj";
		String uname = "dfgh";
		String pw = "fghj";

		// Enter first name.
		WebElement firstname = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[1]/input"));
		firstname.sendKeys(fname);

		// Click on the registration button.
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// Call the methods to check the errors
		checkLastNameError(driver);
		checkUserNameError(driver);
		checkPasswordError(driver);

		// Enter last name.
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[2]/input"));
		lastname.sendKeys(lname);

		// Click on the registration button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// Call the methods to check the errors
		checkUserNameError(driver);
		checkPasswordError(driver);

		// Enter Username.
		WebElement username = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/input"));
		username.sendKeys(uname);

		// Click on the registration button
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// Call the methods to check the errors
		checkPasswordError(driver);

		// Enter password.
		WebElement password = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[4]/input"));
		password.sendKeys(pw);

		// Click on the registration button.
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[5]/button")).click();

		// Check whether the message on the registration was successful or not.
		String actualString = driver.findElement(By.cssSelector("#app > div > div > div > div.alert.alert-success"))
				.getText();
		assertTrue(actualString.contains("Registration successful"));

		// Close browser.
		driver.close();

	}

	// Method to check whether First name is correctly entered.
	private void checkFirstNameError(WebDriver driver) {
		String msg = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[1]/div")).getText();
		assertTrue(msg.contains("First Name is required"));
	}

	// Method to check Password name.
	private void checkPasswordError(WebDriver driver) {
		String msg;
		msg = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[4]/div")).getText();
		assertTrue(msg.contains("Password is required"));
	}

	// Method to check Username.
	private void checkUserNameError(WebDriver driver) {
		String msg;
		msg = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/div")).getText();
		assertTrue(msg.contains("Username is required"));
	}

	// Method to check Last Name.
	private void checkLastNameError(WebDriver driver) {
		String msg;
		msg = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[2]/div")).getText();
		assertTrue(msg.contains("Last Name is required"));
	}
}
