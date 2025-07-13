package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class CartPage  extends AbstractComponent {
	WebDriver driver;
	int calculatedPrice = 0;
	int totalPrice=0;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart ul .items")
	List<WebElement> cartItems;
	
	By cartPriceLoc=By.cssSelector("p:nth-of-type(2)");
	By productNameLoc=By.cssSelector("h3");
	
	@FindBy(css=".totalRow:nth-of-type(2) span:nth-of-type(2)")
	WebElement totalPriceLoc;
	
	@FindBy(xpath="//li/button[contains(text(),'Checkout')]")
	WebElement checkOutButton;
	
	public void verifyAddedProducts(List<String> actualItems) {
	   
	    for (WebElement item : cartItems) {
	    	String cartItems=item.findElement(productNameLoc).getText().trim();
	    	System.out.println("Items in Cart: " + cartItems);
		        Assert.assertTrue(cartItems.contains(cartItems),"❌ Missing item in cart: " + actualItems);	       
	    }
	}
	
	public void calculatePrice() {
	

		for (WebElement cartItem : cartItems) {
		    String priceString = cartItem.findElement(cartPriceLoc).getText(); 
		    String[] priceArray = priceString.split(" "); 
		    
		    int priceProduct = Integer.parseInt(priceArray[2]);
		    calculatedPrice += priceProduct;
		}
		System.out.println("Total Cart Price: " + calculatedPrice);
	}
	
	public int totalPrice() {
		String priceText=totalPriceLoc.getText();
		int totalPrice = Integer.parseInt(priceText.replace("$", "").trim());
		return totalPrice;
	}
  
	public void VerifyPrice() {
		totalPrice=totalPrice();
		Assert.assertEquals(calculatedPrice, totalPrice , "Price not equal");
	}
	
	public void clickCheckOut() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll into view and adjust scroll slightly
	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkOutButton);

	    // Give browser time to render the view properly
	    try {
	        Thread.sleep(500); // Not ideal in prod, better to use WebDriverWait in real scenarios
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Optionally scroll again slightly in case still covered
	    js.executeScript("window.scrollBy(0, -100);"); // shift up to ensure it's not behind sticky header

	    // Final click
	    checkOutButton.click();
	}
	
	
}
