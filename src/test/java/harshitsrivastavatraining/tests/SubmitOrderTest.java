package harshitsrivastavatraining.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import harshitsrivastavatraining.TestComponents.BaseTest;
import harshitsrivastavatraining.pageObjects.CartPage;
import harshitsrivastavatraining.pageObjects.CheckOutPage;
import harshitsrivastavatraining.pageObjects.ConfirmationPage;
import harshitsrivastavatraining.pageObjects.OrderPage;
import harshitsrivastavatraining.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

//		Everything is taken care in basetest class for url and browser invocation and login page load
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//		Login Page(landing page object model class)
//		Calling the loginapplication method from landing page class to login on the page
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		LandingPage landingPage = launchApplication();

//		ProductCatalogue productCatalogue = landingPage.loginApplication("harshitsrivastava1991@gmail.com", "Oct@1810");
//		ProductCatalogue productCatalogue = landingPage.loginApplication(email, Password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

//		Product Catalogue page(ProductCatlogue object model class)
//		Calling the ProductCatalogue methods from productcatalogue class and also wait declaration through extending abstractcomponent class-
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver); geting called directly from landing page class

		productCatalogue.getProductList();

//		Finding any product on the catalogue page and adding it to card(ProductCatlogue object model class)
//		productCatalogue.addProductToCart(productName);
		productCatalogue.addProductToCart(input.get("product"));

//		All Waits for disappering messages and spinners are handled in Abstract Component class.

//		Going to cat page(code in abstract class)
		CartPage cartPage = productCatalogue.goToCartpage();

//		Cartpage with selected cart products to verify correct cart product is added(CartPage class)
//		CartPage cartPage= new CartPage(driver); getting called directly from abstract class now
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

//		Clicking on checkout link(cartPage class)-
//		cartPage.goToCheckout();

//		Final Checkout page-
//		CheckOut Page class- to select a country and then submit-
//		Clicking on checkout link(cartPage class)-
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
//		checkOutPage.submitOrder();

//		Confirmation Page-
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

//		Checking the confirmation message-
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

//		method created in baseTest class
//		driver.close();

	}

//		to verify if that selected products is displayed on the orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("harshitsrivastava1991@gmail.com", "Oct@1810");
		OrderPage orderPage = productCatalogue.goToOrderspage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

//	Parameterization -- creating multiple data sets whihc will be used to run the above code
	@DataProvider
	public Object[][] getData() throws IOException {

//		Crating a hasmap for more readable code to send data to above method-
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "harshitsrivastava1991@gmail.com");
//		map.put("password", "Oct@1810");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "harshitsrivastava1991@gmail.com");
//		map1.put("password", "Oct@1810");
//		map1.put("product", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { "harshitsrivastava1991@gmail.com", "Oct@1810", "ADIDAS ORIGINAL" },{ "harshitsrivastava1991@gmail.com", "Oct@1810", "ZARA COAT 3" } };

		
//		Everythign above is now getting extracted by a json file which is scanned below-
		

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//harshitsrivastavatraining//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
// 		utility to take scrreenshot is writtten in BaseTest Class
	

}
