package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import BrowserDriver.BrowserLaunch;
import Pages.AddOns;
import Pages.BookingDetails;
import Pages.FlightSummary;
import Pages.PincodeAndStateDetails;
import Pages.ReviewPage;
import Pages.SearchPage;
import Pages.SearchResultPage;
import Pages.SeatMealSelection;
import Pages.TravellerDetailsSelection;
import TestCases.MakeMyTripTestData;
import Utils.PropertyFileRead;
import CommonWebElementFunctions.*;

public class FlightSearch extends BrowserLaunch {

	WebElementFunctions elementFunction;
	SearchPage s;
	SearchResultPage sp;
	FlightSummary fs;
	TravellerDetailsSelection tds;
	BookingDetails bd;
	PincodeAndStateDetails pd;
	ReviewPage rp;
	SeatMealSelection sm;
	AddOns add;
	int adultCount,childCount,infantCount = 0;

	@BeforeSuite
	public void LaunchTheBrowser() throws IOException {
		launchBrowser();
	}

	@BeforeTest
	public void OpenTheApplication() throws IOException {
		String applicationURL = PropertyFileRead.GetEnvironmentDetail().getProperty("url");
		driver.get(applicationURL);
		elementFunction = new WebElementFunctions(this.driver);
		s = new SearchPage(this.driver);
		sp = new SearchResultPage(this.driver);
		fs = new FlightSummary(this.driver);
		tds = new TravellerDetailsSelection(driver);
		bd = new BookingDetails(driver);
		pd = new PincodeAndStateDetails(this.driver);
		rp = new ReviewPage(this.driver);
		sm = new SeatMealSelection(this.driver);
		add = new AddOns(this.driver);
	}

	@BeforeClass
	public void deleteAdds() throws InterruptedException {
		Thread.sleep(3000);
		// elementFunction.ClickIfAdExist("//*[@data-cy='closeModal']");
		// Thread.sleep(5000);
		elementFunction.ClickIfAdExist("//*[@class='close']");
		Thread.sleep(2000);
		//elementFunction.CloseCreateAccountPopup(driver.findElement(By.xpath("//*[@data-cy='closeModal']")));
		//Thread.sleep(2000);
	}

	@Test(dataProvider="GetValidSearchTestData",dataProviderClass=MakeMyTripTestData.class)
	public void SearchWithValidValues(String fromCity,String toCity,String departureDate,String travellerCount,String flightType) {

		/*
		 * Click on From Location 
		 * Click on To Location 
		 * Select the Departure Date
		 * Click on search Button 
		 * Click Ok in the upcoming popup 
		 * Get the search result text
		 * and validate with the input
		 */
		
		s.SelectFromLocation(fromCity);
		s.SelectToLocation(toCity);
		s.SelectDepatureDate(departureDate);
		String[] travellercounts = travellerCount.split(",");
		adultCount=Integer.valueOf(travellercounts[0]);
		childCount = Integer.valueOf(travellercounts[1]);
		infantCount = Integer.valueOf(travellercounts[2]);
		s.travellerDetails(adultCount,childCount,infantCount,flightType);
		String FromCityName = s.GetFromLocation();
		String ToCityName = s.GetToLocation();
		s.ClickOnSearchButton();
		sp.WaitAndClickOnPopup();
		AssertJUnit.assertEquals(sp.GetActualSearchText(), sp.ExpectedSearchText(FromCityName, ToCityName));

	}

	@Test(dependsOnMethods = "SearchWithValidValues")
	public void bookAFlight() {

		/*
		 * After successful search, Click on 1st View Price dropdown Select 1st Book
		 * Now button from the dropdown values
		 * And Complete your Booking Page is opened
		 */
		String bookingHeader = sp.BookAFlight();
		AssertJUnit.assertEquals(bookingHeader, "Complete your booking");

	}

	@Test(dependsOnMethods = "bookAFlight",dataProvider = "GetTripSecureOption",dataProviderClass =MakeMyTripTestData.class )
	public void selectTripSecureOption(String secureOption) {

		/*
		 * After successfull booking of flight, Under Trip Secure Heading select any
		 * option
		 */
		boolean tripSecureMessagereceived = fs.tripSecure(secureOption);
		AssertJUnit.assertEquals(tripSecureMessagereceived,true);
	}

	@Test(dependsOnMethods = "selectTripSecureOption",dataProvider="GetPassengerDetails",dataProviderClass = MakeMyTripTestData.class)
	public void ProvidePassengerDetails(String adultDetails,String ChildDetails, String InfantDetails) throws InterruptedException {

		/*
		 * Providing passenger Details Enter FirstName,LastName and Gender of
		 * adult,Child and Infant along with other info if required
		 */
		tds.addPassengerDetails(adultCount,childCount,infantCount,adultDetails,ChildDetails,InfantDetails);
		
	}

	@Test(dependsOnMethods = "ProvidePassengerDetails",dataProvider="GetInvoiceDetails",dataProviderClass = MakeMyTripTestData.class)
	public void ProvideInvoiceDetails(String countryCode,String mobileNumber,String emailID,String Pincode, String state,String address) throws InterruptedException {
		/*
		 * Select country Code from the dropdown value
		 * Enter mobile number and Email id with valid extension
		 * Enter pincode 
		 * Select State from the dropdown values
		 * Enter address
		 * Click On continue button
		 * Review or confirmation popup will be displayed 
		 * Select confirm button in review page
		 */
		
		bd.bookingDetailsSender(countryCode, mobileNumber, emailID);
		pd.invoiceDetails(Pincode, state, address);
		test.log(LogStatus.INFO, test.addScreenCapture(elementFunction.takeScreenshot()));
		rp.editOrReviewPage();
	}

	@Test(dependsOnMethods = "ProvideInvoiceDetails")
	public void SelectionOfSeatAndMeal() throws InterruptedException {
		/*
		 * Check whether seat and meal selection option is applicable
		 * If yes, select seat and meal for total no of passenger provided
		 */
		sm.availableMenu(adultCount+childCount);
	}

	@Test(dependsOnMethods = "SelectionOfSeatAndMeal")
	public void SelectAddOnOptions() {
		/*
		 * Click on Proceed to Pay button in add on section
		 */
		add.SelectionOfAddOns();
	}

	@AfterMethod
	public void GenerateReport(ITestResult result) {
		if (result.getStatus() == 1) {
			test.log(LogStatus.INFO, test.addScreenCapture(elementFunction.takeScreenshot()));
			test.log(LogStatus.PASS, result.getMethod().getMethodName() + " Test Passed");
		} else if (result.getStatus() == 2) {
			test.log(LogStatus.INFO, test.addScreenCapture(elementFunction.takeScreenshot()));
			test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "Test Case Failed");
		} else if (result.getStatus() == 3) {
			test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "Test Case Skipped");
		}
	}

	@AfterSuite
	public void CloseTheApplication() throws IOException {
		closeBrowser();
	}
}
