package WebTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SignUp {

    @Test
    public void signUp() {
        //False: visible test execution; True: Invisible tests execution
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        //Open Sigh Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        //Fill form
        String lastName = "Kowalski";
        driver.findElement(By.name("firstname")).sendKeys("Jan");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys("jan.kowalski6@test.pl");
        driver.findElement(By.name("password")).sendKeys("passWord12!");
        driver.findElement(By.name("confirmpassword")).sendKeys("passWord12!");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        String heading = driver.findElement(By.xpath("//h3[@class='RTL']")).getText();
        Assert.assertTrue(heading.contains(lastName));
        Assert.assertEquals(heading, "Hi, Jan Kowalski");

//        driver.quit();

    }

}

























