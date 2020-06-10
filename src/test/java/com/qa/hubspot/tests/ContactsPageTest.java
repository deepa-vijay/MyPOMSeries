package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credUser;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		credUser = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credUser);
		contactsPage = homePage.clickOnContactsMenu();
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData("Contacts");
		return data;
	}
	
	@Test (dataProvider= "getContactsTestData")
	public void readTestData(String email, String FName, String LName, String JTitle) {
		contactsPage.createNewContact(email, FName, LName, JTitle);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}