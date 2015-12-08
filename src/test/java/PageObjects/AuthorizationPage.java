package PageObjects;

import Common.TestHelper;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class AuthorizationPage {
    public static String validLogin = "uxgautomation@gmail.com";
    public static String validPassword = "Uxgpassword1";
    public static String validNewPassword = "Uxgpassword2";

    public static String xpathInputLogin = "//*[@id='edit-name']";
    public static String xpathInputPassword = "//*[@id='edit-pass']";
    public static String xpathSubmitButton = "//*[@id='edit-submit']";
    public static String xpathWrongCredentialsMessage = "//*[contains(text(),'unrecognized username or password')]";


    @Step("Fill Email input with valid value.")
    public static void fillInputLogin(String login) {
        TestHelper.waitXpathElement(AuthorizationPage.xpathInputLogin).sendKeys(login);
    }
    @Step("Fill Password input with valid value.")
    public static void fillInputPassword(String password) {
        TestHelper.waitXpathElement(AuthorizationPage.xpathInputPassword).sendKeys(password);
    }
    @Step("Click on Submit button.")
    public static void submitAuthorization() {
        TestHelper.waitXpathElement(AuthorizationPage.xpathSubmitButton).click();
    }
    @Step("Make sure that welcome message appeared.")
    public static void verifyWelcomeMessage() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(HomePage.xpathMessageWelcome).isDisplayed());
    }

    @Step("Pass authorization with valid credentials.")
    public static void logIn() {
        HomePage.clickOnLogInButton();
        fillInputLogin(validLogin);
        fillInputPassword(validPassword);
        submitAuthorization();
        checkErrorMessage();
    }
    @Step("Previous password was wrong, picked up right.")
    public static void checkErrorMessage() {
        if(TestHelper.waitXpathElement(xpathWrongCredentialsMessage).isDisplayed()) {
            fillInputPassword(validNewPassword);
            submitAuthorization();
        }
    }
}
