package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePageObj 
{
	WebDriver driver;
	By btnFilter=By.xpath("//span[@class='active_option']//following::select");
	By item1= By.xpath("//div[@class='inventory_item'][1]/div[2]/div[@class='pricebar']/button");
	By btnAddtoCart= By.id("add-to-cart");
	By cartSymbol= By.className("shopping_cart_link");
	By btnCheckOut= By.id("checkout");
	By checkoutFname= By.xpath("//input[@placeholder='First Name']");
	By checkoutLname= By.id("last-name");
	By checkoutzip= By.id("postal-code");
	By btncontinueCheckout= By.id("continue");
	By textTotalAmt= By.xpath("//div[@class='summary_total_label']");
	By btnFinish= By.id("finish");
	
	
	public HomePageObj(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectFilter(int value)
	{
		waitForElement(driver.findElement(btnFilter));
		Select selectItm= new Select(driver.findElement(btnFilter));
		selectItm.selectByIndex(value);
		
	}
	public void addFirstItem()
	{
		driver.findElement(item1).click();
	}
	public void addCustomItem(String value)
	{
		driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='"+value+"']")).click();
		waitForElement(driver.findElement(btnAddtoCart));
		driver.findElement(btnAddtoCart).click();
	}
	
	public void navigateCart()
	{
		driver.findElement(cartSymbol).click();
	}
	public void clickCheckOut()
	{
		driver.findElement(btnCheckOut).click();
	}
	
	public void provideCheckOutInfo(String fname, String lname, String zipcode)
	{
		
		waitForElement(driver.findElement(checkoutFname));
		driver.findElement(checkoutFname).sendKeys(fname);

		
		driver.findElement(checkoutLname).sendKeys(lname);
	
		driver.findElement(checkoutzip).sendKeys(zipcode);
		
		waitForElement(driver.findElement(btncontinueCheckout));
		driver.findElement(btncontinueCheckout).click();
		
	}
	public void checkoutDeatils()
	{
		String amount=driver.findElement(textTotalAmt).getText();
		System.out.println("Total Amount is: "+amount);
		
		//clicking on finish
		driver.findElement(btnFinish).click();
	}
	
	public void waitForElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
}
