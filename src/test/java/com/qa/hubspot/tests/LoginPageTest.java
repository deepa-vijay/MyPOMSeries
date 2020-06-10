package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.asserts.*;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginPage;
	Credentials credUser;
	
	@Epic("EPIC-1: Login Page Feature")
	@Feature("USER STORY-1 : Create Test Login for HubSpot")
	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		credUser = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitle() throws InterruptedException {
		//Thread.sleep(10000);
		
		String pageTitle = loginPage.getPageTitle();
		Assert.assertEquals(pageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	@Description("Verify SignUp Link")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@DataProvider
	public Object[][] getInvalidTestData() {
		Object data[][] = {
							{"test12@gmail.com", "test12"},
							{"test11@gmail.com", " "},
							{" ", "testsdd"},
							{" ", " "}
						  };
							
		return data;
	}
	
	@Test (priority = 5, dataProvider = "getInvalidTestData", enabled = false)
	public void doInvalidLogin(String userName, String password) {
		credUser.setAppUserName(userName);
		credUser.setAppPassword(password);
		loginPage.doLogin(credUser);
		Assert.assertTrue(loginPage.isErrorMessageDisplayed());
	}
	
	@Test(priority = 3)
	@Description("Verify Login in feature")
	@Severity(SeverityLevel.BLOCKER)
	public void doLogin() throws InterruptedException {
		HomePage homePage = loginPage.doLogin(credUser);
		Thread.sleep(5000);
		String headerTitle = homePage.getHeaderTitle();
		Assert.assertEquals(headerTitle, AppConstants.HOME_PAGE_HEADER_TITLE);
	}
	
	@Test (priority = 4)
	@Description("Verify the display of Remember Me Checkbox")
	@Severity(SeverityLevel.TRIVIAL)
	public void enableRememberMeCheckBox() {
		loginPage.clickCheckBox();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
