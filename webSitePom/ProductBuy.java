package webSitePom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestException;

public class ProductBuy 
{
	WebDriver driver;
	private final By PRICE = By.cssSelector("#priceblock_ourprice");
	private final By SUBTOTAL = By.cssSelector("#sc-subtotal-amount-activecart"); 
		
	public ProductBuy(WebDriver driver)
	{
		this.driver = driver;
	}
	public void addToCart() throws InterruptedException {
		// Click on Add-to-cart button
		WebElement add_to_cart = driver.findElement(By.id("add-to-cart-button"));
		add_to_cart.click();
		Thread.sleep(2000);
		
		//Make sure that item is added in cart or not
		WebElement addedCart = driver.findElement(By.xpath("//h1[@class='a-size-medium a-text-bold']")); // added to cart label
		WebElement cart = driver.findElement(By.cssSelector("#huc-v2-order-row-inner"));
		Assert.assertTrue(addedCart.isDisplayed(), "Item is not added into Cart");
		Assert.assertTrue(cart.isDisplayed(), "Item is not added into Cart");
		
	}
	
	public void verifyCartReviewPage() {
		
		String url = driver.getCurrentUrl();
		if(!url.contains("view")) {
			throw new TestException("ERROR: Not on SHOPPING_CART_REVIEW_PAGE! URL: " + url); 
		}
	}
	public String getPrice(){
			
		return driver.findElement(PRICE).getText(); 
	}
	
	public String getSubTotal(){
		return driver.findElement(SUBTOTAL).getText(); 
	}
	
	public void checkCart() throws InterruptedException {
		Thread.sleep(1000);
		WebElement cartBtn = driver.findElement(By.id("hlb-view-cart-announce"));
		cartBtn.click();
		Thread.sleep(1000);	
	}

}
