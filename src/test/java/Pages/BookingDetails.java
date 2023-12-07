package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonWebElementFunctions.*;
import CommonWaitFunctions.*;

public class BookingDetails {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public BookingDetails(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public void bookingDetailsSender(String countryCode, String mobileNumber, String email) {

		elementFunction.ClickableElement(
				By.xpath("//div[contains(@class,'bookingDetailsForm')]//div[contains(@class,'dropdown__control')]"));
		String classAttribute = elementFunction.retriveAttributeValue(
				By.xpath("//div[contains(@class,'bookingDetailsForm')]//div[contains(@class,'dropdown__control')]"),
				"class");
		if (classAttribute.contains("dropdown__control--menu-is-open")) {
			waitFunction.explicitWaitForElementToBeClickable(
					By.xpath("(//div[contains(@id,'react-select-2-option')])[last()]"));
			List<WebElement> allCountryCodes = driver
					.findElements(By.xpath("//div[contains(@id,'react-select-2-option')]"));
			for (WebElement actualCountryCode : allCountryCodes) {
				if (elementFunction.retriveText(actualCountryCode).contains(countryCode)) {
					elementFunction.ClickableElement(actualCountryCode);
					break;
				}
			}
		}

		waitFunction.explicitWaitforPresenceOfElement(By.xpath("//input[@placeholder='Mobile No']"));
		elementFunction.textBoxToBeEntered(mobileNumber, By.xpath("//input[@placeholder='Mobile No']"));

		waitFunction.explicitWaitforPresenceOfElement(By.xpath("//input[@placeholder='Email']"));
		elementFunction.textBoxToBeEntered(email, By.xpath("//input[@placeholder='Email']"));

		elementFunction.ClickableElement(By.xpath("//input[@placeholder='Email']//parent::div//div[@class='emailId']"));

	}
}
