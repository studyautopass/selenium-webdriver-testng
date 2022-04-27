package listener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register {
	//mở trình duyệt
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String currentUrl,Username, Password, Email;
	

@BeforeClass
public void beforeClass() {
	
	System.out.print(projectPath);
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	//ép kiểu tường minh
	jsExcutor = (JavascriptExecutor)driver;
	  

driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.manage().window().maximize();

//Khai bao bien su dung tao New customer

Email = "autopass" + getRandomNumber() + "@mail.vn";

}

@Test
public void TC_01_Register() {
	driver.get("http://demo.guru99.com/v4/");
	currentUrl = driver.getCurrentUrl();
	driver.findElement(By.xpath("//a[text()='here']")).click();
	driver.findElement(By.name("emailid")).sendKeys(Email);
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	
	//Get thong tin username, pass 
	Username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	Assert.assertFalse(driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).isDisplayed());
	
	
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