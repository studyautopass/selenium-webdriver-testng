package webdrivers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Radio_Checkbox {
	//mở trình duyệt
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	//mở app
	
	
}
	

	public void TC_01_CustomCheckbox_Radio_01() {
		driver.get("https://material.angular.io/components/radio/examples");
		//Case1: thẻ input click được nhưng không verify được
		By summer = By.xpath("//input[@value='Summer']");
		driver.findElement(summer).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(summer).isSelected());
		
		
	}

	
	public void TC_02_CustomCheckbox_Radio_02() {
		driver.get("https://material.angular.io/components/radio/examples");
		//Case2: thẻ input verify được nhưng không Click đc
		By summer = By.xpath("//input[@value='Winter']/preceding-sibling::span");
		driver.findElement(summer).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(summer).isSelected());
		
	}
	
	
	
	public void TC_03_CustomCheckbox_Radio_03() {
		
		driver.get("https://material.angular.io/components/radio/examples");
		By radioSummer = By.xpath("//input[@value='Summer']");
		//Case2: dùng css
		jsExcutor.executeScript("arguments[0].click();",driver.findElement(radioSummer));		
		
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(radioSummer).isSelected());
		
	}

	@Test
	public void TC_04_CustomCheckbox_1() {
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		WebElement checkChecked = driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input"));
		
		jsExcutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input")));		
		
		sleepInSecond(2);
		
		 Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input")).isSelected());
//		 
//		 checkToCheckbox(checkChecked);
//		 sleepInSecond(2);
//		 Assert.assertFalse(driver.findElement(checkChecked).isSelected());
//		 
//		 
//		 UncheckToCheckbox(checkChecked);
//		 sleepInSecond(2);
//		
	}
	
	
	
	public void TC_05_CustomCheckbox_RadioButton_3() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		
		
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	
	
	
	
	//khai báo 1 hàm cho checkbox
	public void checkToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if(!element.isSelected()) {
			jsExcutor.executeScript("arguments[0].click();", element);
		}
	}
	
	public void UncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			jsExcutor.executeScript("arguments[0].click();", element);
		}
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
