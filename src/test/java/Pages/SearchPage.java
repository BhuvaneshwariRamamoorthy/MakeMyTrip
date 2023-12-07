package Pages;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import CommonWaitFunctions.*;
import CommonWebElementFunctions.*;
import SubPages.CitySelection;

public class SearchPage {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;
	CitySelection citySelection;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(driver);
		citySelection = new CitySelection(driver);
		waitFunction = new ExplicitWaitFunctions(driver);
	}

	public void SelectFromLocation(String FromCity) {

		elementFunction.ClickableElement(driver.findElement(By.xpath("//label//input[@id='fromCity']")));
		elementFunction.textBoxToBeEntered(FromCity, driver.findElement(By.xpath("//div//input[@placeholder='From']")));
		waitFunction.explicitWaitForTextToBeInElement(
				By.xpath("//*[@role='listbox']//div[contains(@class,'react-autosuggest__section-title')]//p"),
				"SUGGESTIONS");

		boolean clickEvent = citySelection.citySelectionCode(FromCity);
		Assert.assertTrue(clickEvent, "From City/Airport name is selected successfully");

	}

	public void SelectToLocation(String ToCity) {

		elementFunction.ClickableElement(driver.findElement(By.xpath("//label//input[@id='toCity']")));
		elementFunction.textBoxToBeEntered(ToCity, driver.findElement(By.xpath("//div//input[@placeholder='To']")));
		waitFunction.explicitWaitForTextToBeInElement(
				By.xpath("//*[@role='listbox']//div[contains(@class,'react-autosuggest__section-title')]//p"),
				"SUGGESTIONS");
		boolean clickEvent = citySelection.citySelectionCode(ToCity);
		Assert.assertTrue(clickEvent, "To City/Airport name is selected successfully");

	}

	public void SelectDepatureDate(String departureDate) {

		// elementFunction.ClickableElement(driver.findElement(By.xpath("(//div[contains(@class,'dates')]//parent::label[@for='departure']//span)[1]")));
		String expectedDepartureDate = null;
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("E, MMM dd yyyy");
			Date date = inputFormat.parse(departureDate);
			expectedDepartureDate = outputFormat.format(date).replace(",", "");
		} catch (ParseException e) {

		}

		List<WebElement> enabledDates = driver
				.findElements(By.xpath("//div[contains(@class,'DayPicker-Day') and @aria-disabled='false']"));
		List<WebElement> disabledDates = driver
				.findElements(By.xpath("//div[contains(@class,'DayPicker-Day--disabled') and @aria-disabled='true']"));

		boolean clickEvent = false;
		for (WebElement disableddate : disabledDates) {
			if (elementFunction.retriveAttributeValue(disableddate, "aria-label") != null) {
				if (elementFunction.retriveAttributeValue(disableddate, "aria-label")
						.equalsIgnoreCase(expectedDepartureDate)) {
					System.out.println("Entered date is disabled");
					clickEvent = true;
					break;
				}
			}
		}

		if (!clickEvent) {
			for (WebElement actualDate : enabledDates) {
				if (elementFunction.retriveAttributeValue(actualDate, "aria-label") != null) {
					if (actualDate.getAttribute("aria-label").equalsIgnoreCase(expectedDepartureDate)) {
						actualDate.click();
						clickEvent = true;
						break;
					}
				}
			}
		}

		if (!clickEvent) {
			WebElement nextmonth = driver.findElement(
					By.cssSelector("div.DayPicker>div>div[class='DayPicker-NavBar']>span[aria-label='Next Month']"));
			if (nextmonth.isDisplayed()) {
				elementFunction.ClickableElement(nextmonth);
				SelectDepatureDate(departureDate);
			} else
				System.out.println("Cannot proceed with the next month");
		}
	}

	public void travellerDetails(int adultCount, int childCount, int infantCount, String economy) {
		elementFunction.ClickableElement(driver.findElement(By.xpath("//div[@data-cy='flightTraveller']")));
		waitFunction.explicitWaitForElementToBeVisible(By.xpath("//div[contains(@class,'fltTravellers')]//child::ul"));

		// Get the adult,child,infant and travel class option from the application
		List<WebElement> AdultList = driver.findElements(
				By.xpath("//div[contains(@class,'fltTravellers')]//child::ul//li[contains(@data-cy,'adults')]"));
		List<WebElement> ChildList = driver.findElements(
				By.xpath("//div[contains(@class,'fltTravellers')]//child::ul//li[contains(@data-cy,'children')]"));
		List<WebElement> InfantList = driver.findElements(
				By.xpath("//div[contains(@class,'fltTravellers')]//child::ul//li[contains(@data-cy,'infants')]"));
		List<WebElement> TravelClass = driver.findElements(
				By.xpath("//div[contains(@class,'fltTravellers')]//child::ul//li[contains(@data-cy,'travelClass')]"));

		boolean aclickEvent = false, iclickEvent = false, cclickEvent = false, tclickEvent = false;
		String expectedAdult = null, expectedChild, expectedInfant;

		// Select relevant traveller count based on data from excel
		for (WebElement adult : AdultList) {
			String actualAdult = elementFunction.retriveText(adult);
			if (adultCount > 9 && adultCount != 0)
				expectedAdult = ">9";
			else if (adultCount != 0)
				expectedAdult = String.valueOf(adultCount);

			if (actualAdult.equalsIgnoreCase(expectedAdult)) {
				elementFunction.ClickableElement(adult);
				aclickEvent = true;
				break;
			}
		}
		for (WebElement Child : ChildList) {
			String actualChild = elementFunction.retriveText(Child);
			if (childCount > 6)
				expectedChild = ">6";
			else
				expectedChild = String.valueOf(childCount);
			if (actualChild.equalsIgnoreCase(expectedChild)) {
				elementFunction.ClickableElement(Child);
				cclickEvent = true;
				break;
			}
		}
		for (WebElement Infant : InfantList) {
			String actualInfant = elementFunction.retriveText(Infant);
			if (infantCount > 6)
				expectedInfant = ">6";
			else
				expectedInfant = String.valueOf(infantCount);
			if (actualInfant.equalsIgnoreCase(expectedInfant)) {
				elementFunction.ClickableElement(Infant);
				iclickEvent = true;
				break;
			}
		}
		for (WebElement actualTraveler : TravelClass) {
			String traveller1 = actualTraveler.getText();
			if (traveller1.contains(economy)) {
				actualTraveler.click();
				tclickEvent = true;
				break;
			}
		}

		// If all traveler count options are valid or not
		if (!aclickEvent || !iclickEvent || !cclickEvent || !tclickEvent) {
			System.out.println("Invalid traveller details entered");
		} else
			elementFunction.ClickableElement(driver.findElement(By.xpath("//button[text()='APPLY']")));

	}

	public String GetFromLocation() {

		return elementFunction.retriveAttributeValue(By.xpath("//label//input[@id='fromCity']"), "value");
	}

	public String GetToLocation() {

		return elementFunction.retriveAttributeValue(By.xpath("//label//input[@id='toCity']"), "value");
	}

	public void ClickOnSearchButton() {

		elementFunction.ClickableElement(By.xpath("//a[text()='Search']"));
	}

}
