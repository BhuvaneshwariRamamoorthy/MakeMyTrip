package BrowserDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utils.PropertyFileRead;

public class BrowserLaunch extends PropertyFileRead {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;

	public void launchBrowser() throws IOException {
		String browserName = GetEnvironmentDetail().getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browserName.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browserName.equalsIgnoreCase("FireFox"))
			driver = new FirefoxDriver();
		else if (browserName.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();
		else if (browserName.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else
			driver = null;

		driver.manage().window().maximize();
		startReport();
	}

	public void startReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\Result.html", true);
		File file = new File(System.getProperty("user.dir")+"\\EnvironmentDetails\\extent-config.xml");
		report.loadConfig(file);
		test = report.startTest("MakeMyTrip End to End Test Report");
	}

	public void closeBrowser() {
		report.flush();
		driver.quit();
	}

}
