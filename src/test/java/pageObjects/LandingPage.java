package pageObjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement emailField;
	
	@FindBy(xpath="//*[@type=\"password\"]")
	WebElement passwordField;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css=".toast-message")
	WebElement toastMessage;
	
	public void navigateHomePage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void login(String email, String password) throws InterruptedException {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		Thread.sleep(500);
	}
	public String validationMessage() {
		String validationMessage=toastMessage.getText();
		return validationMessage;
	}
}
