package webdrivers;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_6_Mixing {
	//mở trình duyệt
	WebDriver driver;
	WebDriverWait ExplicitWait;
	
	//Implicit se anh huong toi kết quả của Explicit, không có trường hợp ngược lại
	
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();		
		
		driver.manage().window().maximize();
	
	
}

	public void TC_01_Found() {		
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ExplicitWait = new WebDriverWait(driver,5);	
		
		System.out.println("Start implicitlyWait luc: "+ getDateTimeNow());		
		driver.findElement(By.id("email"));
		System.out.println("End implicitlyWait luc: "+ getDateTimeNow() );
		
		
		System.out.println("Start ExplicitWait luc: "+ getDateTimeNow());
		ExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("End ExplicitWait luc: "+ getDateTimeNow());
		
		
	}

	 //unhappy path case 
	public void TC_02_NotFound_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ExplicitWait = new WebDriverWait(driver,5);	
		
		System.out.println("Start implicitlyWait luc: "+ getDateTimeNow());		
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End implicitlyWait luc: "+ getDateTimeNow());
		
		
	}

	 
	public void TC_03_Not_Found_Mix_Equal() {
		
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ExplicitWait = new WebDriverWait(driver,5);	
		
		System.out.println("Start implicitlyWait luc: "+ getDateTimeNow());		
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End implicitlyWait luc: "+ getDateTimeNow() );
		
		
		System.out.println("Start ExplicitWait luc: "+ getDateTimeNow());
		try {
			ExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End ExplicitWait luc: "+ getDateTimeNow());
		
	}
	 
	public void TC_03_Not_Found_Mix_Implicit__Explicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ExplicitWait = new WebDriverWait(driver,7);	
		
		System.out.println("Start ExplicitWait luc: "+ getDateTimeNow());
		try {
			ExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End ExplicitWait luc: "+ getDateTimeNow());
		
	
	}
		
  
	public void TC_04_Only_Explicit_By() { //khong set implicit, lay =0
		driver.get("https://www.facebook.com/");
		ExplicitWait = new WebDriverWait(driver,4);	
		System.out.println("Start ExplicitWait luc: "+ getDateTimeNow());
		try {
			ExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End ExplicitWait luc: "+ getDateTimeNow());
		
	}
	@Test  
	public void TC_05_Only_Explicit_WebElement() { 
		driver.get("https://www.facebook.com/");
		ExplicitWait = new WebDriverWait(driver,4);	
		System.out.println("Start ExplicitWait luc: "+ getDateTimeNow());
		try {
			ExplicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("selenium"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End ExplicitWait luc: "+ getDateTimeNow());
	
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
			public String getDateTimeNow() {
				Date date = new Date();
				return date.toString();
				
			}
		
}
