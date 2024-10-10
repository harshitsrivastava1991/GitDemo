package harshitsrivastavatraining.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import harshitsrivastavatraining.pageObjects.CartPage;
import harshitsrivastavatraining.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
//	how to enable the driver so that its conencted to all files-

//	Constructor created to get the connectivity  of deriver from child class landing page-
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	// its waiting for the element to appear-
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public CartPage goToCartpage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

		Thread.sleep(3000);
//		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		

	}
	
	public OrderPage goToOrderspage() {
		
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
