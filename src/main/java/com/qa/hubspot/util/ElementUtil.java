package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public void waitForElementVisible(By locator) {
		new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public boolean waitForTitlePresent(String titleValue) {
		wait.until(ExpectedConditions.titleIs(titleValue));
		return true;
	}
	
	public boolean waitForURLPresent(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
		return true;
	}
	
	public String doGetPageTitle() {
		try{
		return driver.getTitle();
		}
		catch(Exception e) {
			System.out.println("Exception occured while getting the page title....");
		}
		return null;
	}
	/**
	 * This method is used to create the WebElement based on the By locator
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
		 element = driver.findElement(locator);
		 if(highlightElement) {
			 jsUtil.flash(element);
		 }
		 
		}
		catch(Exception e) {
			System.out.println("Exception occured while creating the WebElement...");
		}
		return element;
	}

	public void doClick(By locator) {
		try {
		getElement(locator).click();
		}
		catch(Exception e) {
			System.out.println("Exception occured while clicking the WebElement...");
		}
	}
	
	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		}
		catch(Exception e) {
			System.out.println("Exception occured while entering the value...");
		}
	}

	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		}
		catch(Exception e) {
			System.out.println("Exception occured...");
		}
		return false;
	}
	
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}
		catch(Exception e) {
			System.out.println("Exception occured while getting the text from an WebElement...");
		}
		return null;
	}
	
	public void waitForAttributesToLoad(By locator, int timeout) {
    	WebElement element = null;
    	try {
    	for(int i=0; i<timeout; i++) {
    		element = getElement(locator);
    		String href = element.getAttribute("href");
    		System.out.println(href);
    		if(!href.isEmpty()) {
    			break;
    		}
    	}
    }
    	catch(Exception e) {
    		System.out.println("Element not found...");
    		
    		try {
    			Thread.sleep(5000);
    		}
    		catch(Exception e1) {
    			System.out.println("Exception occured...");
    				
    			}
    		}
    	}
	public void waitForTitlePresent(By locator, int timeout) {
    	WebElement element = null;
    	try {
    	for(int i=0; i<timeout; i++) {
    		element = getElement(locator);
    		
    			break;
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Element not found...");
    		
    		try {
    			Thread.sleep(5000);
    		}
    		catch(Exception e1) {
    			System.out.println("Exception occured...");
    				
    			}
    		}
    	}
	public void selectValueFromDropDown(By locator, String value) {
		
	   Select select = new Select (driver.findElement(locator));
	   select.selectByVisibleText(value);
   
	}

}