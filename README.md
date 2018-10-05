# SeleniumTest
This Repository contains Selenium Automation Tests 
1. src/test/RegistrationJunitTest.java - This class contains Positive and Negative Test Cases for Registration Workflow

2. src/test/ActionsClassWorkFlowJUnitTestCase.java - This class contains test scenario for some functions used in Action class
 such as drag & drop action, sliders - click & hold action.
 
3. src/test/AlertMessageHandleJunitTest.java - Class contains scenario to Handle the Negative Alert message work flow

4. src/test/WindowPopUpHandlerJunitTest.java -  Class contains two scenarios for Handling Pop Up for the browser
   window pop-up: 
 * WindowHandler API- getWindowHandles()
 * and JavaScript Pop up - Alert messages: Alert API (accept,dismiss).
 
 5. src/test/HeadlessJunitTest.java - Class contains two test scenarios for Headless Browser testing - one using Chrome Browser & another using HtmlUnitDriver.
 
 6. src/test/NewContactWorkFlowJunitTestCase.java - Class contains the work flow of freecrm.com, it creates the two new contacts, one using contact tab and other using quick contact. After that it verify the contacts are present or not by taking screenshot and then delete all the contacts created.
