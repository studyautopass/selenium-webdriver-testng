package webdrivers;
import java.util.List;
import java.util.Random;
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

public class Topic_20_StaticWait_Part4 {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
	driver.manage().window().maximize();
	
	
}

	@Test
	public void TC_01_Timeout_Less_Than_Display() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();	
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
		
	}

	@Test
	public void TC_02_Timeout_Equal_Display() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//div[@id='finish']/h4"));
		System.out.println("Text hien thi: "+ driver.findElement(By.xpath("//div[@id='finish']/h4")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
	}

	@Test
	public void TC_03_Timeout_Greater_More_Display() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		System.out.println("Text hien thi: "+ driver.findElement(By.xpath("//div[@id='finish']/h4")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
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
