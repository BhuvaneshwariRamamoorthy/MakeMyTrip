package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import CommonWaitFunctions.*;
import CommonWebElementFunctions.*;

public class SeatMealSelection {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public SeatMealSelection(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public void availableMenu(int totalNoOfPassenger) throws InterruptedException {

		waitFunction.explicitWaitForElementToBeClickable(By.xpath("(//div[@class='ancillaryHeader']//ul//li)[last()]"));
		List<WebElement> allTabsAvailable = driver.findElements(By.xpath("//div[@class='ancillaryHeader']//ul//li"));
		if (allTabsAvailable.size() > 0) {
			for (WebElement eachTab : allTabsAvailable) {
				if (eachTab.getText().equalsIgnoreCase("Seats")) {
					seatSelection(totalNoOfPassenger);
				}
				if (eachTab.getText().equalsIgnoreCase("Meals")) {
					mealSelection(totalNoOfPassenger);
				}
			}
			elementFunction.ClickableElement(By.xpath("//form[@id='mainSection_1']//div//button[text()='Continue']"));
			waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//span[text()='Submitting Details']"));
		}
	}

	public void seatSelection(int totalNoOfPassenger) throws InterruptedException
	{
		waitFunction.explicitWaitForElementToBeClickable(By.xpath("//div[@class='ancillaryContainer']//ul[@class='ancillaryTabs']//li//span[text()='Seats']"));
		List<WebElement> flightDivider = driver.findElements(By.xpath("//div[contains(@class,'scrollItem customScroll seats')]"));
		if(flightDivider.size()>0)
		{
			for(int ConnectingFlightCount=1;ConnectingFlightCount<=flightDivider.size();ConnectingFlightCount++)
			{
				for(int passengerCount=1;passengerCount<=totalNoOfPassenger;passengerCount++)
				{
					List<WebElement> allAvailableSeats = driver.findElements(By.xpath("//div[contains(@class,'scrollItem customScroll seats')]["+ConnectingFlightCount+"]//div[@class='seatBlock pointer']//ancestor::div[@class='seatCol']"));
					aa: for (WebElement availableSeats : allAvailableSeats) 
					{
						elementFunction.ClickableElement(availableSeats);
						waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//span[text()='Submitting Details']"));
						break aa;
					}
				}
				SelectionValidation(By.xpath("(//div[@class='scrollItem customScroll seats'])["+ConnectingFlightCount+"]//div[@class='seatHeader']//p[2]"));
				if ((ConnectingFlightCount!=flightDivider.size())&&driver.findElement(By.xpath("//button[@class='sliderNextBtn']")).isEnabled()) 
				{
					elementFunction.ClickableElement(By.xpath("//button[@class='sliderNextBtn']"));
				}
				Thread.sleep(1000);
			}
		}
	}

	public void mealSelection(int totalNoOfPassenger) throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)", "");
		
		waitFunction.explicitWaitForElementToBeClickable(By.xpath("//div[@class='ancillaryContainer']//ul[@class='ancillaryTabs']//span[text()='Meals']//parent::li"));

		elementFunction.ClickableElement(By.xpath("//div[@class='ancillaryContainer']//ul[@class='ancillaryTabs']//span[text()='Meals']//parent::li"));
		
		List<WebElement> mealDivider = driver.findElements(By.xpath("//div[@class='scrollItem customScroll']"));
		
		if(mealDivider.size()>0)
		{
			for(int ConnectingFlightCount=1;ConnectingFlightCount<=mealDivider.size();ConnectingFlightCount++)
			{
				for(int passengerCount=1;passengerCount<=totalNoOfPassenger;passengerCount++)
				{
					List<WebElement> allAvailableMeals = driver.findElements(By.xpath("(//div[@class='mealsHeader'])["+ ConnectingFlightCount+"]//parent::div//ul[@class='mealList ']//li//div//button"));
					aa: for (WebElement availableMeal : allAvailableMeals)
					{
						elementFunction.ClickableElement(availableMeal);
						waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//span[text()='Submitting Details']"));
						break aa;
					}
				}
				SelectionValidation(By.xpath("(//div[@class='scrollItem customScroll seats'])["+ConnectingFlightCount+"]//div[@class='mealsHeader']//p[2]"));
				if ((ConnectingFlightCount!=mealDivider.size())&&driver.findElement(By.xpath("//button[@class='sliderNextBtn']")).isEnabled()) 
				{
					elementFunction.ClickableElement(By.xpath("//button[@class='sliderNextBtn']"));
				}
				Thread.sleep(1000);
			}
		}
	}
	
	public void SelectionValidation(By ElementPath)
	{
		List<WebElement> BookingHeaders = driver.findElements(ElementPath);
		for(WebElement bookingHeader: BookingHeaders)
		{
			String[] SelectionHeader = elementFunction.retriveText(bookingHeader).substring(0,6).split(" of");
			Assert.assertEquals(" "+SelectionHeader[0], SelectionHeader[1]);
		}
	}

}
