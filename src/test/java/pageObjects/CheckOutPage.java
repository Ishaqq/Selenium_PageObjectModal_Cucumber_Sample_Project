package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	@FindBy(xpath="//div[contains(text(),'CVV Code')]/following-sibling::input")
	WebElement cvvCode;
	
	@FindBy(xpath="//div[contains(text(),'Name on Card')]/following-sibling::input")
	WebElement name;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//input[@placeholder='Select Country']/following-sibling::section/button/span")
	List<WebElement> countrySpan;
	
	@FindBy(xpath = "//a[contains(text(),'Place Order ')]")
	WebElement placeOrderLink;
	
	@FindBy(css=".hero-primary")
	WebElement spanMessage;
	
	public void enterInformation() {
		cvvCode.sendKeys("123");
		name.sendKeys("Ishaq Afridi");
	}
	
	public void selectCountry() {
		String reqCountry="Pakistan";
		country.sendKeys(reqCountry);
		for(WebElement rqCountry:countrySpan) {
			if(rqCountry.getText().equalsIgnoreCase(reqCountry)) {
				rqCountry.click();
			}
		}
	}
	public void clickCheckOut() {
		placeOrderLink.click();
	}
	
	public String checkOutMessage() {
		String message=spanMessage.getText();
		return message;
	}
}
