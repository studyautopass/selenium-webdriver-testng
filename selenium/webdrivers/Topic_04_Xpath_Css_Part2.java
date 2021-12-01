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

public class Topic_04_Xpath_Css_Part2 {
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
	public void TC_01_Parent_node() {
		// Lấy Text Hợp tác giảng dạy
		driver.findElement(By.xpath("//div[@class='groupmenu']//a[text()='Hợp tác giảng dạy']"));
	}

	@Test
	public void TC_02_absolute() {
	driver.findElement(By.xpath("//div[@id='content']//h3[(text()='TRẢI NGHIỆM CÙNG ALADA')]"));
		
	}

	@Test
	public void TC_03_Contains() {
	driver.findElement(By.xpath("//div[@id='content']//h3[contains(text(),'CÙNG ALADA')]"));	
		
	}
	

	@Test
	public void TC_04_Startwith() {
		//Mỗi lần load trang giá trị thay đổi, chỉ fix phần đầu, phần sau không cố định, vd: www.abc.com/uzbnnn11
		
		
	}
	
	@Test
	public void TC_05_Text() {
		//nằm trên chính node đó, không chấp nhận child node
	driver.findElement(By.xpath("//input[@id='txtEmail']"));
		
	}
	
	@Test
	public void TC_06_Contains_text() {
	//nằm trong child vẫn lấy được
		driver.findElement(By.xpath("//span[contains(string(),'CSKH')]"));
		
	}
	
	@Test
	public void TC_07_String() {
		
	driver.findElement(By.xpath("//div[@id='content']//h3[contains(text(),'CÙNG ALADA')]"));
		
	}
	
	@Test
	public void TC_08_Contains_Point() {
		//Contrain. và Contains string() work như nhau
		//Contains. không có ngoại lệ, case nào cũng work đc
		driver.findElement(By.xpath("//span[contains(.,'CSKH')]"));
		
	}
	@Test
	public void TC_09_Concat() {
		
	}
	@Test
	public void TC_10() {

		
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
