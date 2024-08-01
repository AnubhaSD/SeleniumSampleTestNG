package pageObjects;



import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;






public class LoginPageObj 
{
	
	///////////Page Object Model////////////
	
	WebDriver driver;
	
	By username= By.name("user-name");
	By pwd= By.id("password");
	By btnLogin= By.id("login-button");
	
	
	public LoginPageObj(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSignIn(String uname, String password)
	{
		
		waitForElement(driver.findElement(username));
		
		driver.findElement(username).sendKeys(uname);
		driver.findElement(pwd).sendKeys(password);
		
		driver.findElement(btnLogin).click();
		
		
		
	}
	
	
	public void waitForElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	
///////// By using PageFactory	
//	
//	@FindBy(id= "session_key")
//	WebElement username;
//	
//	@FindBy(id= "session_password")
//	WebElement password;
//	
//	@FindBy(linkText= "Sign In")
//	WebElement btn_signIn;
//	
//	@FindBy(xpath="//div/a[2][contains(text(),'Sign in')]")
//	WebElement btnSignInClick;
//	
//	public LoginPage(WebDriver driver)
//	{
//		PageFactory.initElements(driver, this);
//	}
//	public void clickSignInBtn()
//	{
//		btnSignInClick.click();
//	}
//	public void Login(String uname, String pwd)
//	{
//		username.sendKeys(uname);
//		password.sendKeys(pwd);
//		
//		btn_signIn.click();
//	}


}
