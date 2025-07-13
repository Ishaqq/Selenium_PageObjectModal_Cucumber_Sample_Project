package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import utils.SharedContext;

public class LoginIntoApplication extends BaseTest {
	SharedContext context;
	public ProductCatalogue pc;

    public LoginIntoApplication(SharedContext context) {
        this.context = context;
    }


	@Given("The user is on the landing page")
	public void the_user_is_on_the_landing_page() {
		//landingPage = launchApplication();
	}

	@When("Logged in with the username {string} and password {string}")
	public void logged_in_with_the_username_and_password(String email, String password) throws InterruptedException {
	    context.landingPage.login(email, password);
	}

	@Then("Success message {string} should appear after login")
	public void success_message_should_appear_after_login(String expectedMessage) {
		pc = new ProductCatalogue(context.driver);
		String actualMessage = pc.toastContainerText(); // Update with actual toast method
		Assert.assertEquals(actualMessage, expectedMessage, "Login message validation failed");
	}

}
