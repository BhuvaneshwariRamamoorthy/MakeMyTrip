package Pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonWaitFunctions.*;

import CommonWebElementFunctions.*;

public class SearchResultPage {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public void WaitAndClickOnPopup() {
		waitFunction.explicitWaitForElementToBeInvisible(By.xpath("/div[@class='fillingLoader']"));
		try {
			elementFunction.ClickableElement(By.xpath("//span[contains(@class,'overlayCrossIcon')]"));
		} catch (Exception e) {
		
		}
	}

	public String GetActualSearchText() {
		return elementFunction.retriveText(By.xpath("//*[contains(@class,'journey-title')]"));
	}

	public String ExpectedSearchText(String fromCityName, String toCityName) {
		return "Flights from " + fromCityName + " to " + toCityName;
	}

	public String BookAFlight() {
		String bookingHeader = null;
		List<WebElement> viewPriceButton = driver.findElements(By.xpath("(//span[text()='View Prices'])[1]"));
		if(viewPriceButton.size()>0)
		{
			aa: for(WebElement viewPrice:viewPriceButton)
			{
				elementFunction.ClickableElement(viewPrice);
				elementFunction.ClickableElement(By.xpath("(//div//button[text()='Book Now'])[1]"));
				break aa;
			}
		}
		
		List<WebElement> bookNowButton = driver.findElements(By.xpath("(//span[text()='BOOK NOW'])[1]"));
		if(bookNowButton.size()>0)
		{
			aa: for(WebElement bookNow:bookNowButton)
			{
				elementFunction.ClickableElement(bookNow);
				elementFunction.ClickableElement(By.xpath("(//div[@class='glider-contain']//button[text()='BOOK NOW'])[1]"));
				break aa;
			}
		}
		String HomePage = driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			if (!childWindow.equals(HomePage)) {
				driver.switchTo().window(childWindow);
				driver.manage().window().maximize();
				waitFunction.explicitWaitForElementToBeVisible(By.xpath("//div[@class='pageHeader']//h2"));
				bookingHeader = elementFunction.retriveText(driver.findElement(By.xpath("//div[@class='pageHeader']//h2")));
				waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//div[@class='fillingLoader']"));
				/*List<WebElement> expectedBookingHeader = driver
						.findElements(By.xpath("//div[@class='pageHeader']//h2"));

				if (expectedBookingHeader.size() > 0) {
					WebElement actualBookingHeader = driver.findElement(By.xpath("//div[@class='pageHeader']//h2"));

					if (elementFunction.retriveText(actualBookingHeader).equalsIgnoreCase("Complete your booking")) {
						System.out.println("Booking process is inprogress");
						waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//div[@class='fillingLoader']"));
					} else
						System.out.println("Error Occured");
				}*/
			}
		}
		return bookingHeader;
	}

}
