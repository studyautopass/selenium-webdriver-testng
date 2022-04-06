package webdrivers;
import java.io.File;
import java.sql.Driver;
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



public class Topic_19_Upload_AutoIT_Robot {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String SeparatorChar = File.separator;
	
	String hinh1 = "hinh1.jpg";
	String hinh2 = "hinh2.png";
	String hinh3 = "hinh3.png";
	
	String hinh1Location = projectPath + SeparatorChar + "UploadFiles" + SeparatorChar + hinh1;
	String hinh2Location = projectPath + SeparatorChar + "UploadFiles" + SeparatorChar + hinh2;
	String hinh3Location = projectPath + SeparatorChar + "UploadFiles" + SeparatorChar + hinh3;
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	

	

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hinh1Location);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hinh2Location);
		sleepInSecond(1);
//		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hinh3Location);
//		sleepInSecond(1);
//		
		//Uploading
		
		List<WebElement> StartButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : StartButtons) {
			start.click();
			sleepInSecond(1);
		}
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='"+ hinh1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='hinh2.png']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='hinh3.png']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02_Multi_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hinh1Location + "\n" + hinh2Location);
		sleepInSecond(1);
		
//		
		//Uploading
		
		List<WebElement> StartButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : StartButtons) {
			start.click();
			sleepInSecond(1);
		}
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='"+ hinh1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='hinh2.png']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//span/a[@title='hinh3.png']")).isDisplayed());
		
		
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
