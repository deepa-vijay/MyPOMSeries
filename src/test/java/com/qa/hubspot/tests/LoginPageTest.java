package com.qa.hubspot.tests;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.LoginPage;

import junit.framework.Assert;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyLoginPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		String pageTitle = loginPage.getPageTitle();
		Assert.assertEquals(pageTitle, "HubSpot Login");
	}
	
	@Test(priority = 2)
	public void verifySignUpLink() {
		assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority = 3)
	public void doLogin() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 4)
	public void enableRememberMeCheckBox() {
		loginPage.clickCheckBox();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
