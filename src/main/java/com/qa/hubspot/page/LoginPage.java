package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	// By Locators
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By forgotPwdLink = By.linkText("Forgot my password");
	By signUpLink = By.linkText("Sign up");
	By rememberCkoBox = By.xpath("//span[@class='private-checkbox__icon private-checkbox__dash']");
	By ErrorMessage = By.xpath("//div[@class='private-alert__inner']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Actions (Methods)
	
	public String getPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(Credentials credUser) {
		elementUtil.waitForElementPresent(username);
		elementUtil.doSendKeys(username, credUser.getAppUserName());
		elementUtil.doSendKeys(password, credUser.getAppPassword());
		elementUtil.doClick(loginBtn);
		return new HomePage(driver);
	}
	
	public void clickCheckBox() {
	//	driver.findElement(rememberCkoBox).click();
		elementUtil.doClick(rememberCkoBox);
	}
	
	public boolean isErrorMessageDisplayed() {
		return elementUtil.doIsDisplayed(ErrorMessage);
	}
}
