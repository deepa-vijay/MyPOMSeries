package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.itextpdf.text.log.SysoCounter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//public WebDriver driver;
	public Properties prop;
	public static boolean highlightElement;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public WebDriver init_driver(String browserName) {
		highlightElement = prop.getProperty("highlight").equalsIgnoreCase("yes")? true : false;
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
		if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				//driver = new ChromeDriver(co);
				tldriver.set(new ChromeDriver(co));
			}
			else {
				//driver = new ChromeDriver();
				tldriver.set(new ChromeDriver());
			}
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				//driver = new FirefoxDriver(fo);
				tldriver.set(new FirefoxDriver(fo));
			}
			else {
				//driver = new FirefoxDriver();
				tldriver.set(new FirefoxDriver());
			}
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
		//	driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		}
		else {
			System.out.println("Please enter the correct browser name");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	public Properties init_properties() {
		prop = new Properties();
		String path = null;
		String env = null;
		
		try {
			
			env = System.getProperty("env");
			
			if(env.equals("qa")) {
				path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
			} else if (env.equals("stg")) {
				path = "./src/main/java/com/qa/hubspot/config/stg.config.properties";
			}
		}	
			catch(Exception e) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
			}
		
   
		try {
			FileInputStream file = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				System.out.println("File not loaded");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		return prop;
	}
	
	/**
	 * Take Screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "\\screenshots" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Screenshot capture failed...");
		}
		
		return path;
	}
	
}
