package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class contains scenario to Handle the Negative Alert message work flow
 * 
 * @author Suvarna
 *
 */
class AlertMessageHandleJunitTest {

	/**
	 * This method handles the login on Rediffmail.com, Click on go button without entering the credentials.
	 * Alert message will pop up and will ask to enter the username, click on ok on pop up alert.
	 * Enter the username and click on go button. Then another alert will pop-up asking for password.
	 * Click on Ok and enter the password then click go button.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void AlertMessageHandle() throws InterruptedException {
		
		// Invoke the web browser and navigating to the website.
		System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
		// Call handleAlert method
		handleAlertMessage(driver,"Please enter a valid user name");
        
        // Enter login credentials
        driver.findElement(By.name("login")).sendKeys("abc");
        handleAlertMessage(driver, "Please enter your password");
        
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.name("proceed")).click();
        
     // Check whether the message on the login was successful or not.
        String str = driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div[2]/div[4]/div")).getText();
        System.out.println(str);
        assertTrue(str.contains("Wrong username and password combination"));
        
        // Close the window
        driver.close();
	}

	private void handleAlertMessage(WebDriver driver, String text) throws InterruptedException {
		// Click on go button to get Alert message
		driver.findElement(By.name("proceed")).click();
		Thread.sleep(2000);
		
		// Alert Message handling------ Switch to Alert        
        Alert alert = driver.switchTo().alert();	
        
        // Capture the alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Display the alert message		
        System.out.println(alertMessage);	
        Thread.sleep(2000);
        
        // Check whether message is correctly displayed.
        String msg = alert.getText();
        assertTrue(text.equals(msg));
        
        		
        // Accepting alert	---click on ok button	
        alert.accept();	
        
	}

}
