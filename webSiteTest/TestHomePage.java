package webSiteTest;


import org.testng.annotations.Test;
import webSitePom.Homepage;
import webSitePom.ProductBuy;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class TestHomePage extends BaseClass {

	@Test
	public void openHomepage() {
		new Homepage(driver);

		// Make sure that page title is correct.

		Assert.assertEquals(driver.getTitle(),
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}

	/*
	 * @Test(dataProvider = "link_lists") public void verifyMainMenuLinks(String
	 * linkname) { Homepage page = new Homepage(driver);
	 * page.getMenuLinks(linkname); System.out.println(driver.getTitle()); }
	 * 
	 * @DataProvider(name = "link_lists") public static Object[][] getLinkdata() {
	 * return new Object[][] { { "Mobiles" }, { "Best Sellers" }, { "Computers" }, {
	 * "Pantry" }, { "Books" },{"Gift Ideas	"},{"New Releases"}}; }
	 * 
	 * @Test public void verifyuserLogin() { Homepage page = new Homepage(driver);
	 * page.setLogin("jigna210@yahoo.com", "jigna1234"); //
	 * Assert.assertEquals(driver.getTitle(), "Register - DMart",
	 * "Page Title does not match");
	 * 
	 * }
	 */
	@Test
	public void verifyAddtoCartitem() throws InterruptedException {
		Homepage page = new Homepage(driver);
		page.validateSearch("Dairy milk");
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

		// Click on Item
		driver.findElement(By.xpath("//span[contains(text(),'Cadbury Celebrations Rich Dry Fruit Chocolate Gift')]"))
				.click();
		Thread.sleep(2000);		
		
		// Switch to next tab
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		ProductBuy bItem = new ProductBuy(driver);
		String actualPrice = bItem.getPrice();
		System.out.println("Price = "+actualPrice);
		
		bItem.addToCart();		
		bItem.checkCart();
		bItem.verifyCartReviewPage();
		System.out.println("Subtotal = "+bItem.getSubTotal());
		
	}

}
