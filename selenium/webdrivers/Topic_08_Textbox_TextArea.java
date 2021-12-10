package webdrivers;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String currentUrl,Username, Password;
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	//mở app
	
	
}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		currentUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("study@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Get thong tin username, pass 
		Username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		
		
		
		
	}

	@Test
	public void TC_02_Login() {
		driver.get(currentUrl);	
		
		driver.findElement(By.name("uid")).sendKeys(Username);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@style='color: green']")).getText(),"Manger Id : "+ Username);
		
	}

	@Test
	public void TC_03_Add_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
	}
	@Test
	public void TC_04() {
		
	}

	@AfterClass
	public void afterClass() {
		
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
