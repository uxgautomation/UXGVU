package PageObjects;

import Common.TestHelper;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage {
    public static String xpathButtonRegistration = "//a[contains(@href,'register?')]";
    public static String xpathButtonLogin = "//a[contains(@href,'login?')]";

    public static String xpathMessageWelcome = "//*[text()='Hello, uxgautomation t.']";
    public static String xpathButtonMyProfile = "//a[@title='My profile']";

    @Step("Click on Log In button.")
    public static void clickOnlogInButton() {
        TestHelper.waitXpathElement(HomePage.xpathButtonLogin).click();
    }

    @Step("Click on My profile button.")
    public static void goToMyProfile() {
        TestHelper.waitXpathElement(HomePage.xpathButtonMyProfile).click();
    }
}
