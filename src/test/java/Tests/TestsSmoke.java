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

@Title("Smoke Test Suite")
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

    @BeforeClass
    public static void setPrecondition() {
        TestHelper.localDriverFullscreenAndGo("chrome","https://test2.vocalpoint.com/articles");
        ArticlePage.goToTheFirstAvailableArticle();
        Environments.getGrabbedArticleUrl();
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.setDefaultPassword();
        TestHelper.quit();
    }
    @AfterClass
    public static void setPostcondition() {

    }
    @Title("Launch web driver.")
    @Before
    public void setUp() {
        TestHelper.localDriverFullscreen(webdriver);
    }
    @Title("Quit web driver.")
    @After
    public void tearDown() {
        TestHelper.quit();
    }

    @Features("Authorization and Registration")
    @Stories("User should be able to log in via existed account.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void userShouldBeAbleToLogIn() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        HomePage.clickOnLogInButton();
        ProfilePage.setRightPreconditionPasswordAndLogIn();
    }

    @Features("Articles")
    @Stories("User should be able to leave comments in articles.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void a_AbilityToAddComments() {
        Environments.goTo(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.fillTextAreaByComment();
        ArticlePage.clickOnPostMyCommentButton();
        ArticlePage.verifyThatCommenIsAdded();
    }

    @Features("Comments")
    @Stories("User should be able to see comments section in account profile.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void b_VerifyCommentsSection() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToCommentsSection();
        ProfilePage.verifyThatCommentInSectionIsDisplayed();
    }

    @Features("Comments")
    @Stories("User should be able to delete comments in account profile.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void c_VerifyDeletingComments() {
        Environments.goTo(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToCommentsSection();
        ProfilePage.deleteComment();
        ProfilePage.confirmDeleteComment();
        ProfilePage.verifyThatCommentIsDeleted();
    }

    @Features("Loves")
    @Stories("User should be able to add loves in articles.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void a_AbilityToAddLoves() {
        Environments.goTo(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.loveArticle();
        ArticlePage.verifyThatLovesCounterIsIncreased();
    }

    @Features("Loves")
    @Stories("User should be able to unlove in article.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void b_AbilityToRemoveLoveInArticle() {
        Environments.goTo(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.unLoveArticle();
        ArticlePage.verifyThatLovesCounterIsDecreased();
        ArticlePage.loveArticle();
    }

    @Features("Loves")
    @Stories("User should be able to overview loves section in account profile.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void c_VerifyLoveSection() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToLovesSection();
        ProfilePage.verifyLoveInLovesSection();
    }

    @Features("Loves")
    @Stories("User should be able to delete love in loves section of account profile.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void d_VerifyDeletingLove() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToLovesSection();
        ProfilePage.deleteLoveInProfile();
        ProfilePage.confirmDeleteLove();
        ProfilePage.verifyLoveDeleted();
    }

    @Features("Reset Password")
    @Stories("User should be able to change password of existed account.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void y_resetPasswordVerification() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.fillNewPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.fillConfirmNewPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.submitResetPasswordForm();
        ProfilePage.verifyThatConfirmationMessageAppears();
    }

    @Features("Reset Password")
    @Stories("User should be able to log in with new password value after password reset.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void z_logInAfterResetPassword() {
        Environments.goTo(Environments.TEST_VOCALPOINT_BASE_URL);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.fillNewPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.fillConfirmNewPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.submitResetPasswordForm();
        ProfilePage.verifyThatConfirmationMessageAppears();
    }
}
