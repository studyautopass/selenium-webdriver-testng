package webdrivers;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrower_Command_Part2 {
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

		//driver.get("http://live.techpanda.org/");
		
	
}
	@Test
	public void TC_01_Verify_Url() {
		//mở page
		driver.get("http://live.techpanda.org/");
		//click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//so sánh current url
		String Currenturl = driver.getCurrentUrl();
		Assert.assertEquals(Currenturl, "http://live.techpanda.org/index.php/customer/account/login/");
		//click vào  Create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//get current url so sánh link
		String Currenturl1 = driver.getCurrentUrl();
		Assert.assertEquals(Currenturl1, "http://live.techpanda.org/index.php/customer/account/create/");
		sleepInSecond(3);
		//đóng trình duyệt
		//driver.close();

	}

	@Test
	public void TC_02_Verify_Title() {
		//mở page
		driver.get("http://live.techpanda.org/");
		//click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//get title và so sánh
		String title = driver.getTitle();
		Assert.assertEquals(title, "Customer Login");
		//click vào  Create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//get title và so sánh
		String title1 = driver.getTitle();
		Assert.assertEquals(title1, "Create New Customer Account");
	
	}


	@Test
	public void TC_03_Navigate_Function() {
		//mở page
		driver.get("http://live.techpanda.org/");
		//click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//click vào  Create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Back lại page trc
		driver.navigate().back();
		//get url và so sánh url
		String currenturl = driver.getCurrentUrl();
		Assert.assertEquals(currenturl, "http://live.techpanda.org/index.php/customer/account/login/");
		//forward tới page login
		driver.navigate().forward();
		
		
		String title1 = driver.getTitle();
		Assert.assertEquals(title1, "Create New Customer Account");
		


	}
	
	@Test
	public void TC_04_Get_PageSource_Code() {
		//mở page
		driver.get("http://live.techpanda.org/");
		//click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginpagesource = driver.getPageSource();
		
		Assert.assertTrue(loginpagesource.contains("Login or Create an Account"));
		
		//click vào  Create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//get page source
		String registerpagesource = driver.getPageSource();
		Assert.assertTrue(registerpagesource.contains("Create an Account"));
		
		
		driver.close(); 

	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
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
