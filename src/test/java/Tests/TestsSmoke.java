package Tests;

import Common.TestHelper;
import PageObjects.ArticlePage;
import PageObjects.AuthorizationPage;
import Common.Environments;
import PageObjects.HomePage;
import PageObjects.ProfilePage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Arrays;
import java.util.Collection;

@Title("Smoke Tests Suite")
@Description("This suite's tests covers the most critical functionality of the testable product. " +
        "Any failures which is found by this suite's tests is critical.")
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsSmoke {
    private String webdriver;

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                {"chrome"}
        });
    }

    public TestsSmoke(String webdriver) {
        this.webdriver = webdriver;
    }

    @Before
    public void setUp() {
        TestHelper.localDriverFullscreen(webdriver);
    }
    @After
    public void tearDown() {
        TestHelper.quit();
    }
    @AfterClass
    public static void postcondition() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput("Uxgpassword2");
        ProfilePage.fillNewPasswordInput("Uxgpassword1");
        ProfilePage.fillConfirmNewPasswordInput("Uxgpassword1");
        ProfilePage.submitResetPasswordForm();
        ProfilePage.checkThatConfirmationMessageAppears();
    }

    @Features("Authorization and Registration")
    @Stories("User should be able to log in via existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void userShouldBeAbleToCreateAccount() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        AuthorizationPage.seesWelcomeAuthorizedUser();
    }

    @Features("Reset Password")
    @Stories("User should be able to change password of existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void resetPasswordVerification() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput("Uxgpassword1");
        ProfilePage.fillNewPasswordInput("Uxgpassword2");
        ProfilePage.fillConfirmNewPasswordInput("Uxgpassword2");
        ProfilePage.submitResetPasswordForm();
        ProfilePage.checkThatConfirmationMessageAppears();
    }

    @Features("Articles")
    @Stories("User should be able to leave comments in articles.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void a_AbilityToAddComments() {
        Environments.goToVocalpointTestEnvironment("/articles");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        ArticlePage.goToTheFirstAvailableArticle();
        ArticlePage.fillTextAreaByComment();
        ArticlePage.clickOnPostMyCommentButton();
        ArticlePage.verifyThatCommenIsAdded();
    }

    @Features("Loves")
    @Stories("User should be able to add loves in articles.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void a_AbilityToAddLoves() {
        Environments.goToVocalpointTestEnvironment("/articles");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        ArticlePage.goToTheFirstAvailableArticle();
        ArticlePage.loveArticle();
        ArticlePage.verifyThatLovesCounterIsUpdated();
    }

    @Features("Loves")
    @Stories("User should be able to overview loves section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void b_VerifyLoveSection() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.goToMyProfile();
        ProfilePage.goToLovesSection();
        ProfilePage.verifyLoveInLovesSection();
    }

    @Features("Comments")
    @Stories("User should be able to see comments section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void b_VerifyCommentsSection() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.goToMyProfile();
        ProfilePage.goToCommentsSection();
        ProfilePage.verifyThatCommentSectionIsOpened();
    }

    @Features("Comments")
    @Stories("User should be able to delete comments in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void c_VerifyDeletingComments() {
        Environments.goToVocalpointTestEnvironment("/articles");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin();
        AuthorizationPage.fillInputPassword();
        AuthorizationPage.submitAuthorization();
        HomePage.goToMyProfile();
        ProfilePage.deleteComment();
        ProfilePage.confirmDeleteComment();
        ProfilePage.verifyThatCommentIsDeleted();
    }
}
