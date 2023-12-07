package TestCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import Utils.MakeMyTripExcelreaddata;

public class MakeMyTripTestData {

	@DataProvider
	public Object[][] GetValidSearchTestData() throws IOException
	{
		return MakeMyTripExcelreaddata.readData("SampleData.xlsx","Search");
	}

	@DataProvider
	public Object[][] GetTripSecureOption() throws IOException
	{
		return MakeMyTripExcelreaddata.readData("SampleData.xlsx","TripSecure");
	}
	
	@DataProvider
	public Object[][] GetPassengerDetails() throws IOException
	{
		return MakeMyTripExcelreaddata.readData("SampleData.xlsx","PassengerDetail");
	}
	
	@DataProvider
	public Object[][] GetInvoiceDetails() throws IOException
	{
		return MakeMyTripExcelreaddata.readData("SampleData.xlsx","InvoiceDetails");
	}
}
