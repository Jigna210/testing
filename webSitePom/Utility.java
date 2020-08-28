package webSitePom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Utility 
{

	WebDriver driver;
	public Utility(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public void moveElement(WebElement element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
}
