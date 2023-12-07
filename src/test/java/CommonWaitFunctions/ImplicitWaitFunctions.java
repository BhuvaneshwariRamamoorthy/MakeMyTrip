package CommonWaitFunctions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class ImplicitWaitFunctions {
	
	public static void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
}
