package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#toast-container")
	WebElement toastContainer;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By cartbutton = By.cssSelector(".card-body button:last-of-type");
	By goToCartButton=By.cssSelector("[routerlink*='cart']");

	public String toastContainerText() {
		String message = toastContainer.getText();
		return message;
	}

	public void waitForLoginToast() {
		waitUntilVisibilityOfElement(toastContainer);
	}

	public void waitForProductToast() {
		waitUntilVisibilityOfElement(toastContainer);
	}

	public void waitForSpiner() throws InterruptedException {
		waitUntilInVisibilityOfElement(spinner);
	}

	public void addProductToCart(List<String> itemsToBuy) throws InterruptedException {
		for (WebElement product : products) {
			String productName = product.findElement(By.cssSelector("b")).getText();
			System.out.println(productName);
			if (itemsToBuy.contains(productName)) {
				product.findElement(cartbutton).click();
			}
			waitForSpiner();
		}
	}

	public void addProductthroughStream(List<String> itemsToBuy) throws InterruptedException {
		for (String item : itemsToBuy) {
			WebElement prod = products.stream()
					.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item))
					.findFirst().orElse(null);
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


			waitForSpiner();
		}

	}
	
	public void goToCart() {
		fwaitAndClick(goToCartButton);
	
	}
}
