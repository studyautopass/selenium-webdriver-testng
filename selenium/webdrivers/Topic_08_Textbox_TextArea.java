package webdrivers;
import java.sql.Driver;
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

public class Topic_08_Textbox_TextArea {
	//mở trình duyệt
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String currentUrl,Username, Password;
	String Gender, Dob, AddressInput,AddressOutput, City, State, PIN, PhoneNumber, Mail,Password1, name;
	String AddressInputEdit, AddressOutputEdit, CityEdit, StateEdit, PinEdit, PhoneEdit, mailEdit, customerID;
	
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//ép kiểu tường minh
		jsExcutor = (JavascriptExecutor)driver;
		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
//Khai bao bien su dung tao New customer
	name = "autopass";
	Dob = "1996-09-09";
	AddressInput = "Binh Tan \nBac Binh";
	AddressOutput = "Binh Tan Bac Binh";
	City = "Phan Thiet"; 
	State = "Binh Thuan";
	PIN = "700000";
	PhoneNumber = "0928438292"; 
	Mail = "autopass" + getRandomNumber() + "@mail.vn";
	Password1 = "12345678";

//Giá trị khi edit
	AddressInputEdit = "Binh Thuan \n Bình Tân";
	AddressOutputEdit = "Binh Thuan Bình Tân";
	CityEdit = "Hà nội";
	StateEdit = "VietNam";
	PinEdit = "899988";
	PhoneEdit = "0987654321";
	mailEdit = "autopass" + getRandomNumber() + "@mail.vn";
	
}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		currentUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("study@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Get thong tin username, pass 
		Username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		
		
		
		
	}

	@Test
	public void TC_02_Login() {
		driver.get(currentUrl);	
		
		
		driver.findElement(By.name("uid")).sendKeys(Username);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@style='color: green']")).getText(),"Manger Id : "+ Username);
		
	}

	@Test
	public void TC_03_Add_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(By.name("name")).sendKeys(name);
		
		WebElement date = driver.findElement(By.name("dob"));
		jsExcutor.executeScript("arguments[0].removeAttribute('type')", date);
		sleepInSecond(3);
		date.sendKeys(Dob);
		
		driver.findElement(By.name("addr")).sendKeys(AddressInput); //textarea
		driver.findElement(By.name("city")).sendKeys(City);
		driver.findElement(By.name("state")).sendKeys(State);
		driver.findElement(By.name("pinno")).sendKeys(PIN);
		driver.findElement(By.name("telephoneno")).sendKeys(PhoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(Mail);
		driver.findElement(By.name("password")).sendKeys(Password1);
		driver.findElement(By.name("sub")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(),"Customer Registered Successfully!!!");
		//verify các giá trị đang hiển thị với các giá trị đã input  customerID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),Dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),AddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),PIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),PhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),Mail);
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//verify giá trị trên trang edit  
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"),Dob);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(),AddressInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"),City);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"),State);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"),PIN);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"),PhoneNumber);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"),Mail);
		
	}
	@Test
	public void TC_04_EditCustomer() {
		
		//edit
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(AddressInputEdit);
		
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(CityEdit);
		
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(StateEdit);
		
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(PinEdit);
		
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(PhoneEdit);
		
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(mailEdit);
		
		driver.findElement(By.name("sub")).click();
		
		//verify lại
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getAttribute("value"),Dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),AddressOutputEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getAttribute("value"),CityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getAttribute("value"),StateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getAttribute("value"),PinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getAttribute("value"),PhoneEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getAttribute("value"),mailEdit);
		
		sleepInSecond(3);
	}

	@AfterClass
	public void afterClass() {
		
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
