package webdrivers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Alert {
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
		expliciWait = new WebDriverWait(driver,30);
		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
	
}

	public void TC_01_Accept_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		WebElement abc =  driver.findElement(By.xpath("//p[@id='result']"));
		//System.out.println(abc);
		
		Assert.assertEquals(abc.getText(), "You clicked an alert successfully");
		
	}


	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		WebElement abc =  driver.findElement(By.xpath("//p[@id='result']"));
		//System.out.println(abc);
		
		Assert.assertEquals(abc.getText(), "You clicked: Cancel");
		
	}

	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String inputValue = "thuy123";
	
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(inputValue);	
		sleepInSecond(2);
		
		alert.accept();
		
		WebElement abc =  driver.findElement(By.xpath("//p[@id='result']"));
		
		Assert.assertEquals(abc.getText(), "You entered: "+ inputValue);
		
	}
	

	public void TC_04_Authen_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
				
		}
	
	@Test
	public void TC_05_Authen_Alert() {
	driver.get("http://the-internet.herokuapp.com");
	String username = "admin";
	String password = "admin";
	WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
	String url = basicAuthenLink.getAttribute("href");
	
	
	driver.get(getAthenURL(url, username, password));
	
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
		
		
		public String getAthenURL(String url, String username, String password) {
			String[] urlValue = url.split("//");
			url = urlValue[0] + username +":"+ password +"@" +urlValue[1];
			return url;
		}
		
		
			public int getRandomNumber() {
				Random rd = new Random();
				return rd.nextInt(1000);
			}
		
}
