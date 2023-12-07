package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import CommonWaitFunctions.ExplicitWaitFunctions;
import CommonWaitFunctions.ImplicitWaitFunctions;
import CommonWebElementFunctions.WebElementFunctions;

public class TravellerDetailsSelection {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public TravellerDetailsSelection(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public int totalTravellerCount = 0;

	public void addPassengerDetails(int adultCount, int childCount, int infantCount, String adultDetails,
			String ChildDetails, String InfantDetails) throws InterruptedException {
		String[] eachAdultDetail = adultDetails.split(",");
		String[] adultFirstNames = new String[eachAdultDetail.length];
		String[] adultLastNames = new String[eachAdultDetail.length];
		String[] adultGender = new String[eachAdultDetail.length];

		for (int passengerCount = 0; passengerCount < eachAdultDetail.length; passengerCount++) 
		{
			String[] details = eachAdultDetail[passengerCount].split(" ");
			adultFirstNames[passengerCount] = details[0];
			adultLastNames[passengerCount] = details[1];
			adultGender[passengerCount] = details[2];
		}
		adult(adultCount, adultFirstNames, adultLastNames, adultGender);

		if (childCount > 0) {
			String[] eachChildDetail = ChildDetails.split(",");
			String[] childFirstNames = new String[eachChildDetail.length];
			String[] childLastNames = new String[eachChildDetail.length];
			String[] childGender = new String[eachChildDetail.length];
			for (int passengerCount = 0; passengerCount < eachChildDetail.length; passengerCount++) {
				String[] details = eachChildDetail[passengerCount].split(" ");
				childFirstNames[passengerCount] = details[0];
				childLastNames[passengerCount] = details[1];
				childGender[passengerCount] = details[2];
			}
			child(childCount, childFirstNames, childLastNames, childGender);
		}

		if (infantCount > 0) {
			String[] eachInfantDetail = InfantDetails.split(",");
			String[] infantFirstNames = new String[eachInfantDetail.length];
			String[] infantLastNames = new String[eachInfantDetail.length];
			String[] infantGender = new String[eachInfantDetail.length];
			String[] infantDOB = new String[eachInfantDetail.length];
			for (int passengerCount = 0; passengerCount < eachInfantDetail.length; passengerCount++) {
				String[] details = eachInfantDetail[passengerCount].split(" ");
				infantFirstNames[passengerCount] = details[0];
				infantLastNames[passengerCount] = details[1];
				infantGender[passengerCount] = details[2];
				infantDOB[passengerCount] = details[3];
			}
			infant(infantCount, infantFirstNames, infantLastNames, infantGender, infantDOB);
		}		
	}

	public void adult(int noOfAdults, String[] adultFirstName, String[] adultLastName, String[] adultGender)
			throws InterruptedException {

		for (int adultcount = 0; adultcount < noOfAdults; adultcount++) {
			elementFunction
					.ClickableElement(By.xpath("//div[@class='adultListWrapper']//button[text()='+ ADD NEW ADULT']"));
			waitFunction.explicitWaitforPresenceOfElement(By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (adultcount + 1) + "]"));

			elementFunction.textBoxToBeEntered(adultFirstName[adultcount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (adultcount + 1) + "]"));
			elementFunction.textBoxToBeEntered(adultLastName[adultcount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'Last Name')])["
							+ (adultcount + 1) + "]"));

