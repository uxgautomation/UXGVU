package PageObjects;

import Common.TestHelper;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class ProfilePage {
    public static String xpathButtonResetPassword = "//*[@id='resetpass']";
    public static String xpathInputCurrentPassword = "//*[@id='edit-current-pass']";
    public static String xpathInputNewPassword = "//*[@id='edit-current-pass']";
    public static String xpathInputConfirmNewPassword = "//*[@id='edit-current-pass']";
    public static String xpathButtonSubmitResetPassword = "//*[@id='edit-submit-rp--3']";
    public static String xpathConfirmationMessage = "//*[@class='resetpass-popup']";

    public static String xpathLinkToCommentsSection = "//a[@href='/profile/2049189/comments']";
    public static String xpathCommentInCommentsSection = "//p[text()='Selenium autotest comment.']";
    public static String xpathButtonDeleteCommentLove = "//a[text()='delete']";
    public static String xpathButtonYesDeleteComment = "//button[text()='Yes']";

    public static String xpathLinkToLovesSection = "//a[contains(@href,'/loves')]";
    public static String xpathLoveInLovesSection = "//div[@class='topic-title']";

    @Step("Click on Reset Password.")
    public static void clickOnResetPasswordButton() {
        TestHelper.moveXpathElement(xpathButtonResetPassword);
        TestHelper.waitXpathElement(xpathButtonResetPassword).click();
    }
    @Step("Fill Current Password input with current password value.")
    public static void fillCurrentPasswordInput(String currentPass) {
        TestHelper.waitXpathElement(xpathInputCurrentPassword).sendKeys(currentPass);
    }
    @Step("Fill New Password input with new password value.")
    public static void fillNewPasswordInput(String newPass) {
        TestHelper.waitXpathElement(xpathInputNewPassword).sendKeys(newPass);
    }
    @Step("Fill Confirm New Password input with new password value.")
    public static void fillConfirmNewPasswordInput(String confirmNewPass) {
        TestHelper.waitXpathElement(xpathInputConfirmNewPassword).sendKeys(confirmNewPass);
    }
    @Step("Submit Reset Password form by click on Reset Password button.")
    public static void submitResetPasswordForm() {
        TestHelper.waitXpathElement(xpathButtonSubmitResetPassword).click();
    }
    @Step("Make sure that pop up with confirmation message appears.")
    public static void checkThatConfirmationMessageAppears() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(xpathConfirmationMessage).isDisplayed());
    }


    @Step("Click on Comments section link in profile.")
    public static void goToCommentsSection() {
        TestHelper.waitXpathElement(xpathLinkToCommentsSection).click();
    }
    @Step("Verify that previously added comments are displayed.")
    public static void verifyThatCommentSectionIsOpened() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(xpathCommentInCommentsSection).isDisplayed());
    }

    @Step("Click on Delete button to remove comment.")
    public static void deleteComment() {
        TestHelper.waitXpathElement(xpathButtonDeleteCommentLove).click();
    }
    @Step("Confirm deleting by click on Yes button in pop up.")
    public static void confirmDeleteComment() {
        TestHelper.waitXpathElement(xpathButtonYesDeleteComment).click();
    }
    @Step("Make sure that deleted comment isn't displays now.")
    public static void verifyThatCommentIsDeleted() {
        Assert.assertEquals(true,TestHelper.waitXpathElementNotExist(xpathCommentInCommentsSection));
    }

    @Step("Click on Loves section link in profile.")
    public static void goToLovesSection() {
        TestHelper.waitXpathElement(xpathLinkToLovesSection).click();
    }
    @Step("Verify that there is Love in Loves section.")
    public static void verifyLoveInLovesSection() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(xpathLoveInLovesSection).isDisplayed());
    }

    @Step("Click delete button in Loves section of user profile.")
    public static void deleteLoveInProfile() {
        TestHelper.waitXpathElement(xpathButtonDeleteCommentLove).click();
    }
    @Step("Verify that deleted love isn't displays in Loves section of user profile.")
    public static void verifyLoveDeleted() {
        Assert.assertEquals(true,TestHelper.waitXpathElementNotExist(xpathLoveInLovesSection));

    }
}
