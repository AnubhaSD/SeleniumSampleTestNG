package testBase;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import reusableComponents.PropertiesOperations;

public class TestCaseBase 
{
	
	WebDriver driver;
	BrowserFactory bf = new BrowserFactory();

	@BeforeMethod
	public void setUp()

	{
		
		String url = PropertiesOperations.getPropertyValueByKey("url");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);

		

	}
	

	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
