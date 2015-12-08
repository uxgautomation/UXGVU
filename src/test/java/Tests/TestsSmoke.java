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

    @Title("Set required test suite preconditions.")
    @BeforeClass
    public static void precondition() {
        Environments.goToVocalpointTestEnvironment("/articles");
        ArticlePage.goToTheFirstAvailableArticle();
        Environments.getGrabbedArticleUrl();
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
    @Title("Set required test suite postconditions.")
    @AfterClass
    public static void postcondition() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin(AuthorizationPage.validLogin);
        AuthorizationPage.fillInputPassword(AuthorizationPage.validNewPassword);
        AuthorizationPage.submitAuthorization();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.fillNewPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.fillConfirmNewPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.submitResetPasswordForm();
        ProfilePage.checkThatConfirmationMessageAppears();
    }

    @Features("Authorization and Registration")
    @Stories("User should be able to log in via existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void userShouldBeAbleToLogIn() {
        Environments.goToVocalpointTestEnvironment("/");
        HomePage.clickOnlogInButton();
        AuthorizationPage.fillInputLogin(AuthorizationPage.validLogin);
        AuthorizationPage.fillInputPassword(AuthorizationPage.validPassword);
        AuthorizationPage.submitAuthorization();
        AuthorizationPage.seesWelcomeAuthorizedUser();
    }

    @Features("Articles")
    @Stories("User should be able to leave comments in articles.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void a_AbilityToAddComments() {
        Environments.goToVocalpointTestEnvironment(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.fillTextAreaByComment();
        ArticlePage.clickOnPostMyCommentButton();
        ArticlePage.verifyThatCommenIsAdded();
    }

    @Features("Comments")
    @Stories("User should be able to see comments section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void b_VerifyCommentsSection() {
        Environments.goToVocalpointTestEnvironment("/");
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToCommentsSection();
        ProfilePage.verifyThatCommentSectionIsOpened();
    }

    @Features("Comments")
    @Stories("User should be able to delete comments in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void c_VerifyDeletingComments() {
        Environments.goToVocalpointTestEnvironment(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.deleteComment();
        ProfilePage.confirmDeleteComment();
        ProfilePage.verifyThatCommentIsDeleted();
    }

    @Features("Loves")
    @Stories("User should be able to add loves in articles.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void a_AbilityToAddLoves() {
        Environments.goToVocalpointTestEnvironment(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.loveArticle();
        ArticlePage.verifyThatLovesCounterIsIncreased();
    }

    @Features("Loves")
    @Stories("User should be able to unlove in article.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void b_VerifyUnLoveInArticle() {
        Environments.goToVocalpointTestEnvironment(Environments.grabbedArticleUrl);
        AuthorizationPage.logIn();
        ArticlePage.unLoveArticle();
        ArticlePage.verifyThatLovesCounterIsDecreased();
        ArticlePage.loveArticle();
    }

    @Features("Loves")
    @Stories("User should be able to overview loves section in account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void c_VerifyLoveSection() {
        Environments.goToVocalpointTestEnvironment("/");
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToLovesSection();
        ProfilePage.verifyLoveInLovesSection();
    }

    @Features("Loves")
    @Stories("User should be able to delete love in loves section of account profile.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void d_VerifyDeletingLove() {
        Environments.goToVocalpointTestEnvironment("/");
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.goToLovesSection();
        ProfilePage.deleteLoveInProfile();
        ProfilePage.verifyLoveDeleted();
    }

    @Features("Reset Password")
    @Stories("User should be able to change password of existed account.")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void z_resetPasswordVerification() {
        Environments.goToVocalpointTestEnvironment("/");
        AuthorizationPage.logIn();
        HomePage.goToMyProfile();
        ProfilePage.clickOnResetPasswordButton();
        ProfilePage.fillCurrentPasswordInput(AuthorizationPage.validPassword);
        ProfilePage.fillNewPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.fillConfirmNewPasswordInput(AuthorizationPage.validNewPassword);
        ProfilePage.submitResetPasswordForm();
        ProfilePage.checkThatConfirmationMessageAppears();
    }
}
