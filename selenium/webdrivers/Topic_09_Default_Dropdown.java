package webdrivers;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;





public class Topic_09_Default_Dropdown {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	//mở app
	
	
}

	//@Test
	public void TC_01() {
		
		driver.get("https://www.rode.com/wheretobuy/");
		select = new Select(driver.findElement(By.id("where_country")));
		//chọn giá trị Việt Nam
		select.selectByVisibleText("Vietnam");
	
		//verify gia trị vừa chọn
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam") ;
		
		//Kiểm tra dropdown multi
		Assert.assertFalse(select.isMultiple());
		
		//Kiểm tra có bao nhiêu option
		
		Assert.assertEquals(select.getOptions().size(), 233);
		driver.findElement(By.id("search_loc_submit")).click();
		
		String abc = driver.findElement(By.xpath("//div[@class='result_count']/span")).getText();
		System.out.println(abc);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32") ;
		
		List<WebElement> distributorName = driver.findElements(By.cssSelector("div.store_name"));
		
	for (WebElement distributor : distributorName) {
		System.out.println(distributor.getText());
	}
		
	}
	
	

	@Test
	public void TC_02() {
		driver.get("https://demo.nopcommerce.com/register");
		
		//input
		String firstName = "autopass";
		String lastName = "studyhard";
		String Password = "123123";
		String cfPassword = "123123";
		String day = "1";
		String month = "January";
		String year = "1996";
		String Email = "autopass"+getRandomNumber()+"@gamil.com";
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		Select dateDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		dateDropdown.selectByVisibleText(day) ;
		
		Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		monthDropdown.selectByVisibleText(month) ;
		
		Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		yearDropdown.selectByVisibleText(year) ;
		
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys(Password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(cfPassword);
		
		driver.findElement(By.name("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']")).isDisplayed());
		
		
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		//output
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),Email);
		
		dateDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(dateDropdown.getFirstSelectedOption().getText(), day);
		
		monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(monthDropdown.getFirstSelectedOption().getText(), month);
		
		yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(), year);
		
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
