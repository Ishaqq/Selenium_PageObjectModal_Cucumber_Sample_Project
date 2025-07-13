package automation.selenium.E2E_Selenium_Java_Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import testComponents.Retry;
public class ErrorValidation extends BaseTest {
//@Test(priority=1, groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
@Test(priority=1, groups={"ErrorHandling"})
  public void validateError() throws InterruptedException {
	  LandingPage landingPage=new LandingPage(driver);
	  landingPage.login("ishaq8283@ga.com", "test12323");
	  String expectedErroMessage="Incorrect email or password.";
	  String actualErrorMessage=landingPage.validationMessage();
	  Assert.assertEquals(expectedErroMessage, actualErrorMessage, "ValidationMessage not matched");
  }
  
//  @Test(dependsOnMethods= {"validateError"})
  @Test(priority=2)
  public void loginMessageValidation() throws InterruptedException {
	  LandingPage landingPage=new LandingPage(driver);
	  landingPage.login("ishaq8283@gmail.com", "test123");
	  ProductCatalogue pC=new ProductCatalogue(driver);
	  String expectedLoginMessage="Login Successfully";
	  String actualLoginMessage=pC.toastContainerText();
	  Assert.assertEquals(expectedLoginMessage, actualLoginMessage, "ValidationMessage not matched");
  }
  
}
