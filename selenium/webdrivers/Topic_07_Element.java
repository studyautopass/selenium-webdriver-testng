package webdrivers;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Element {
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

	@Test
	public void TC_01() {
		//1. mở app
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Verify txt Email is dispay
		WebElement txtEmail = driver.findElement(By.id("mail"));
		boolean status = txtEmail.isDisplayed();
		System.out.println(status);
		
		if (status) {
			System.out.println("Textbox Email is dislayed");
			txtEmail.sendKeys("Automation Testing");
			
		} else {
			System.out.println("Textbox email is not dislayed");
		}
		
		//Verify radio button Under 18
		WebElement radioUnder18 = driver.findElement(By.id("under_18"));
		radioUnder18.click();
		boolean status1 = radioUnder18.isSelected();
		System.out.println(status1);		
		if (status1) {
			System.out.println("Radio button under18 is selected");
						
		} else {
			System.out.println("Radio button under18 is not selected");
		}
		
		//Verify Education textbox is dislay
		WebElement txtEdu = driver.findElement(By.id("edu"));
		boolean status2 = txtEdu.isDisplayed();
		if (status2) {
			System.out.println("textbox education is dislay");
			txtEdu.sendKeys("Automation Testing");
		} else {
			System.out.println("textbox education is not dislay");
		}
		
		//Verify user5
		
		WebElement user5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		boolean status3 = user5.isDisplayed();
		if (status3) {
			System.out.println("name User 5 is dislay");
		} else {
			System.out.println("name User 5 is not dislay");
		}
	}

	@Test
	public void TC_02() {
		
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
}
