package webdrivers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Action_Part1 {
	//mở trình duyệt
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		action = new Actions(driver);

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(2);
		
		String abc = driver.findElement(By.className("ui-tooltip-content")).getText();
		System.out.println(abc);
		
		Assert.assertEquals(abc, "We ask for your age only for statistical purposes.");
		
		
		
		
		
	}

	
	public void TC_02_Hover() {
		driver.get("https://www.fahasa.com/");
		//driver.findElement(By.xpath("//a/img[@id='NC_IMAGE1']")).click();
		
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//a[text()='Kỹ Năng Sống']"))).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		
	}

	@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		System.out.println("Tổng element"+ rectangleNumbers.size());
		action.clickAndHold(rectangleNumbers.get(0)).moveToElement(rectangleNumbers.get(7)).release().perform();
		sleepInSecond(2);
		
		//action.clickAndHold(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']")))
		
	}
	
	
	
	public void TC_04() {
		
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
