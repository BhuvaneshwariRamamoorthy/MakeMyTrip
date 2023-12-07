package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonWaitFunctions.ExplicitWaitFunctions;
import CommonWebElementFunctions.*;

public class AddOns {
	
public static WebDriver driver;
WebElementFunctions elementFunction;
ExplicitWaitFunctions waitFunction;

	public AddOns(WebDriver driver)
	{
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}
	
	public void SelectionOfAddOns()
	{
		elementFunction.ClickableElement(By.xpath("//div[@id='ACKNOWLEDGE_SECTION']//button[text()='Proceed to pay']"));
		waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//span[text()='Submitting Details']"));

	}

	

}
