package Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import CommonWaitFunctions.*;
import CommonWebElementFunctions.*;

public class ReviewPage {

	public static WebDriver driver;
	WebElementFunctions elementFunction;
	ExplicitWaitFunctions waitFunction;

	public ReviewPage(WebDriver driver) {
		this.driver = driver;
		elementFunction = new WebElementFunctions(this.driver);
		waitFunction = new ExplicitWaitFunctions(this.driver);
	}

	public void editOrReviewPage() {

		elementFunction
				.ClickableElement(By.xpath("//div[@id='BILLING_ADDRESS']//following::button[text()='Continue']"));

		waitFunction.explicitWaitForElementToBeVisible(By.xpath("//div[contains(@class,'reviewDtlsOverlayContent')]"));
		elementFunction.ClickableElement(By.xpath("//div[contains(@class,'detailsPopupFooter')]//button"));
		waitFunction.explicitWaitForElementToBeInvisible(By.xpath("//span[text()='Submitting Details']"));
	}

}
