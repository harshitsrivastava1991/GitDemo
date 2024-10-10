package harshitsrivastavatraining.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshitsrivastavatraining.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent {

	WebDriver driver;

//	Creating  constructor to get the properties of driver from StandaloneTest class.
	public LandingPage(WebDriver driver) {

//	using super keyword to send driver info to the abstract componnet class whihc is the parent.
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement UserEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	// PageFactory

	@FindBy(id = "userEmail")
	WebElement UserEmail;

//	driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(pass);

	@FindBy(id = "userPassword")
	WebElement UserPass;

//	driver.findElement(By.xpath("//input[@id='login']")).click();

	@FindBy(id = "login")
	WebElement Submit;

//	.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {
		UserEmail.sendKeys(email);
		UserPass.sendKeys(password);
		Submit.click();

//		if we know what will be the next page we can create it inside any driver class like below-
//		 product catalog will be called from here only-
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

	}

}
