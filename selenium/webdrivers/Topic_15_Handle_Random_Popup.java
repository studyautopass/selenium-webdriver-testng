package webdrivers;


import java.util.List;
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



//import com.sun.tools.classfile.Annotation.element_value;

public class Topic_15_Handle_Random_Popup {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		jsExcutor = (JavascriptExecutor)driver;
		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}


	public void TC_01_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		WebElement supportHomePopup = driver.findElement(By.cssSelector("img#support-home"));
		if(supportHomePopup.isDisplayed()) {
			
			jsExcutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("area#btn-r")));
			//driver.findElement(By.cssSelector("area#btn-r")).click();	
			sleepInSecond(2);
			
		}
			//chọn mục 64GB
			driver.findElement(By.xpath("//div[@id='container']//a[text()='PC 64X']")).click();
			
			WebElement Layerpopup = driver.findElement(By.cssSelector("img.layer-popup"));
			//kiểm tra có hiển thị không
			
			Assert.assertTrue(Layerpopup.isDisplayed());
			
			//click vào download
			jsExcutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("area#down-url")));
			sleepInSecond(2);


		
		
	}

	@Test
	public void TC_02_Popup_NOT_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(2);
		//Kiểm tra popup có hiển thị hay không
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Popup size: "+popupContent.size());
		
		
		if(popupContent.size()>0) {
			//Popup có hiển thị=> close đi
			System.out.println("close popup, chuyển step3");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
		}
		else {
			System.out.println("không làm gì cả, chuyển đến steps 3");
		}
		
		//Step3
		driver.findElement(By.xpath("//h4[text()='Khóa học thiết kế hệ thống M&E - Tòa nhà']")).click();
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
