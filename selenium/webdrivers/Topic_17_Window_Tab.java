package webdrivers;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
	//mở trình duyệt
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver,10);

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	
}


	public void TC_01_Only_Two_Windown_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Get ID của Tab mà user đang đứng
		
		String parentID = driver.getWindowHandle();
		System.out.println("ID tab hiện tại: "+parentID);
		//Click vào Google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		//Switch qa google Tab thành công
		switchToTabByID(parentID); //truyền expected parent là parent để ! parent chuyển sang google
			
				
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		String googleTab = driver.getWindowHandle();
		
		//switch qa tab parent
		switchToTabByID(googleTab);
		System.out.println("URL hiện tại: " + driver.getCurrentUrl()) ;
	}

	public void TC_02_Greater_Than_One_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		//Click vào Google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);		
		System.out.println(driver.getCurrentUrl());	
		//Switch qua tab google 
		switchToTabByTitle("Google");		
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		sleepInSecond(2);
		
		//Switch qua tab parent
		switchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		
		
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);		
		switchToTabByTitle("Facebook – log in or sign up");
		System.out.println(driver.getCurrentUrl());	
		
	}
	
	
	
	//Trường hợp có 2Tab
	public void switchToTabByID(String expectedID) {
		Set<String> allTabID = driver.getWindowHandles();
		
		for (String id : allTabID) {
			if(!id.equals(expectedID)) {
				driver.switchTo().window(id);
				
			}
		}
		
	}
	//Truong hợp 2 hoặc nhiều hơn 2 Tab
	public void switchToTabByTitle(String expectedTitle) {
		Set<String> allTabID = driver.getWindowHandles();

		for (String id : allTabID) { //id là biến tạm
			//Switch vào Tab trước rồi kiểm tra sau
			driver.switchTo().window(id);
			String actuaTitle = driver.getTitle();			
			
			//Kiểm tra title bằng vs title mong muốn
			if(actuaTitle.equals(expectedTitle)) {
				//thoát khỏi vòng lặp
				break;
			}
			}
		
		}
		
	
	
	public void TC_03_Window1() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Sony Xperia has been added to comparison list.");
	
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToTabByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		
		System.out.println("Title la: "+ driver.findElement(By.tagName("h1")).getText());
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "COMPARE PRODUCTS");
		//Click vao close Window
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		
		switchToTabByTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepInSecond(3);		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The comparison list was cleared.");
	
	}

	@Test
	public void TC_04_Windown2() {
		
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		//switch qa tab Login
		switchToTabByTitle("Login");		
		
		//Chua input click vao ntn Login
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']//following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']//following-sibling::span")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		switchToTabByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(2);
		//Verify Login thanh cong
		System.out.println("Ten la: " + driver.findElement(By.xpath("//span[@class='tb lpl-2 cdo-username']")).getText());
		//Assert.assertEquals(driver.findElement(By.xpath("//span[@class='tb lpl-2 cdo-username']")).getText(), "Automation FC");
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
