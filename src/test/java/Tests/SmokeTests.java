package Tests;

import Common.TestHelper;
import Steps.SmokeSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Arrays;
import java.util.Collection;

@Title("Smoke Tests Suite")
@Description("This suite's tests covers the most critical functionality of the testable product. " +
        "Any failures which is found by this suite's tests is critical.")
@RunWith(Parameterized.class)
public class SmokeTests {
    @Before
    public void setUp() {
        TestHelper.localDriverFullscreen(webdriver);
    }
    @After
    public void tearDown() {
        TestHelper.quit();
    }

    @Features("Authorization")
    @Stories("User should be able to create new account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToCreateAccount() {

    }

    @Features("Authorization")
    @Stories("User should be able to log in via existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToLogIn() {

    }

    @Features("Authorization")
    @Stories("User should be able to change password of existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToChangePassword() {

    }

    @Features("Article")
    @Stories("User should be able to leave comments in articles.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToLeaveComments() {

    }

    @Features("Profile")
    @Stories("User should be able to overview loves section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToOverviewLovesSection() {

    }

    @Features("Profile")
    @Stories("User should be able to see comments section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToOverviewCommentsSection() {

    }

    @Features("Profile")
    @Stories("User should be able to delete comments in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Ignore
    public void userShouldBeAbleToDeleteComments() {

    }


    SmokeSteps user = new SmokeSteps();

    private String webdriver;
    private int width;
    private int height;

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                {"firefox"},
                {"chrome"}
        });
    }

    public SmokeTests(String webdriver, int width, int height) {
        this.webdriver = webdriver;
        this.width = width;
        this.height = height;
    }
}
