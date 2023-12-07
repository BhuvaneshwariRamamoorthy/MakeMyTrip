package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonWaitFunctions.ExplicitWaitFunctions;
import CommonWebElementFunctions.*;

public class FlightSummary {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public FlightSummary(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(driver);
		waitFunction = new ExplicitWaitFunctions(driver);
	}

	public boolean tripSecure(String secureTrip) {
		boolean messageReceived = false;
		try {
			elementFunction.ClickableElement(By.xpath(
					"//div[contains(@class,'insRadioSection')]//span//b[contains(text(),'" + secureTrip + "')]"));
			waitFunction.explicitWaitForElementToBeVisible(
					By.xpath("//div[contains(@class,'insBottomSectionV3_2')]//span[@class='alert-text']"));
			String secureTripMessage = elementFunction.retriveText(driver.findElement(By.xpath("//div[contains(@class,'insBottomSectionV3_2')]//span[@class='alert-text']")));
			if(secureTripMessage.equalsIgnoreCase("Great! Your trip is secured.")||secureTripMessage.equalsIgnoreCase("Don't let a flight delay or cancellation add to your worries. Get your trip secured."))
			{
				messageReceived=true;
			}
			
			return messageReceived;
		} catch (Exception e) {

		}
		return messageReceived;
	
	}

}
