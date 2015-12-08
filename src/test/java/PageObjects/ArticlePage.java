package PageObjects;

import Common.TestHelper;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class ArticlePage {
    public static String xpathButtonArticleOrder = "//*[contains(@class,'articles-order')]/a";
    public static String xpathLinkFirstArticle = "//*[@class='rel-article-headline-wrap']//a[@class='linkUrlFix-processed']";
    public static String xpathTextAreaComment = "//*[@id='comment-body-add-more-wrapper']//textarea";
    public static String xpathButtonPostComment = "//*[@class='wl-upload-media-submit']//input[@type='submit']";
    public static String xpathComment = "//*[text()='uxgautomation t.']";
    public static String xpathButtonLoveArticle = "//a[contains(@href,'/node/')]";
    public static String xpathLovesCounter = "//a[contains(@href,'/node/')][contains(@href,'/like')]";
    public static String xpathUpdatedLovesCounter = "//a[contains(@href,'/node/')][contains(@href,'/unlike')]";

    @Step("Click on first available article in articles section.")
    public static void goToTheFirstAvailableArticle() {
        TestHelper.waitXpathElement(xpathLinkFirstArticle).click();
    }
    @Step("Go to the text area for user's comment and fill it by some text.")
    public static void fillTextAreaByComment() {
        TestHelper.waitXpathElement(xpathTextAreaComment);
        TestHelper.moveXpathElement(xpathTextAreaComment);
        TestHelper.waitXpathElement(xpathTextAreaComment).sendKeys("Selenium autotest comment.");
    }
    @Step("Click on Post My comment button.")
    public static void clickOnPostMyCommentButton() {
        TestHelper.moveXpathElement(xpathButtonPostComment);
        TestHelper.waitXpathElement(xpathButtonPostComment).click();
    }
    @Step("Verify that comment appears on the page.")
    public static void verifyThatCommenIsAdded() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(xpathComment).isDisplayed());
    }

    @Step("Add Love in article.")
    public static void loveArticle() {
        TestHelper.waitXpathElement(xpathButtonLoveArticle).click();
    }
    @Step("Verify that loves counter is updated and equals 1")
    public static void verifyThatLovesCounterIsUpdated() {
        Assert.assertEquals(true,TestHelper.waitXpathElement(xpathUpdatedLovesCounter).isDisplayed());
    }

}
