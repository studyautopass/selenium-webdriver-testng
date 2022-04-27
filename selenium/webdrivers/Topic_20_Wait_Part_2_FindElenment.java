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

public class Topic_20_Wait_Part_2_FindElenment {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	

		 driver.get("https://www.facebook.com/");

	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	@Test
	public void TC_01_FindElement() {
		WebElement links = null;
		//Không có element được tìm thấy
		//links  = driver.findElement(By.id("selenium"));
		
		// có 1 element được tìm thấy
		links = driver.findElement(By.id("email"));
		
		//có nhiều element đc tìm thấy
		links = driver.findElement(By.xpath("//a"));
		System.out.println(links);
		
		
	}

	@Test
	public void TC_02_FinElements() {
		 List<WebElement> links = null;
		//Không có element được tìm thấy
			links  = driver.findElements(By.id("selenium"));
			System.out.println("Element size: "+ links.size());
			
			// có 1 element được tìm thấy
			links = driver.findElements(By.id("email"));
			System.out.println("Element size: "+ links.size());
			//có nhiều element đc tìm thấy
			links = driver.findElements(By.xpath("//a"));
			System.out.println("Element size: "+ links.size());
			
		
	}

	@Test
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
