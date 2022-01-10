package webdrivers;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Custom_DropdownList {
	//mở trình duyệt
	WebDriver driver;
	WebDriverWait explicitlyWait;
	JavascriptExecutor jsExecutor;
		
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		explicitlyWait = new WebDriverWait(driver, 30);
		

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	//mở app
	
	
}
    @Test
	public void TC_01_JQuery() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		jsExecutor.executeScript("scroll(0,500);");
		sleepInSecond(2);
		
		//1. Chọn 1 item trong dropdownList
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "7");
		
		//So sánh xem giá trị hiển thị và giá trị mình vừa tạo
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "7") ;
			
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "10");				
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "10");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "15");				
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");				
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
		
		
	}

    
    //@Test
    public void TC_02_ReactJS() {
		// Mở trang
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(5);
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']/div/span", "Jenny Hess");
		//So sánh xem đúng giá trị mình đã chọn không
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
		
		
		
	}
    //@Test
    public void TC_03_VueJS() {
    	
    	driver.get("https://mikerodham.github.io/vue-dropdowns/");
    	selectItemInCustomDropdown("//div[@class='btn-group']", "//ul[@class='dropdown-menu']/li/a", " Second Option");
    	//driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText()
    }
    
    //@Test
    public void TC_04_AngularJS() {
    	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
    	
    
    
    }
    //@Test
    public void TC_05_Angualar() {
    	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
    	
    	selectItemInCustomDropdown("//ng-select[@bindvalue='provinceCode']//span[@class='ng-arrow-wrapper']", "//span[contains(@class,'ng-option-label')]", "Thành phố Hồ Chí Minh");
    	sleepInSecond(2);
    	String abc = driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(abc,"Thành phố Hồ Chí Minh");
    	
    	selectItemInCustomDropdown("//ng-select[@bindvalue='districtCode']//span[@class='ng-arrow-wrapper']", "//span[contains(@class,'ng-option-label')]", "Quận Tân Phú");
    	sleepInSecond(2);
    	String xyz = driver.findElement(By.xpath("//ng-select[@bindvalue='districtCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(xyz,"Quận Tân Phú");
    	
    	selectItemInCustomDropdown("//ng-select[@bindvalue='wardCode']//span[@class='ng-arrow-wrapper']", "//span[contains(@class,'ng-option-label')]", "Phường Phú Thọ Hòa");
    	sleepInSecond(2);
    	String mnk = driver.findElement(By.xpath("//ng-select[@bindvalue='wardCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(mnk,"Phường Phú Thọ Hòa");
    }
    //@Test
    public void TC_06_Edit_table() {

    	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
    	EditItemInCustomDropdown("//ng-select[@bindvalue='provinceCode']//input", "//span[contains(@class,'ng-option-label')]", "Thành phố Hồ Chí Minh");
    	String abc = driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(abc,"Thành phố Hồ Chí Minh");
    	
    	EditItemInCustomDropdown("//ng-select[@bindvalue='districtCode']//input", "//span[contains(@class,'ng-option-label')]", "Quận Tân Phú");
    	String xyz = driver.findElement(By.xpath("//ng-select[@bindvalue='districtCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(xyz,"Quận Tân Phú");
    	
    	
    	EditItemInCustomDropdown("//ng-select[@bindvalue='wardCode']//input", "//span[contains(@class,'ng-option-label')]", "Phường Phú Thọ Hòa");
    	String mnk = driver.findElement(By.xpath("//ng-select[@bindvalue='wardCode']//span[contains(@class,'ng-value-label')]")).getText();
    	Assert.assertEquals(mnk,"Phường Phú Thọ Hòa");
    }
    //@Test
    public void TC_07_JQuery_Edit_table() {
    	driver.get("http://indrimuska.github.io/jquery-editable-select/");
    	EditItemInCustomDropdown("//div[@id='default-place']/input", "//div[@id='default-place']/ul[@class='es-list']", "Audi");
    	sleepInSecond(2);
    	Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#default-place>input').value;"), "Audi");
    	
    	
    	
    }
    
    
    public void EditItemInCustomDropdown(String parentxPath, String childxPath, String expectedtemText){
		
		//Click vào element cho xổ hết ra
    	driver.findElement(By.xpath(parentxPath)).clear();
		driver.findElement(By.xpath(parentxPath)).sendKeys(expectedtemText);
			sleepInSecond(2);
			
			//Chờ cho tất cả item dc present trong vòTopic_10_Custom_DropdownList.javang 30s đến (explicitlyWait)=> load hết n item
			explicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childxPath)));
			
			//Lấy hết tất cả các item 
			List<WebElement> childItems = driver.findElements(By.xpath(childxPath));
						
			//duyệt qua từng item  
			for (WebElement tempElement : childItems) {
				
				//kiểm tra xem có đúng cái mình cần chọn hay không
				if (tempElement.getText().trim().equals(expectedtemText)) {
					tempElement.click();
					sleepInSecond(2);
					//thoát khỏi vòng lặp
					break;
				}
				
				
			}
    }
    
    
    public void selectItemInCustomDropdown(String parentxPath, String childxPath, String expectedtemText){
		
		//Click vào element cho xổ hết ra
		driver.findElement(By.xpath(parentxPath)).click();
			sleepInSecond(2);
			
			//Chờ cho tất cả item dc present trong vòTopic_10_Custom_DropdownList.javang 30s đến (explicitlyWait)=> load hết n item
			explicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childxPath)));
			
			//Lấy hết tất cả các item 
			List<WebElement> childItems = driver.findElements(By.xpath(childxPath));
						
			//duyệt qua từng item  
			for (WebElement tempElement : childItems) {
				
				//kiểm tra xem có đúng cái mình cần chọn hay không
				if (tempElement.getText().trim().equals(expectedtemText)) {
					tempElement.click();
					sleepInSecond(2);
					//thoát khỏi vòng lặp
					break;
				}
				
				
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
