package CommonWebElementFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonWaitFunctions.ExplicitWaitFunctions;

public class WebElementFunctions {

	public static WebDriver driver;
	ExplicitWaitFunctions waitFunction;

	public WebElementFunctions(WebDriver driver) {
		this.driver = driver;
		waitFunction = new ExplicitWaitFunctions(driver);
	}

	public void textBoxToBeEntered(String text, WebElement textBox) {
		waitFunction.explicitWaitForElementToBeVisible(textBox);
		if (textBox.isDisplayed()) {
			textBox.clear();
			textBox.sendKeys(text);
		}
	}

	public void textBoxToBeEntered(String text, By textBoxPath) {
		waitFunction.explicitWaitforPresenceOfElement(textBoxPath);
		WebElement textBox = driver.findElement(textBoxPath);
		if (textBox.isDisplayed()) {
			textBox.clear();
			textBox.sendKeys(text);
		}
	}

	public void ClickableElement(WebElement Element) {
		waitFunction.explicitWaitForElementToBeClickable(Element);
		if (Element.isDisplayed()) {
			Element.click();
		}
	}

	public void ClickableElement(By Element) {
		waitFunction.explicitWaitForElementToBeClickable(Element);
		WebElement clickElement = driver.findElement(Element);
		if (clickElement.isDisplayed()) {
			clickElement.click();
		}
	}

	public String retriveAttributeValue(By elementToBeRetrived, String attributeValue) {
		return driver.findElement(elementToBeRetrived).getAttribute(attributeValue);
	}

	public String retriveAttributeValue(WebElement elementToBeRetrived, String attributeValue) {
		return elementToBeRetrived.getAttribute(attributeValue);
	}

	public String retriveText(By elementToBeRetrived) {
		return driver.findElement(elementToBeRetrived).getText();
	}

	public String retriveText(WebElement elementToBeRetrived) {
		return elementToBeRetrived.getText();
	}

	public void ClickIfAdExist(String Elementpath) {
		List<WebElement> allframeelement = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < allframeelement.size(); i++) {
			driver.switchTo().frame(i);
			List<WebElement> AllElements = driver.findElements(By.xpath(Elementpath));
			if (AllElements.size() > 0) {
				for (WebElement eachElement : AllElements) {

					eachElement.click();
					driver.switchTo().defaultContent();
					break;
				}
			} else {
				driver.switchTo().defaultContent();
			}
		}
	}

	public void ClickOnAddClose() {
		try {
			waitFunction.explicitWaitForElementToBeClickable(By.xpath("//*[@class='ic_circularclose_grey']"));
			driver.findElement(By.xpath("//*[@class='ic_circularclose_grey']")).click();
		} catch (Exception e) {

		}
	}

	public void CloseCreateAccountPopup(WebElement buttonElement) {
		try {
			if (buttonElement.isEnabled())
				buttonElement.click();
		} catch (Exception e) {

		}
	}

	public String takeScreenshot() {
		Date date = new Date();
		long millisec = date.getTime();
		String filename = String.valueOf(millisec);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir") + "//Screenshot/" + filename + ".png");

		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {

		}
		return destinationFile.toString();

	}
}
