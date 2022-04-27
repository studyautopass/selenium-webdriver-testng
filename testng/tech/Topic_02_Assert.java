package tech;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Assert {
	
  @Test
  public void TC_01() {
	 //Thu vien Assert:Kiểm tra tính đúng đắn của dữ liệu
	  String addressCity = "Ho Chi Minh";
	  Assert.assertTrue(addressCity.equals("Ho Chi Minh"));
	  Assert.assertTrue(addressCity.contains("Chi Minh"));
	  
	  //mong doi sai
	  Assert.assertFalse(addressCity.contains("1Chi Minh"));
	  //Kiểm tra điều kiện mong đợi là null
	  Object fullname = null;
	  Assert.assertNull(fullname);
	  	  
	  fullname ="thuykim96";
	  Assert.assertNotNull(fullname);
  }
  

}

