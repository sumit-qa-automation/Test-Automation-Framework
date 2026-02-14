package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		; // Initialize instance variable driver
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());

		} else {
			logger.error("invalid browser details, Please select chrome or edge only");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				logger.info("Launching browser in headless mode");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				logger.info("Launching browser in headless mode");
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());

			}

		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				logger.info("Launching browser in headless mode");
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}
		}
	}

	public void goToWebsite(String url) {
		logger.info("Launch the WebSite " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximize the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning visible text " + element.getText());
		return element.getText();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		String path = System.getProperty("user.dir") + "//screenshot//" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);

		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
