package harshitsrivastavatraining.pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshitsrivastavatraining.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(xpath = "//*[@class='cartSection']/h3")
	private List<WebElement> cartProducts;

//	Creating  constructor to get the properties of driver from SubmitorderTest class.
	public CartPage(WebDriver driver) {

//	using super keyword to send driver info to the abstract componnet class whihc is the parent.
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyProductDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

//List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
//Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
	}

	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
