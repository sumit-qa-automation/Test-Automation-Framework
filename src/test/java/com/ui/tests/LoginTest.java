package com.ui.tests;

import static com.constants.Browser.*;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.utilities.LoggerUtility;
import com.utilities.User;

@Listeners(com.ui.listeners.TestListener.class)

public class LoginTest extends TestBase {

	@BeforeMethod(description = "Load the HomePage of the WebSite")
	public void setUp() {
		logger.info("Load the HomePage of the WebSite");

		homePage = new HomePage(CHROME, true);

	}

	@Test(description = "Verify user is able to login with valid user using csv data", groups = {
			"sanity,regression" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Sumit gh");

	}

	@Test(description = "Verify user is able to login with valid user using excel data", groups = {
			"sanity,regression" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider")
	public void loginExcelTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Sumit gh");

	}

}
