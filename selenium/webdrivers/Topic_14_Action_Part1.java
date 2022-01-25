package webdrivers;


import org.openqa.selenium.support.Color;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.SourceLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Action_Part1 {
	//mở trình duyệt
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelperPath = projectPath + "\\DragDropFolder\\drag_and_drop_helper.js";
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(2);
		
		String abc = driver.findElement(By.className("ui-tooltip-content")).getText();
		System.out.println(abc);
		
		Assert.assertEquals(abc, "We ask for your age only for statistical purposes.");
		
		
		
		
		
	}

	
	public void TC_02_Hover() {
		driver.get("https://www.fahasa.com/");
		//driver.findElement(By.xpath("//a/img[@id='NC_IMAGE1']")).click();
		
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//a[text()='Kỹ Năng Sống']"))).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		
	}

	
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		System.out.println("Tổng element"+ rectangleNumbers.size());
		action.clickAndHold(rectangleNumbers.get(0)).moveToElement(rectangleNumbers.get(7)).release().perform();
		sleepInSecond(2);
		
		//action.clickAndHold(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']")))
		
	}
	
	
	
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	
	}
	
	
	public void TC_05_Righ_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());			
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		//System.out.println(driver.switchTo().alert().getText());
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: paste");
		
		driver.switchTo().alert().accept();
	}
	
	
	
	public void TC_06_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallcircle = driver.findElement(By.id("draggable"));
		WebElement bigcircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallcircle, bigcircle).perform();
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		
		String backgroudColor = bigcircle.getCssValue("background-color");
		
		String hexaColor = Color.fromString(backgroudColor).asHex().toLowerCase();
		Assert.assertEquals(hexaColor, "#03a9f4");
		
	}
	
	
	public void TC_07_Drag_Drop_HTML5()throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String columnACss = "#column-a";
		String columnBCss = "#column-b";
		
		String dragDropHeplperContent = getContentFile(dragDropHelperPath);
		
		dragDropHeplperContent = dragDropHeplperContent + "$(\"" + columnACss + "\").simulateDragDrop({ dropTarget: \"" + columnBCss + "\"});";
		
		//drop a->b
		jsExecutor.executeScript(dragDropHeplperContent);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
		
		
		
	}
	
	@Test
	public void TC_08_Drag_Drop_HTML5_Xpath()throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String columnAXpath = "//div[@id='column-a']";
		String columnBXpath = "//div[@id='column-b']";
		//Keo A>B
		dragAndDropHTML5ByXpath(columnAXpath, columnBXpath);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
		
		
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
			
			public String getContentFile(String filePath) throws IOException {
				Charset cs = Charset.forName("UTF-8");
				FileInputStream stream = new FileInputStream(filePath);
				try {
					Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
					StringBuilder builder = new StringBuilder();
					char[] buffer = new char[8192];
					int read;
					while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
						builder.append(buffer, 0, read);
					}
					return builder.toString();
				} finally {
					stream.close();
				}
			}
			
			
			public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

				WebElement source = driver.findElement(By.xpath(sourceLocator));
				WebElement target = driver.findElement(By.xpath(targetLocator));

				// Setup robot
				Robot robot = new Robot();
				robot.setAutoDelay(500);

				// Get size of elements
				Dimension sourceSize = source.getSize();
				Dimension targetSize = target.getSize();

				// Get center distance
				int xCentreSource = sourceSize.width / 2;
				int yCentreSource = sourceSize.height / 2;
				int xCentreTarget = targetSize.width / 2;
				int yCentreTarget = targetSize.height / 2;

				Point sourceLocation = source.getLocation();
				Point targetLocation = target.getLocation();
				System.out.println(sourceLocation.toString());
				System.out.println(targetLocation.toString());

				// Make Mouse coordinate center of element
				sourceLocation.x += 20 + xCentreSource;
				sourceLocation.y += 110 + yCentreSource;
				targetLocation.x += 20 + xCentreTarget;
				targetLocation.y += 110 + yCentreTarget;

				System.out.println(sourceLocation.toString());
				System.out.println(targetLocation.toString());

				// Move mouse to drag from location
				robot.mouseMove(sourceLocation.x, sourceLocation.y);

				// Click and drag
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

				// Move to final position
				robot.mouseMove(targetLocation.x, targetLocation.y);

				// Drop
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		
}
