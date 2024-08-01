package Tests;
import java.util.HashMap;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import reusableComponents.ExcelOperations;
import reusableComponents.PropertiesOperations;
import reusableComponents.ScreenShotOperation;

import testBase.TestCaseBase;


public class TestCase extends TestCaseBase {
	WebDriver driver;

	LoginPageObj login;
	HomePageObj homepageobj;
	
	ExcelOperations excel = new ExcelOperations("TestData");
	ScreenShotOperation scOperation= new ScreenShotOperation();

	
	@BeforeSuite
	public void setUp()

	{
		
		String url = PropertiesOperations.getPropertyValueByKey("url");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);

		

	}
	@Test
	public void launchApplication() throws Exception {
		login = new LoginPageObj(driver);
		homepageobj = new HomePageObj(driver);

		String username = PropertiesOperations.getPropertyValueByKey("username");
		String password = PropertiesOperations.getPropertyValueByKey("password");
		

//		String url = PropertiesOperations.getPropertyValueByKey("url");
//		driver.get(url);

		login.clickSignIn(username, password);
		
		
		

	}

	@Test(dataProvider = "testDataSupplier")
	public void performOperations(Object obj1) throws Exception {
		// -------------------adding items in the cart----------------------------

		// Selecting item from filter

		homepageobj.selectFilter(2);
		homepageobj.addFirstItem();

		// Selecting a custom item by providing name
		homepageobj.addCustomItem("Sauce Labs Bike Light");
		
		

		// Navigating to cart
		homepageobj.navigateCart();
		
		Thread.sleep(3000);

		// Checkout
		homepageobj.clickCheckOut();
		Thread.sleep(3000);

		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		homepageobj.provideCheckOutInfo(testData.get("Firstname"),testData.get("Lastname"),testData.get("Zipcode"));
		
	
		
		homepageobj.checkoutDeatils();
		
		String FilePath = PropertiesOperations.getPropertyValueByKey("ScreenShotPath");
		
		scOperation.takeSnapShot(driver, FilePath);

	}

	
	
	
//	
	//Dataprovider method --> return object array
	@DataProvider 
	public Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0] = testData;
		}
		return obj;
		
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
	

}
