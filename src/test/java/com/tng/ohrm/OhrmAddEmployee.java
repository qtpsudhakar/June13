package com.tng.ohrm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wd.util.DriverFactory;

public class OhrmAddEmployee {
	WebDriver driver;
	@BeforeClass()
	public void openApplication() {
		driver = DriverFactory.getDriverFor("chrome");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://qtpsudhakar1-trials641.orangehrmlive.com/auth/login");

		System.out.println("Application opened");
	}

	@Test
	public void login() {
		driver.findElement(By.id("txtUsername")).clear(); //clears the existing texttext
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("qtpsudhakar");
		driver.findElement(By.id("btnLogin")).click();
	}
	
	@Test(dependsOnMethods="login")
	public void addEmployee() {
		driver.findElement(By.linkText("PIM")).click();
		driver.findElement(By.linkText("Add Employee")).click();

		driver.findElement(By.id("firstName")).sendKeys("selenium");
		driver.findElement(By.id("lastName")).sendKeys("hq");
		
		driver.findElement(By.xpath("//div[@id='location_inputfileddiv']//input[@class='select-dropdown']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Australian Regional HQ')]")).click();

	}
	
	@AfterClass
	public void closeApplication() {
		//driver.quit();
		System.out.println("Application closed");
	}
}
