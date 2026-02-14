package com.ui.listeners;

import org.apache.commons.beanutils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utilities.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	private final static int MAX_NUMBER_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUMBER_ATTEMPTS"));
	private static int currentAttempts = 1;

	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempts<=MAX_NUMBER_ATTEMPTS)
		{
			currentAttempts++;
			return true;
		}

		return false;
	}

}
