package webdrivers;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.sun.tools.classfile.Annotation.element_value;

public class Topic_20_Wait_Part_7_Fluent {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();		
		
		driver.manage().window().maximize();
	
	
}
	
	public void TC_01_() {		
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = driver.findElement(By.id("javascript_countdown_time"));
		fluentWaitElement = new FluentWait<WebElement>(countdownTime);
		//Tim voi tong thoi gian 15s
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))
		//cơ chế tìm lại nếu chưa thỏa mãn điều kiện 0.5s/ lần
		.pollingEvery(Duration.ofMillis(500))
		//nếu trong tgian tìm lại k thấy element
		.ignoring(NoSuchElementException.class)
		//xử lí điều kiện
		.until(new Function<WebElement, Boolean>() {

			@Override  //điều kiện
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println("Time: "+ text);
				return text.endsWith("00");
			}
		});
		
		
	}


	public void TC_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
		
		.pollingEvery(Duration.ofSeconds(1))
		
		.ignoring(NoSuchElementException.class); 
		
		WebElement textHello = fluentWaitDriver.until(new Function<WebDriver, WebElement>(){
			@Override  //điều kiện
			public WebElement apply(WebDriver driver) {
				
				return driver.findElement(By.xpath("//h4[text()='Hello World!']"));
				
		}
		});
		Assert.assertEquals(textHello.getText(), "Hello World!");
	}
		
		 
	@Test
	public void TC_03_() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='3 month(s)']")).isDisplayed());
		
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
			// Tìm element (custom)
		public WebElement getWebElement(By locator) {
				
				FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout(Duration.ofSeconds(30))
						.pollingEvery(Duration.ofSeconds(1))
						.ignoring(NoSuchElementException.class);
				WebElement element = wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						return driver.findElement(locator);
					}
			});
				return element;				
			}
			
			
			
			//Tìm element xong click vào
		public void waitForElementAndClick(By locator) {
			
				WebElement element = getWebElement(locator);
				 element.click();;

			}
		public boolean waitForElementAndDisplay(By locator) {
				WebElement element = getWebElement(locator);
				return element.isDisplayed();

			}
		
		public boolean isJQueryLoadedSuccess(WebDriver driver) {
			explicitWait = new WebDriverWait(driver, 30);
			jsExcutor = (JavascriptExecutor) driver;
			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (Boolean) jsExcutor.
							executeScript("//span[text()='3 month(s)']");
				}
			};
			return explicitWait.until(jQueryLoad);
			
		}


}
