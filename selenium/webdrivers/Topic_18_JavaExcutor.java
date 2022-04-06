package webdrivers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

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

public class Topic_18_JavaExcutor {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		jsExecutor = (JavascriptExecutor) driver;
		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	@Test
	public void TC_01() {
		
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(2);
		String liveGruDomain  = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGruDomain, "live.techpanda.org");
		
		sleepInSecond(2);
		String liveGruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGruUrl, "http://live.techpanda.org/");
		
		sleepInSecond(2);
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		sleepInSecond(2);
		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		
		sleepInSecond(2);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		sleepInSecond(2);
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		sleepInSecond(2);
		String titleCustom = (String) executeForBrowser("return document.title;");
		sleepInSecond(2);
		Assert.assertEquals(titleCustom, "Customer Service");
		
		sleepInSecond(2);
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "thuy" + getRandomNumber() + "@gmail.com");
		
		sleepInSecond(2);
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		sleepInSecond(2);
		String subcribeMessage = getInnerText();
		Assert.assertTrue(subcribeMessage.contains("Thank you for your subscription."));
		
		
		
	}

	public void TC_02() {
		
	}


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
			
		public int getRandomNumber() {
				Random rd = new Random();
				return rd.nextInt(1000);
		}
		
		public Object executeForBrowser(String javaScript) {
			return jsExecutor.executeScript(javaScript);
		}

		public String getInnerText() {
			return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		}

		public boolean areExpectedTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		public void hightlightElement(String locator) {
			WebElement element = getElement(locator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
		}

		public void clickToElementByJS(String locator) {
			jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		}

		public void scrollToElementOnTop(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
		}

		public void scrollToElementOnDown(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
		}

		public void sendkeyToElementByJS(String locator, String value) {
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
		}

		public String getElementValidationMessage(String locator) {
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
		}

		public boolean isImageLoaded(String locator) {
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
			if (status) {
				return true;
			}
			return false;
		}

		public WebElement getElement(String locator) {
			return driver.findElement(By.xpath(locator));
		}
		
}
