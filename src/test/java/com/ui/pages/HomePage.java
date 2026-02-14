package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utilities.BrowserUtility;
import com.utilities.LoggerUtility;

import static com.utilities.PropertiesUtil.*;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign')]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		goToWebsite(readProperty(QA, "URL"));
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(readProperty(QA, "URL"));
	}

	public LoginPage goToLoginPage() {
		logger.info("Performing click action on sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginpage = new LoginPage(getDriver());
		return loginpage;
	}

	public void closeBrowser() {
		getDriver().quit();
		
	}
	

}
