package automation.selenium.E2E_Selenium_Java_Framework;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import testComponents.BaseTest;

public class DataDrivenJson extends BaseTest {
  @Test(dataProvider="getData")
  public void validateError(HashMap<String,String> input) throws InterruptedException {
	  LandingPage landingPage=new LandingPage(driver);
	  landingPage.login(input.get("email"), input.get("password"));
	  String expectedErroMessage="Incorrect email or password.";
	  String actualErrorMessage=landingPage.validationMessage();
	  Assert.assertEquals(expectedErroMessage, actualErrorMessage, "ValidationMessage not matched");
  }
  

  
@DataProvider
public Object[][] getData() throws Throwable {
	
	List<HashMap<String, String>> data;
	try {
		data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\login.json");
	} catch (IOException e) {
		System.out.println("File not found " + e);
		throw e;
	}
	return new Object[][] {{data.get(0)},{data.get(1)}};
}
  
}
