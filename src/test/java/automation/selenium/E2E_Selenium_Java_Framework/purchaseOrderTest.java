package automation.selenium.E2E_Selenium_Java_Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import java.util.Arrays;
import java.util.List;

public class purchaseOrderTest extends BaseTest {
	@Test
	public void loginTest() throws InterruptedException {
	//	launchApplication();
		String username = System.getProperty("userName");
		if (username == null) {
		    username = prop.getProperty("userName");
		}
		String password = System.getProperty("password");
		if (password == null) {
		    password = prop.getProperty("password");
		}
		LandingPage landingPage=new LandingPage(driver);
		landingPage.login(username, password);
		ProductCatalogue pc = new ProductCatalogue(driver);
		pc.waitForLoginToast();
		String expectedLoginMessage = "Login Successfully";
		String ActualLoginMessage = pc.toastContainerText();
		System.out.println(ActualLoginMessage);
		pc.waitForLoginToast();
		Assert.assertEquals(ActualLoginMessage, expectedLoginMessage, "User is not logedIn");

		List<String> itemsToBuy = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
		pc.addProductToCart(itemsToBuy);
		// pc.addProductthroughStream(itemsToBuy);
		pc.goToCart();

		CartPage cp = new CartPage(driver);
		cp.verifyAddedProducts(itemsToBuy);
		cp.calculatePrice();
		cp.VerifyPrice();
		cp.clickCheckOut();

		CheckOutPage cOP = new CheckOutPage(driver);
		cOP.enterInformation();
		cOP.selectCountry();
		cOP.clickCheckOut();

		String purchaseMessage = cOP.checkOutMessage();
		String expectedPurchaseMessage = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(purchaseMessage, expectedPurchaseMessage, "Message is not same");
		Thread.sleep(2000); 
		tearDown();
	}
}