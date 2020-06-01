package com.qa.hubspot.page;

import org.openqa.selenium.By;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {

	By accountName = By.xpath("//span[@class='account-name ']");
	By headerTitle = By.xpath("//h1[@class='private-header__heading private-header__heading--solo']");
}
