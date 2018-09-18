package test;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class contains two scenarios for Handling Pop Up for the browser window pop-up : 
 * WindowHandler API- getWindowHandles()
 * and JavaScript Pop up - Alert messages: Alert API (accept,dismiss).
 * 
 * @author Suvarna
 *
 */
class WindowPopUpHandlerJunitTest {

	/**
	 * Handle Window Pop-up method - This method shows how to switch from main window to pop-up window then get the title of the page.
	 * Then switch back to the main window and get the title of the main window. 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void HandleWindowPopUp() throws InterruptedException {
		//Invoke Browser
		System.setProperty("webdriver.chrome.driver","/Users/Suvarna/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//Launch the website
		driver.get("http://www.popuptest.com/goodpopups.html");
		
		// Click on the pop-up3 link
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/font/b/a[3]")).click();
		
		// wait for 2 second
		Thread.sleep(2000);
		
		// Call method to handle window  
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();
		
		// get the parent window id
		String parentWindowId = it.next();
		System.out.println("Parent Window id: "+ parentWindowId);
		
		// Get the Pop-up window id
		String childWindowId =it.next();
		System.out.println("Child Window id: "+ childWindowId);
		
		//Passing the control to pop up window ...Switch to pop-up window
		driver.switchTo().window(childWindowId);
		
		Thread.sleep(2000);
		
		// Get the title of the Pop-up Window
		System.out.println(" Child window pop up title "+ driver.getTitle()); 
		
		//Close the pop-up window
		driver.close();
		
		//Switch to Parent Window
		driver.switchTo().window(parentWindowId);
		
		Thread.sleep(2000);
		
		//Get the title of Parent window
		System.out.println(" Parent window title "+ driver.getTitle());
		
		// Close the main window
		driver.close();
		
	}
	
	
	/**
	 * Alert Message Handle method - This method handles the Alert message JavaScript Pop Up
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void AlertMessageHandle() throws InterruptedException {
		
		// Invoke the web browser and navigating to the website.
		System.setProperty("webdriver.chrome.driver", "/Users/Suvarna/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		// Alert Message handling
        driver.get("http://demo.guru99.com/test/delete_customer.php");			
        
        driver.findElement(By.name("cusid")).sendKeys("567876");					
        driver.findElement(By.name("submit")).submit();			
        		
        // Switch to Alert        
        Alert alert = driver.switchTo().alert();	
        
        // Capture the alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Display the alert message		
        System.out.println(alertMessage);	
        Thread.sleep(5000);
        		
        // Accepting alert		
        alert.accept();	
        
        // Close the window
        driver.close();
	}

	}


