package parallel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import java.sql.Driver;

import org.testng.annotations.Test;

public class Topic_03_ThirdClass{
	WebDriver driver;	
	By username = By.id("email");
	By password = By.id("pass");
	String projectPath = System.getProperty("user.dir");
	
	

	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		
	
				
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	
}
	
	@Test()
	public void TC_01_Add() {
		driver.get("https://www.facebook.com/");
		driver.findElement(username).sendKeys("thuy");
		driver.findElement(password).sendKeys("123");
	}
	
	@Test()
	public void TC_02_Add() {
		driver.get("https://www.facebook.com/");
		driver.findElement(username).sendKeys("thuy");
		driver.findElement(password).sendKeys("123");
	}
	@AfterClass
	public void afterClass() {
		//Tắt Trình Duyệt 
		
		driver.quit();
	}
	
}

