package harshitsrivastavatraining.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshitsrivastavatraining.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	
	WebDriver driver;
	
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css= ".hero-primary")
	WebElement confirmationMesssage;
	
	
	public String getConfirmationMessage() {
		
		
		return confirmationMesssage.getText();
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	}
	
}
