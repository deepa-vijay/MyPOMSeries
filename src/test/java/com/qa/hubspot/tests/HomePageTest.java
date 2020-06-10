package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {

	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credUser;
	ContactsPage contactsPage;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		credUser = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credUser);
		//homePage = new HomePage(driver);
		Thread.sleep(5000);
	}
	
	@Test (priority = 1)
	public void verifyHeaderTitleTest() {
		String headerName = homePage.getHeaderTitle();
		System.out.println("Header Title" + headerName);
		Assert.assertEquals(headerName, "Thanks for choosing HubSpot");
	}
	
	/*@Test (priority = 2)
	public void verifyLoggedInUserTest() {
		String userName = homePage.getUserName();
		System.out.println("Account Name" + userName);
		Assert.assertEquals(userName, "Test Automation");
	}*/
	
	@Test (priority = 2)
	
	public void clickContactsMenu() {
	//	homePage.clickOnContactsMenu();
		contactsPage = homePage.clickOnContactsMenu();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
