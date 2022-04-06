package webdrivers;
import org.openqa.selenium.Dimension;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part1 {
	//mở trình duyệt
	WebDriver driver;
	
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		explicitWait = new WebDriverWait(driver, 20);
}

	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		//driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("thuy@gmail.com");
		
		
		//Wait cho element dc visible/display
		Dimension confirmEmailSize = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		System.out.println("chieu cao: "+ confirmEmailSize.getHeight());
		System.out.println("chieu rong: "+ confirmEmailSize.getWidth());
	}

	
	public void TC_02_Invisible_In_DOM() {
		driver.get("https://www.facebook.com/");
		
		//Element không hiển thị + Có trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSecond(2);
		//Wait cho element dc invisible/undisplay
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		Dimension confirmEmailSize = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).getSize();
		
		System.out.println("chieu cao: "+ confirmEmailSize.getHeight());
		System.out.println("chieu rong: "+ confirmEmailSize.getWidth());
	}

	public void TC_02_Invisible_Not_In_DOM() {
		driver.get("https://www.facebook.com/");
		//Element không hiển thị + Không có trong DOM
		
		//Wait cho element dc invisible/undisplay
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Fail tại step này
		Dimension confirmEmailSize = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).getSize();
		
		System.out.println("chieu cao: "+ confirmEmailSize.getHeight());
		System.out.println("chieu rong: "+ confirmEmailSize.getWidth());
	}

	
	public void TC_03_Presence_In_UI_Pass() {
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("thuy@gmail.com");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		
	}
	
	
	public void TC_03_Presence_Not_In_UI_Pass() {
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("thuy@gmail.com");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		
	}
	
	
	public void TC_03_Presence_Not_In_DOM_Fail() {
		driver.get("https://www.facebook.com/");
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("thuy@gmail.com");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		
	}
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSecond(2);
		//Element Confirm email có trong DOM (visible/prceding)
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")); 
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img"))).click();
		
		//wait cho confirm email staleness
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
		
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
