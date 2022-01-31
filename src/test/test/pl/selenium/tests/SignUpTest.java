package pl.selenium.tests;

import org.junit.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.LoggedUserPage;
import pl.selenium.pages.SignUpPage;

import java.io.IOException;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() throws IOException {
        setup();
        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Jan")
                .setLastName("Kowalski")
                .setPhone("123456789")
                .setEmail("test" + randomNumber + "@test.pl")
                .setPassword("passWord12")
                .confirmPassword("passWord12")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains("Kowalski"));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Jan Kowalski");
        tearDown();
    }

    @Test
    public void signUpEmptyFormTest() throws IOException {
        setup();
        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.signUp();

        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
        tearDown();
    }

    @Test
    public void signUpInvalidEmailTest() throws IOException {
        setup();
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Jan")
                .setLastName("Kowalski")
                .setPhone("123456789")
                .setEmail("email")
                .setPassword("passWord12")
                .confirmPassword("passWord12");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
        tearDown();
    }
}

























