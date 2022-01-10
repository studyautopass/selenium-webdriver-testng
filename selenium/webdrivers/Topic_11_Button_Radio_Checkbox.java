package webdrivers;
//import java.awt.Color;
import org.openqa.selenium.support.Color;

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



public class Topic_11_Button_Radio_Checkbox {
	//mở trình duyệt
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		jsExcutor = (JavascriptExecutor) driver;
	  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}

	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		By btnLogin = By.cssSelector("button.fhs-btn-login");
		By loginTab = By.cssSelector("li.popup-login-tab-login");
		
		//Verify 
		driver.findElement(loginTab).click();
		Assert.assertFalse(driver.findElement(btnLogin).isEnabled());
		sleepInSecond(2);
		driver.findElement(By.id("login_username")).sendKeys("thuy@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123123");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(btnLogin).isEnabled());
		
		//refresh
		driver.navigate().refresh();
		driver.findElement(loginTab).click();
		//remove thuộc tính disable của btn Login
		
		jsExcutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(btnLogin));
		sleepInSecond(2);
		//Verify sau khi remove disable attribute
	
		Assert.assertTrue(driver.findElement(btnLogin).isEnabled());
		
		//Verify background color 
		String Logincolor = driver.findElement(btnLogin).getCssValue("background-color");
		
		//RGB
		Assert.assertEquals(Logincolor,"rgb(201, 33, 39)");
		
		//HEXA
		String LoginbuttonHexaColor= Color.fromString(Logincolor).asHex().toUpperCase();
	 
		Assert.assertEquals(LoginbuttonHexaColor, "#C92127");
	 
		
		driver.findElement(btnLogin).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']"
				+ "/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//click chọn '1.4 Petrol, 92kW'
		//By RadioButton = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		By CheckboxLuggage= By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input") ;
		By CheckboxDual= By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input") ;
		
		jsExcutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("demo-runner")));
		
		driver.findElement(CheckboxDual).click();
		sleepInSecond(2);
		//Kiểm tra được chọn hay chưa
		Assert.assertTrue(driver.findElement(CheckboxDual).isSelected());
		
		driver.findElement(CheckboxDual).click();
		sleepInSecond(2);
		//Kiểm tra được chọn hay chưa
		Assert.assertFalse(driver.findElement(CheckboxDual).isSelected());
		
		driver.findElement(CheckboxLuggage).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(CheckboxLuggage).isSelected());
		
		
		
	}


	public void TC_03_Checkbox_all() {

		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		//Click all checkbox
		
		for (WebElement checkbox : checkboxes) {
			if(!checkbox.isSelected())
			{
			checkbox.click();
			sleepInSecond(1);
			}
		
		}
		
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		
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
