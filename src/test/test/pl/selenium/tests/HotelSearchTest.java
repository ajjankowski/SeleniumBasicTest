package pl.selenium.tests;

import org.junit.Test;
import org.testng.Assert;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.ResultsPage;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() throws IOException {
        setup();
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("17/04/2025", "20/04/2025")
                .setTravellers(1, 2)
                .performSearch().getHotelNames();

        System.out.println(hotelNames.size());
        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        tearDown();
    }

    @Test
    public void searchHotelWithoutNameTest() throws IOException {
        setup();
        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("17/04/2025", "20/04/2025")
                .setTravellers(0, 1)
                .performSearch();

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        tearDown();
    }

}
