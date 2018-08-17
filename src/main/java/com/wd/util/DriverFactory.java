package com.wd.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	public static WebDriver getDriverFor(String brName) {

		switch (brName.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			// co.addArguments("--disable-notifications");
			return new ChromeDriver(co);
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\geckodriver.exe");
			FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions fo = new FirefoxOptions();
			fo.setProfile(fp);
			return new FirefoxDriver(fo);
		case "ie":
			System.setProperty("webdriver.ie.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\IEDriverServer.exe");

			return new InternetExplorerDriver();
		case "edge":
			System.setProperty("webdriver.edge.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\MicrosoftWebDriver.exe");
			EdgeOptions eo = new EdgeOptions();

			return new EdgeDriver();

		default:
			System.out.println("no browser found");
			return null;
		}
	}

	public static WebDriver getRemoteDriverFor(String brName,String os) {

		DesiredCapabilities capabilities = new DesiredCapabilities();		

		switch (brName.toLowerCase()) {
		case "chrome":
			capabilities.setBrowserName(BrowserType.CHROME);
			break;
		case "firefox":
			capabilities.setBrowserName(BrowserType.FIREFOX);
			break;
		case "ie":
			capabilities.setBrowserName(BrowserType.IE);
			break;
		case "edge":
			capabilities.setBrowserName(BrowserType.EDGE);
			break;
		default:
			capabilities.setBrowserName(BrowserType.CHROME);
			break;
		}

		switch (os.toLowerCase()) {
		case "win10":
			capabilities.setPlatform(Platform.WIN10);
			break;
		case "win":
			capabilities.setPlatform(Platform.WINDOWS);
			break;
		case "mac":
			capabilities.setPlatform(Platform.MAC);
			break;
		default:
			capabilities.setPlatform(Platform.WINDOWS);
			break;
		}
		
		
		try {
			return new RemoteWebDriver(new URL("http://192.168.211.1:4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
