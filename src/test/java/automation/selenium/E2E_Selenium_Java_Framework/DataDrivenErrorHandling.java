package automation.selenium.E2E_Selenium_Java_Framework;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class DataDrivenErrorHandling extends BaseTest {
  @Test(dataProvider="getData")
  public void validateError(HashMap<String,String> input) throws InterruptedException {
	  LandingPage landingPage=new LandingPage(driver);
	  landingPage.login(input.get("email"), input.get("password"));
	  String expectedErroMessage="Incorrect email or password.";
	  String actualErrorMessage=landingPage.validationMessage();
	  Assert.assertEquals(expectedErroMessage, actualErrorMessage, "ValidationMessage not matched");
  }
  
//@DataProvider
//public Object[][] getData() {
//	return new Object[][] {{"ishaq883@gmail.com","test123"},{"test12@gmail.com","test123"}};
//}
  
@DataProvider
public Object[][] getData() {
	HashMap<String,String> map1=new HashMap<String,String>();
	map1.put("email", "ishaq823@gmail.com");
	map1.put("password", "test123");
	
	HashMap<String,String> map2=new HashMap<String,String>();
	map2.put("email", "ishaq883@gmail.com");
	map2.put("password", "test123");
	return new Object[][] {{map1},{map2}};
}
  
}
