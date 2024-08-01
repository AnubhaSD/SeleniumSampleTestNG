
package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserFactory 
{
	public WebDriver setBrowser(String browser)
	{
		WebDriver driver = null;
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			//For executing in incognito mode 
			ChromeOptions optionsC= new ChromeOptions();
			optionsC.addArguments("--incognito");
			driver=new ChromeDriver(optionsC);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions optionsF= new FirefoxOptions();
			optionsF.addArguments("--incognito");
			driver= new FirefoxDriver(optionsF);
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions optionsIe= new InternetExplorerOptions();
			optionsIe.addCommandSwitches("--incognito");
			driver = new InternetExplorerDriver(optionsIe);
		}
		
		return driver;
		
	}

	
}