			List<WebElement> applicableGender = driver
					.findElements(By.xpath("(//div[contains(@class,'adultListWrapper')]//div[@class='selectTab '])["
							+ (adultcount + 1) + "]//label"));
			aa: for (WebElement actualGender : applicableGender) {
				if (elementFunction.retriveText(actualGender).equalsIgnoreCase(adultGender[adultcount])) {
					elementFunction.ClickableElement(actualGender);
					break aa;
				}
			}
			Thread.sleep(1000);
		}
		totalTravellerCount += noOfAdults;
		String[] adultDetailHeader = elementFunction
				.retriveText(driver
						.findElement(By.xpath("(//div[@class='adultDetailsHeading']//font[@color='#4a4a4a'])[1]")))
				.split("/");
		verifyPassengerDetailsAdded(adultDetailHeader);
	}

	public void child(int noOfChild, String[] childFirstName, String[] childLastName, String[] childGender)
			throws InterruptedException {

		for (int childCount = 0; childCount < noOfChild; childCount++) {
			elementFunction
					.ClickableElement(By.xpath("//div[@class='adultListWrapper']//button[text()='+ ADD NEW CHILD']"));
			waitFunction.explicitWaitforPresenceOfElement(By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (childCount + 1 + totalTravellerCount) + "]"));

			elementFunction.textBoxToBeEntered(childFirstName[childCount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (childCount + 1 + totalTravellerCount) + "]"));
			elementFunction.textBoxToBeEntered(childLastName[childCount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'Last Name')])["
							+ (childCount + 1 + totalTravellerCount) + "]"));

			List<WebElement> applicableGender = driver
					.findElements(By.xpath("(//div[contains(@class,'adultListWrapper')]//div[@class='selectTab '])["
							+ (childCount + 1 + totalTravellerCount) + "]//label"));
			aa: for (WebElement actualGender : applicableGender) {
				if (elementFunction.retriveText(actualGender).equalsIgnoreCase(childGender[childCount])) {
					elementFunction.ClickableElement(actualGender);
					break aa;
				}
			}
			Thread.sleep(1000);
		}

		totalTravellerCount += noOfChild;
		String[] childDetailHeader = elementFunction
				.retriveText(driver
						.findElement(By.xpath("(//div[@class='adultDetailsHeading']//font[@color='#4a4a4a'])[2]")))
				.split("/");
		verifyPassengerDetailsAdded(childDetailHeader);
	}

	public void infant(int noOfInfant, String[] infantFirstName, String[] infantLastName, String[] infantGender,
			String[] infantDOB) throws InterruptedException {

		for (int infantCount = 0; infantCount < noOfInfant; infantCount++) {
			elementFunction
					.ClickableElement(By.xpath("//div[@class='adultListWrapper']//button[text()='+ ADD NEW INFANT']"));
			waitFunction.explicitWaitforPresenceOfElement(By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (infantCount + 1 + totalTravellerCount) + "]"));

			elementFunction.textBoxToBeEntered(infantFirstName[infantCount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'First & Middle Name')])["
							+ (infantCount + 1 + totalTravellerCount) + "]"));
			elementFunction.textBoxToBeEntered(infantLastName[infantCount], By.xpath(
					"(//div[contains(@class,'adultListWrapper')]//div//input[contains(@placeholder,'Last Name')])["
							+ (infantCount + 1 + totalTravellerCount) + "]"));

			List<WebElement> applicableGender = driver
					.findElements(By.xpath("(//div[contains(@class,'adultListWrapper')]//div[@class='selectTab '])["
							+ (infantCount + 1 + totalTravellerCount) + "]//label"));
			aa: for (WebElement actualGender : applicableGender) {
				if (elementFunction.retriveText(actualGender).equalsIgnoreCase(infantGender[infantCount])) {
					elementFunction.ClickableElement(actualGender);
					break aa;
				}
			}
			String[] DOB = infantDOB[infantCount].split("-");
			String birthDate = DOB[0];
			String birthMonth = DOB[1].substring(0, 3);
			String birthYear = DOB[2];

			WebElement dateField = driver.findElement(By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount + 1
					+ "]//div[contains(@class,'dropdown__control')])[1]"));
			WebElement monthField = driver.findElement(By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount
					+ 1 + "]//div[contains(@class,'dropdown__control')])[2]"));
			WebElement yearField = driver.findElement(By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount + 1
					+ "]//div[contains(@class,'dropdown__control')])[3]"));

			infantDOBSelection(birthDate, By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount + 1
					+ "]//div[contains(@class,'dropdown__control')])[1]"), dateField);
			infantDOBSelection(birthMonth, By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount + 1
					+ "]//div[contains(@class,'dropdown__control')])[2]"), monthField);
			infantDOBSelection(birthYear, By.xpath("((//div[@class='selectOptionGroup'])[" + infantCount + 1
					+ "]//div[contains(@class,'dropdown__control')])[3]"), yearField);

			Thread.sleep(1000);
		}
		totalTravellerCount += noOfInfant;
		String[] infantDetailHeader = elementFunction
				.retriveText(driver
						.findElement(By.xpath("(//div[@class='adultDetailsHeading']//font[@color='#4a4a4a'])[3]")))
				.split("/");
		verifyPassengerDetailsAdded(infantDetailHeader);
	}

	public void infantDOBSelection(String expectedFieldValue, By elementPath, WebElement DOBField) {
		elementFunction.ClickableElement(elementPath);

		waitFunction.explicitWaitForElementContainsAttibute(DOBField, "class",
				"dropdown__control dropdown__control--is-focused dropdown__control--menu-is-open css-1pahdxg-control");

		if (elementFunction.retriveAttributeValue(DOBField, "class").contains("dropdown__control--menu-is-open")) {
			waitFunction
					.explicitWaitForElementToBeClickable(By.xpath("(//div[contains(@id,'react-select-')])[last()]"));

			List<WebElement> applicableValues = driver.findElements(By.xpath("//div[contains(@id,'react-select-')]"));
			for (WebElement actualValue : applicableValues) {
				if (elementFunction.retriveText(actualValue).equalsIgnoreCase(expectedFieldValue)) {
					elementFunction.ClickableElement(actualValue);
					break;
				}
			}
		}
	}

	public void verifyPassengerDetailsAdded(String[] DetailHeader) {

		Assert.assertEquals(DetailHeader[0], DetailHeader[1]);
	}

}
