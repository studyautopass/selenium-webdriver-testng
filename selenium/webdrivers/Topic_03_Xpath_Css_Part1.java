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
//thuytestlan 2


public class Topic_03_Xpath_Css_Part1 {
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
	
	//mở app
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	
}

	@Test
	public void TC_01_Register_Empty() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
		
		
	}
	

	@Test
	public void TC_02_Register_Invalid_Email() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("thuy11");
		driver.findElement(By.id("txtEmail")).sendKeys("thuy@dbb@ss");
		driver.findElement(By.id("txtCEmail")).sendKeys("thuy@dbb@ss");
		driver.findElement(By.id("txtPassword")).sendKeys("1111111");
		driver.findElement(By.id("txtCPassword")).sendKeys("1111111");
		driver.findElement(By.id("txtPhone")).sendKeys("0911111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);
		
		//Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");		
		//Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		//Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		//Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("thuy11");
		driver.findElement(By.id("txtEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtCEmail")).sendKeys("thuy@dbb@ss");
		driver.findElement(By.id("txtPassword")).sendKeys("1111111");
		driver.findElement(By.id("txtCPassword")).sendKeys("1111111");
		driver.findElement(By.id("txtPhone")).sendKeys("0911111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");		
			
	}
	
	@Test
	public void TC_04_Register_Invalid_Password() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("thuy11");
		driver.findElement(By.id("txtEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtCEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtPassword")).sendKeys("111");
		driver.findElement(By.id("txtCPassword")).sendKeys("111");
		driver.findElement(By.id("txtPhone")).sendKeys("0911111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);		
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
					
	}
	
	@Test
	public void TC_05_Register_Incorrect_Confirm_Password() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("thuy11");
		driver.findElement(By.id("txtEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtCEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtPassword")).sendKeys("11111111");
		driver.findElement(By.id("txtCPassword")).sendKeys("1112222");
		driver.findElement(By.id("txtPhone")).sendKeys("0911111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);
				
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
						
	}
	
	
	@Test
	public void TC_06_Register_Invalid_Phonenumber() {
		//mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("thuy11");
		driver.findElement(By.id("txtEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtCEmail")).sendKeys("thuy@dbb.ss");
		driver.findElement(By.id("txtPassword")).sendKeys("11111111");
		driver.findElement(By.id("txtCPassword")).sendKeys("1112222");
		driver.findElement(By.id("txtPhone")).sendKeys("091111111111111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']// button[text()='ĐĂNG KÝ']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
				
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
