package webSitePom;

import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import webSitePom.*;

public class Homepage {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAlertPresentold() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public WebElement pincode_form(String pinnum) {

		WebElement pin = driver.findElement(By.xpath("//*[@id='pinNumberModal']"));
		pin.sendKeys("" + pinnum);
		return pin;
	}

	public WebElement getMenuLinks(String Linkname) {
		WebElement linkElement = driver
				.findElement(By.xpath("//div[@id='nav-xshop']/a[contains(text(),'" + Linkname + "')]"));

		linkElement.click();
		return linkElement;
	}

	public void setLogin(String uName, String pwd) {
		WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
		Utility uti = new Utility(driver);
		uti.moveElement(accountList);

		// Fill register form
		driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']/a/span")).click();
		driver.findElement(By.id("ap_email")).sendKeys(uName);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		byte[] encodedBytes = Base64.getEncoder().encode(pwd.getBytes());
		System.out.println("encodedBytes --------------->" + new String(encodedBytes));
		driver.findElement(By.id("ap_password")).sendKeys(new String(encodedBytes));
		driver.findElement(By.id("signInSubmit")).click();

	}

	public void validateSearch(String itemName) throws InterruptedException {
		
		WebElement searchItem = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchItem.sendKeys(itemName);
        Thread.sleep(1000);
        searchItem.sendKeys(Keys.ARROW_DOWN);
        searchItem.sendKeys(Keys.ENTER);
		/*
		 * List<WebElement> collection_product_links = driver
		 * .findElements(By.xpath("//input[@id='twotabsearchtextbox']"));
		 * System.out.println("Item list ------"+collection_product_links);
		 * 
		 * // Validate if Search result is displayed corresponding // to the string
		 * which was searched for (int i = 0; i < collection_product_links.size(); i++)
		 * { String temp = collection_product_links.get(i).getText();
		 * 
		 * if ((temp.toLowerCase().contains(itemName.toLowerCase()))) {
		 * Assert.assertTrue(true, itemName +
		 * " is displayed on product title Product Title: " + temp); } else {
		 * Assert.assertTrue(false, itemName +
		 * " is not displayed on product title Product Title: " + temp);
		 * 
		 * } }
		 */
	}
}
