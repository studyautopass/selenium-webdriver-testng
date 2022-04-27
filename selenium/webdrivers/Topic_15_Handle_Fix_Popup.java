package webdrivers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Handle_Fix_Popup {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();			  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}


	public void TC_01_Fix_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		By modalLoginPopup = By.xpath("//div[@id='modal-login-v1']");
		Assert.assertFalse(driver.findElement(modalLoginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		Assert.assertTrue(driver.findElement(modalLoginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("thuyabc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("123456");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close"))	.click();
		sleepInSecond(2);
		//kiem tra khong hien thi
		Assert.assertFalse(driver.findElement(modalLoginPopup).isDisplayed());
		
	}

@Test
	public void TC_02_Fix_Popup() {
		driver.get("https://bizbooks.vn/");
		By loginPopup = By.cssSelector("div#md-signin");
		By registerPopup = By.cssSelector("div#md-signup");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		driver.findElement(By.xpath("//header[@class='header']//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='email']")).sendKeys("thuyabc@gmail.com");
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("button.js-btn-member-login")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#md-signin span.text-danger")).getText(), "Tài khoản không tồn tại");
		
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='password']")).sendKeys(Keys.ESCAPE);
		
//		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
//		Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());
//		
		
		
		
		
	}


	public void TC_03_Popup_Not_In_DOM() {
		
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
