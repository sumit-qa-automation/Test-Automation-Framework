package com.ui.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.utilities.CSVReaderUtil;
import com.utilities.ExcelReaderUtil;
import com.utilities.User;

public class LoginDataProvider {
	@DataProvider(name = "LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtil.readCSVFile("loginData.csv");
	}

	@DataProvider(name = "LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtil.readExcelFile("LoginTestData.xlsx");
	}
}
