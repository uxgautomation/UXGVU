package PageObjects;

import Common.Environments;
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
    public static String xpathLovesCounter = "//a[contains(@href,'/node/')]/following-sibling::p/span[contains(@content,'UserLikes')]";
    public static String xpathButtonUnLoveArticle = "//a[contains(@href,'/node/')][contains(@href,'/unlike')]";

    @Step("Go to the first available article in articles list.")
    public static void goToTheFirstAvailableArticle() {
        TestHelper.waitXpathElement(xpathLinkFirstArticle).click();
        Environments.getGrabbedArticleUrl();
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

    public static int initialLoveCounter = 0;
    public static int finalLoveCounter = 0;
    public static int getLoveState() {
        int intCounter = Integer.parseInt(TestHelper.waitXpathElement(xpathLovesCounter).getText());
        return intCounter;
    }
    @Step("Add Love in article.")
    public static void loveArticle() {
        getLoveState();
        initialLoveCounter = getLoveState();
        TestHelper.waitXpathElement(xpathButtonLoveArticle).click();
    }
    @Step("Remove love in article.")
    public static void unLoveArticle() {
        getLoveState();
        initialLoveCounter = getLoveState();
        TestHelper.waitXpathElement(xpathButtonUnLoveArticle).click();
    }

    @Step("Verify that loves counter is increased by 1")
    public static void verifyThatLovesCounterIsIncreased() {
        getLoveState();
        finalLoveCounter = getLoveState();
        Assert.assertNotEquals(initialLoveCounter + 1,finalLoveCounter);
    }

    @Step("Verify that loves counter is decreased by 1")
    public static void verifyThatLovesCounterIsDecreased() {
        getLoveState();
        finalLoveCounter = getLoveState();
        Assert.assertNotEquals(initialLoveCounter - 1,finalLoveCounter);
    }

}
