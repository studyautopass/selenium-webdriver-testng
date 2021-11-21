package webdrivers;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_02_Selenium_Locator {
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
		
		//Mở app Facbook lên
	
	driver.get("https://www.facebook.com/r.php");
	
}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("password_step_input")).sendKeys("123456");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("registration_redesign")).isDisplayed();
		
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("reg_email__")).sendKeys("thuyac@gmail.com");;
		sleepInSecond(3);
		
	}
	@Test
	public void TC_04_LinkText() {
		// Click xong chuyển qua màn hình khác
		
		driver.findElement(By.linkText("Already have an account?")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("account?"));
		sleepInSecond(3);
				
	}

	@Test
	public void TC_06_TagName() {
		// Bao nhiêu thẻ input trong màn hình quên mật khẩu
		int inputSize = driver.findElements(By.tagName("input")).size();
		
		System.out.println("input size" + inputSize);
		sleepInSecond(3);
	}
	@Test
	public void TC_07_Css() {
		// css cú pháp thông thường: tagname[attribute= 'value']
		driver.findElement(By.cssSelector("input[id='identify_email']")).sendKeys("thuyac@gmail.com");
		sleepInSecond(3);
		
	}

	@Test
	public void TC_08_XPath() {
		//Xpath thông thường //tagname[@attribute= 'value']
		driver.findElement(By.xpath("//button[@name='did_submit']")).click();
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
			Thread.sleep(second * 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
