package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	// By Locators
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By forgotPwdLink = By.linkText("Forgot my password");
	By signUpLink = By.linkText("Sign up");
	By rememberCkoBox = By.xpath("//span[@class='private-checkbox__icon private-checkbox__dash']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	// Actions (Methods)
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean checkSignUpLink() {
		return driver.findElement(signUpLink).isDisplayed();
	}
	
	public void doLogin(String email, String pwd) {
		driver.findElement(username).sendKeys(email);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
	}
	
	public void clickCheckBox() {
		driver.findElement(rememberCkoBox).click();
	}
}
