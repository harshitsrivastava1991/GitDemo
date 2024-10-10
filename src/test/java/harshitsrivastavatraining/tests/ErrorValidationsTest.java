package harshitsrivastavatraining.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import harshitsrivastavatraining.TestComponents.BaseTest;
import harshitsrivastavatraining.TestComponents.Retry;
import harshitsrivastavatraining.pageObjects.CartPage;
import harshitsrivastavatraining.pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		

		landingPage.loginApplication("harshitsrivastava1991@gmail.com", "Oct1@1810");
		
//		.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error	
//		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test(retryAnalyzer=Retry.class)	
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("harshitsrivastava19912@gmail.com", "Oct@1810");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartpage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		
	}

}
