package com.victor.model;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Driver {
	
	public FirefoxDriver getFirefox() {
		return new FirefoxDriver();
	}

	public ChromeDriver getChrome() {
		return new ChromeDriver();
	}

//	public OperaDriver getOpera() {
//		return opera;
//	}
//
//	public InternetExplorerDriver getExplorer() {
//		return explorer;
//	}

	
	public Driver() {
		System.setProperty("webdriver.gecko.driver", driversLocation().getProperty("webdriver.gecko.driver"));
		//System.setProperty("webdriver.chrome.driver", driversLocation().getProperty("webdriver.chrome.driver"));
		
	}
	
	private Properties driversLocation() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream firefoxDriver = classLoader.getResourceAsStream("application.properties");
		Properties prop = new Properties();
		try {
			prop.load(firefoxDriver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
}
