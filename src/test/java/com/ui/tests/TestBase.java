package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utilities.BrowserUtility;
import com.utilities.LambdaTestUtility;
import com.utilities.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the HomePage of the WebSite")
	public void setUp(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, ITestResult result) {
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if (isLambdaTest) {
			logger.info("Running the tests on LambdaTest cloud");
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);
		} else {
			logger.info("Load the HomePage of the WebSite");

			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);

		}
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "Close the browser after the test")
	public void tearDown() {
		logger.info("Closing the browser after the test");
		if (isLambdaTest) {
			logger.info("Ending the LambdaTest session");
			LambdaTestUtility.quitLambdaTestSession();
		} else {
			homePage.closeBrowser();
		}
	}
}
