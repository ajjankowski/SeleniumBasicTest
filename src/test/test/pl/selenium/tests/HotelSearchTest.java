package pl.selenium.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.ResultsPage;
import pl.selenium.utils.ExcelReader;
import pl.selenium.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    // Create report with screenshots
    @Test
    public void searchHotelTest() throws IOException {
        beforeClass();
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        setup();
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("17/04/2025", "20/04/2025");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch().getHotelNames();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.PASS, "Screenshot", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
        afterClass();
        tearDown();
    }

    @Test
    public void searchHotelWithoutNameTest() throws IOException {
        beforeClass();
        setup();
        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("17/04/2025", "20/04/2025")
                .setTravellers(0, 1)
                .performSearch();

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        tearDown();
    }

    @Test
    public void searchHotelTestWithDataProvider() throws IOException {
        setup();
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("17/04/2025", "20/04/2025")
                .setTravellers(1, 2)
                .performSearch().getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        tearDown();
    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}

