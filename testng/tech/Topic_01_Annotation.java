package tech;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotation {
	
  @Test
  public void Register() {
	  System.out.println("Thuy Register");
  }
  


  @BeforeTest
  public void BeforeTest() {
	  System.out.println("BeforeTest");
  }
  @AfterTest
  public void AfterTest() {
	  System.out.println("AfterTest");
  }
  @BeforeMethod
  public void BeforeMethod() {
	  System.out.println("BeforeMethod");
  }
   @AfterMethod
   public void AfterMethod() {
		  System.out.println("AfterMethod");
	  }
   
  @BeforeClass

  public void beforeClass() {
	  System.out.println("BeforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AfterClass");
  }



  @BeforeGroups
  public void BeforeGroups() {
  }

  @AfterGroups
  public void AfterGroups() {
  }
  @BeforeSuite
  public void BeforeSuite() {
	  System.out.println("BeforeSuite");
  }

  @AfterSuite
  public void AfterSuite() {
	  System.out.println("Hello");
  }

}

