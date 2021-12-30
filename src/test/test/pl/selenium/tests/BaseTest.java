package pl.selenium.tests;

import org.openqa.selenium.WebDriver;
import pl.selenium.utils.DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    public void setup() throws IOException {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        driver = DriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    public void tearDown() {
        driver.quit();
    }

}
