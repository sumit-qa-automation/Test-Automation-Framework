package com.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
	
	public static WebDriver initializeLambdaTestSession(String browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("browserVersion", "latest");
		Map<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("user", "sumitght25");
		ltOptions.put("accessKey", "LT_4dUPUjdXy0vfVIkB9LQRPazxBTRMBvyAVXHNRS3Qua4PaE3");
		ltOptions.put("build", "LambdaTestApp");
		ltOptions.put("name", testName);
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("seCdp", true);
		capabilities.setCapability("LT:Options", ltOptions);
		capabilitiesLocal.set(capabilities);
		WebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driverLocal.set(driver);
		return driverLocal.get();
	}
	public static void quitLambdaTestSession() {
		if (driverLocal.get() != null) {
			driverLocal.get().quit();
			driverLocal.remove();
			capabilitiesLocal.remove();
		}
	}
	
	

}
