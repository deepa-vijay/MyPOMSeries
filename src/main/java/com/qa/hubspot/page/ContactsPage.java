package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	By ContactsMenu = By.id("nav-primary-contacts-branch");
	By ContactsLink = By.id("nav-secondary-contacts"); 
	By CreateContactBtn = By.xpath("(//button[@type='button']//span[contains(text(), 'Create contact')])");
	By CreateContactFormBtn = By.xpath("//button[@type='button']//span[@class='private-loading-button__content private-button--internal-spacing' and contains (text(), 'Create contact')]");

	By Email = By.xpath("//input[@data-field='email']");
	By FirstName = By.xpath("//input[@data-field='firstname']");
	By LastName = By.xpath("//input[@data-field='lastname']");
	By JobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	public String getPageTitle() {
		elementUtil.waitForTitlePresent("Contacts");
		return elementUtil.doGetPageTitle();
	}
	
	public void createNewContact(String email, String firstname, String lastname, String jobtitle) {
		elementUtil.waitForElementPresent(CreateContactBtn);
		elementUtil.doClick(CreateContactBtn);
		
		elementUtil.waitForElementPresent(Email);
		elementUtil.doSendKeys(Email, email);
		elementUtil.doSendKeys(FirstName, firstname);
		elementUtil.doSendKeys(LastName, lastname);
		elementUtil.doSendKeys(JobTitle, jobtitle);
		jsUtil.clickElementByJS(elementUtil.getElement(CreateContactFormBtn));
	}
}
