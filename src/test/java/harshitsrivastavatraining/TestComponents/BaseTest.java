package harshitsrivastavatraining.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import harshitsrivastavatraining.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

//		how to setup global properties by using the properties class of java-

//		sytax to make sure that our test is reading the file-
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\harshitsrivastavatraining\\resources\\GlobalData.properties");
		prop.load(fis);

//		to make sure that we can pick any browser info from mvn command change the below line-
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		 prop.getProperty("browser");

		if (browserName.contains("chrome")) {
//		to run the program in headless mode-
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //help to run in fullscreen
		}

		else if (browserName.contains("firefox")) {
//			to run the program in headless mode-
			FirefoxOptions options1 = new FirefoxOptions();
			if (browserName.contains("headless")) {
				options1.addArguments("headless");
			}
			driver = new FirefoxDriver();

		} 
		
		else if (browserName.contains("edge")) {
//			to run the program in headless mode-
			EdgeOptions options2= new EdgeOptions();
			if(browserName.contains("headless"))
			{
				options2.addArguments("headless");
			}
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

//	Hashmap method creation to call data from json- dataReader-
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

//		reading json file content to a string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

//	Converting string content to hashmap using jackson databind-
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

//	Taking screenshot for any error-
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
