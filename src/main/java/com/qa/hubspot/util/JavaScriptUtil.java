package com.qa.hubspot.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for(int i=0; i<1; i++) {
			changeColor("rgb(0,200,0)",element);
			changeColor(bgcolor,element);
		}
	}
	
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "' ", element);
		try {
			Thread.sleep(5000);
		}
		catch(Exception e) {
			
		}
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
	}	
	
	public void pageRefreshByJS() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("history.go(0)");
	}
	
	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("argument[0].scrollIntoView(true);", element);
	}
	
	public String getBrowserInfo() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String uAgent = js.executeScript("return navigator.userAgent;").toString();
		return uAgent;
	}
	
	public void sendKeysUsingJSWithID(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
}
