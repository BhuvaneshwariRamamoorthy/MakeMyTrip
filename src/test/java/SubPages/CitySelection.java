package SubPages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonWaitFunctions.ExplicitWaitFunctions;
import CommonWebElementFunctions.WebElementFunctions;

public class CitySelection {

	public static WebDriver driver;
	static boolean clickEvent = false;
	ExplicitWaitFunctions waitFunction;
	WebElementFunctions elementFunction;

	public CitySelection(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(driver);
		waitFunction = new ExplicitWaitFunctions(driver);
	}

	public boolean citySelectionCode(String expectedCity) {
		waitFunction.explicitWaitForElementToBeClickable(
				By.xpath("(//*[@role='listbox']//ul[@role='listbox']//li)[last()]"));
		List<WebElement> citiesCode = driver.findElements(
				By.xpath("//*[@role='listbox']//ul[@role='listbox']//li//div[contains(@class,'pushRight')]"));

		for (WebElement actualCity : citiesCode) {
			String actualCityCode = elementFunction.retriveText(actualCity);
			if (actualCityCode.equalsIgnoreCase(expectedCity)) {
				elementFunction.ClickableElement(actualCity);
				clickEvent = true;
				break;
			}

		}
		if (!clickEvent) {
			citySelectionName(expectedCity);
		}
		return clickEvent;
	}

	public void citySelectionName(String expectedCity) {

		waitFunction.explicitWaitForElementToBeClickable(By.xpath(
				"(//*[@role='listbox']//ul[@role='listbox']//li//div[@class='calc60']//p[contains(text(),',')])[last()]"));
		List<WebElement> CitiesName = driver.findElements(By
				.xpath("//*[@role='listbox']//ul[@role='listbox']//li//div[@class='calc60']//p[contains(text(),',')]"));

		for (WebElement actualCity : CitiesName) {
			String actualCityName = elementFunction.retriveText(actualCity);
			if (actualCityName.contains(StringUtils.capitalize(expectedCity))) {
				elementFunction.ClickableElement(actualCity);
				clickEvent = true;
				break;
			}
		}
		if (!clickEvent) {
			citySelectionAirport(expectedCity);
		}
	}

	public void citySelectionAirport(String expectedCity) {

		waitFunction.explicitWaitForElementToBeClickable(By.xpath(
				"(//*[@role='listbox']//ul[@role='listbox']//li//div[@class='calc60']//following-sibling::p)[last()]"));
		List<WebElement> citiesAirport = driver.findElements(
				By.xpath("//*[@role='listbox']//ul[@role='listbox']//li//div[@class='calc60']//following-sibling::p"));

		for (WebElement actualCity : citiesAirport) {
			String actualAirportName = elementFunction.retriveText(actualCity);
			if (actualAirportName.contains(StringUtils.capitalize(expectedCity))) {
				elementFunction.ClickableElement(actualCity);
				clickEvent = true;
				break;
			}
		}

	}
}
