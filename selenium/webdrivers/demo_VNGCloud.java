package webdrivers;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//remove

public class demo_VNGCloud {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		//Mở Trình Duyệt
		//System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Mở page login của Portal
	
	driver.get("https://register.vngcloud.vn/cas/login");
	
}

	@Test
	public void TC_01_Login() {
		driver.findElement(By.id("username")).sendKeys("iaas.qc2@vng.com.vn");
		driver.findElement(By.id("password")).sendKeys("12345678@Aa");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		sleepInSecond(3);
	}
	
	
	@Test
	public void TC_04_Server() {
		//Chọn click vào Server
		
		driver.findElement(By.xpath("//img[@src=\"https://vngcloud.vn/documents/20126/1519979/banner+300x150_vServer.jpg\"]")).click();
		sleepInSecond(3);
		
		
		
	}
	
	
	
	@Test	
	public void TC_05_WellcomeServer() {
		sleepInSecond(5);
		//driver.findElement(By.cssSelector(".card-button a[href='/servers/list.html']")).click();
		driver.findElement(By.cssSelector("//button[@class='btn nobg in-row btn-new']//i[@class='vdicon icon-new-instance']")).click();
//		
		sleepInSecond(5);
		
		//driver.findElement(By.xpath("button a:contains('Create instance')")).click();
		//a[@href="/servers/list.html"]
		//driver.findElement(By.xpath("//div[@id='cssmenu']/ul/li[4]")).click();
		//driver.findElement(By.xpath("//button[@ng-disabled='disabledInstance']")).click();
		
	
		//sleepInSecond(3);
		
	}
	
	//@Test
	public void TC_06_CreateServer() {
		//Chọn click Tạo Server
		
		driver.findElement(By.xpath("")).click();
		sleepInSecond(3);
	}
	
	

	@AfterClass
	public void afterClass() {
		//Tắt Trình Duyệt 
		
		driver.quit();
	}
	
	//Gọi hàm khác để dùng
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
