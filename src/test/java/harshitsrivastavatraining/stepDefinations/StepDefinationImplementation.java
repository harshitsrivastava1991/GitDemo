package harshitsrivastavatraining.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import harshitsrivastavatraining.TestComponents.BaseTest;
import harshitsrivastavatraining.pageObjects.CartPage;
import harshitsrivastavatraining.pageObjects.CheckOutPage;
import harshitsrivastavatraining.pageObjects.ConfirmationPage;
import harshitsrivastavatraining.pageObjects.LandingPage;
import harshitsrivastavatraining.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on the Ecommerce page")

	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();

	}

//	regular expression syntax to get the username and password 
	@Given("^Login with username (.+) and passwowrd (.+)$")

	public void Login_with_Username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);

	}

//	regular expression to represent the entire string of product add
	@When("^I add the product (.+) to Cart$")

	public void I_add_the_product_to_Cart(String productName) throws InterruptedException {

		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")

	public void Checkout_and_submit_the_order(String productName) {

		CartPage cartPage = productCatalogue.goToCartpage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();
	}

//	if data is coming directly from the then/when/and statement then use {string}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
