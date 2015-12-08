package PageObjects;

import Common.TestHelper;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class AuthorizationPage {
    public static String xpathInputLogin = "//*[@id='edit-name']";
    public static String xpathInputPassword = "//*[@id='edit-pass']";
    public static String xpathSubmitButton = "//*[@id='edit-submit']";

    @Step("Fill Email input with valid value.")
    public static void fillInputLogin() {
        TestHelper.waitXpathElement(AuthorizationPage.xpathInputLogin).sendKeys("uxgautomation@gmail.com");
    }
    @Step("Fill Password input with valid value.")
    public static void fillInputPassword() {
        TestHelper.waitXpathElement(AuthorizationPage.xpathInputPassword).sendKeys("Uxgpassword1");
    }
    @Step("Click on Submit button.")
    public static void submitAuthorization() {
        TestHelper.waitXpathElement(AuthorizationPage.xpathSubmitButton).click();
    }
    @Step("Make sure that welcome message appeared.")
    public static void seesWelcomeAuthorizedUser() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(HomePage.xpathMessageWelcome).isDisplayed());
    }
}
