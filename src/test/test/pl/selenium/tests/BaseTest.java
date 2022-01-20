package pl.selenium.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pl.selenium.utils.DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReports;

    @BeforeClass
    public static void beforeSuite() {
        htmlReporter = new ExtentHtmlReporter("TestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterClass
    public static void afterSuite() {
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() throws IOException {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        driver = DriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
