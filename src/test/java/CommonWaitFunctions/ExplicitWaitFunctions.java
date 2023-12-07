package CommonWaitFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitFunctions {

	public static WebDriver driver;

	public ExplicitWaitFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void explicitWaitForElementToBeClickable(WebElement clickableElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(clickableElement));
	}

	public void explicitWaitForElementToBeClickable(By clickableElementLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(clickableElementLocator));
	}

	public void explicitWaitForElementToBeVisible(By visiblityElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visiblityElement));
	}

	public void explicitWaitForElementToBeVisible(WebElement visiblityElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(visiblityElement));
	}

	public void explicitWaitForTextToBeInElement(By Element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.textToBe(Element, text));
	}

	public void explicitWaitforPresenceOfElement(By Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(Element));
	}

	public void explicitWaitForElementContainsAttibute(WebElement Element, String Attribute, String AttributeValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.attributeContains(Element, Attribute, AttributeValue));
	}
	
	public void explicitWaitForElementToBeInvisible(By Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Element));
	}

}
