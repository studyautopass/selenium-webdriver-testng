package tech;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Topic_05_Depend_Tech {
	
	@Test()
	public void TC_01_Create() {
	
		
	}
	@Test(dependsOnMethods ="TC_01_Create" )
	public void TC_02_View() {
		Assert.assertTrue(false);
		
	}
	@Test(dependsOnMethods ="TC_01_Create")
	public void TC_03_Edit() {
		
	}
	@Test(dependsOnMethods ={"TC_01_Create","TC_02_View"})
	public void TC_01_OK() {
		
	}
}