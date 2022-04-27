package parallel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Loop_Register{
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String currentUrl,Username, Password,Email;
	
	
	@BeforeClass
	public void beforeClass() {
	
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//ép kiểu tường minh
		jsExcutor = (JavascriptExecutor)driver;
		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	Email = "autopass" + getRandomNumber() + "@mail.vn";

	
}
	
	@Test(invocationCount = 3)
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		currentUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Get thong tin username, pass 
		Username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		System.out.println(Username);
		System.out.println(Password);
		
		
		//sau khi dki thi luu vao 1 file
		//in ra Console
		
	}
	
	@Test()
	public void TC_02() {
		
	}
	
	@AfterClass
	public void afterClass() {
		//Tắt Trình Duyệt 
		
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(1000);
	}
	
}

