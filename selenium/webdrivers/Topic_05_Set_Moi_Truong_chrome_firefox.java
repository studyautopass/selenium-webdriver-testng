package webdrivers;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Set_Moi_Truong_chrome_firefox {
	//mở trình duyệt
	RemoteWebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	

	@Test
	public void TC_01_Run_On_Firfox() {
		if(osName.startsWith("Windows")){
		//windows
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
		//Mac
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		}
		driver = new FirefoxDriver();
				
		driver.get("https://www.facebook.com/");
		sleepInSecond(3);
		driver.quit();
		}

	@Test
	public void TC_02Run_On_Chrome() {
		if(osName.startsWith("Windows")){
			//windows
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			}else {
			//Mac
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver");
			}
			driver = new FirefoxDriver();
					
			driver.get("https://www.facebook.com/");
			sleepInSecond(3);
			driver.quit();
			}	
		
	

	@Test
	public void TC_03() {
		
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
