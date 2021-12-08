package WebTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        setup();

        //City pick
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        //Dates by input
        driver.findElement(By.name("checkin")).sendKeys("17/04/2025");
        driver.findElement(By.name("checkout")).sendKeys("20/04/2025");

//        //Dates by calendar click
//        driver.findElement(By.name("checkin")).click();
//        driver.findElement(By.xpath("//td[@class='day ' and text()='29']")).click();
//        driver.findElement(By.name("checkout")).click();
//        driver.findElement(By.xpath("//td[@class='day ' and text()='30']")).click();

        //Travelers pick
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        //Hotel names
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        //Get Hotel list
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        //Print Hotel list
        System.out.println(hotelNames.size());
        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        tearDown();
    }

    @Test
    public void searchHotelWithoutNameTest() {
        setup();

        driver.findElement(By.name("checkin")).sendKeys("17/04/2025");
        driver.findElement(By.name("checkout")).sendKeys("20/04/2025");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        String noResultHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']")).getText();
        Assert.assertEquals(noResultHeading, "No Results Found");
        tearDown();
    }
}

