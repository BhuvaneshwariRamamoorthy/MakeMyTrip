package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import CommonWaitFunctions.*;
import CommonWebElementFunctions.*;

public class PincodeAndStateDetails {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public PincodeAndStateDetails(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public void invoiceDetails(String pincode, String state, String address) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(1000);

		waitFunction.explicitWaitforPresenceOfElement(By.xpath("//input[@id='pincode_gst_info']"));
		WebElement pincodeField = driver.findElement(By.xpath("//input[@id='pincode_gst_info']"));
		js.executeScript("arguments[0].scrollIntoView();", pincodeField);
		elementFunction.textBoxToBeEntered(pincode, pincodeField);

		Actions act = new Actions(driver);
		act.keyDown(Keys.TAB).perform();
		act.keyUp(Keys.TAB).perform();

		waitFunction.explicitWaitForElementToBeClickable(By.xpath("(//ul[@class='dropdownListWpr']//li)[last()]"));
		List<WebElement> allStates = driver.findElements(By.xpath("//ul[@class='dropdownListWpr']//li"));
		for (WebElement actualState : allStates) {
			if (elementFunction.retriveText(actualState).contains(state)) {
				elementFunction.ClickableElement(actualState);
				break;
			}
		}

		waitFunction.explicitWaitforPresenceOfElement(By.xpath("//input[@id='address_gst_info']"));
		WebElement addressField = driver.findElement(By.xpath("//input[@id='address_gst_info']"));
		js.executeScript("arguments[0].scrollIntoView();", addressField);
		elementFunction.textBoxToBeEntered(address, addressField);

		//Thread.sleep(2000);
		waitFunction.explicitWaitForElementToBeClickable(By.xpath("//div[@class='checkboxWithLblWpr']//div"));
		elementFunction.ClickableElement(By.xpath("//div[@class='checkboxWithLblWpr']//p"));

	}

}
