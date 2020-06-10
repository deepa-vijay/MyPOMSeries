package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	ContactsPage contactsPage;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//By accountName = By.xpath("//span[@class='account-name ']");
	//By headerTitle = By.xpath("//h1[@class='private-header__heading private-header__heading--solo']");
	
    By headerTitle = By.cssSelector("h1.private-header__heading private-header__heading--solo");
    By accountName = By.cssSelector("span.account-name");
	By ContactsMenu = By.id("nav-primary-contacts-branch");
	By ContactsLink = By.id("nav-secondary-contacts"); 
	
	public String getUserName() {
		return elementUtil.doGetText(accountName);
	}
	
	public String getHeaderTitle() {
		elementUtil.waitForTitlePresent(headerTitle, 10);
		return elementUtil.doGetText(headerTitle);
	}
	
	public ContactsPage clickOnContactsMenu() {
		elementUtil.waitForElementPresent(ContactsMenu);
		elementUtil.doClick(ContactsMenu);
		
		elementUtil.waitForElementPresent(ContactsLink);
		elementUtil.doClick(ContactsLink);
		return new ContactsPage(driver);
		
	}
}
