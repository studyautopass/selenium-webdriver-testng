package webdrivers;


import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.source.tree.AssertTree;

public class Topic_16_iFrame_Frame {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		  

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}


	public void TC_01_iFrame() {
		driver.get("https://kyna.vn");
		//swich qa frame facebool
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		
		String LikeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText();
		System.out.println(LikeNumber);
		
		
		//swwich về trang chính
		driver.switchTo().defaultContent();
		
		//switch về trang chat
			driver.switchTo().frame("cs_chat_iframe");
			driver.findElement(By.cssSelector("div.button_bar")).click();
			sleepInSecond(2);
			driver.findElement(By.cssSelector("input.input_name")).sendKeys("thuy123");
			driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
			driver.findElement(By.id("serviceSelect")).sendKeys("thuy123");
			new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
			driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("abc");
			
			driver.switchTo().defaultContent();
			driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
			driver.findElement(By.cssSelector("button.search-button")).click();
			sleepInSecond(2);
			String keyword = "Excel";
			
			List<WebElement> courseName = driver.findElements(By.xpath("//div[@class='content']//h4"));
			for (WebElement course : courseName) {
				System.out.println(course.getText().toLowerCase());
				assertTrue(course.getText().toLowerCase().contains(keyword.toLowerCase()));
			}
			
		
			
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("thuytest");
		driver.findElement(By.cssSelector("a[class='btn btn-primary login-btn']")).click();
		
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed()) ;
		
		
	}


	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
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
			
			public int getRandomNumber() {
				Random rd = new Random();
				return rd.nextInt(1000);
			}
		
}
