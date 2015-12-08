package Common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class TestHelper {
    public static WebDriver driver;

//Setup and quit of web driver

    public static void localDriver(String browser) {
        if(browser == "chrome") {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser == "firefox") {
            driver = new FirefoxDriver();
        } else if (browser == "ie") {
            System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
    }
    public static void localDriverAndGo(String browser, int width, int height, String link) {
        localDriver(browser);
        setResolution(width, height);
        get(link);
    }
    public static void localDriverFullscreen(String browser) {
        localDriver(browser);
        setFullscreen();
    }
    public static void localDriverFullscreenAndGo(String browser, String link) {
        localDriver(browser);
        setFullscreen();
        get(link);
    }

    public static void droneDriver(String browser) {
        if(browser == "chrome") {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
            driver = new ChromeDriver();
        } else if (browser == "firefox") {
            System.setProperty("webdriver.firefox.driver", "/usr/bin/firefox");
            driver = new FirefoxDriver();
        }
    }
    public static void droneDriverAndGo(String browser, int width, int height, String link) {
        droneDriver(browser);
        setResolution(width, height);
        get(link);
    }
    public static void droneDriverFullscreenAndGo(String browser, String link) {
        droneDriver(browser);
        setFullscreen();
        get(link);
    }

    public static void jenkinsDriver(String browser) {
        if(browser == "chrome") {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser == "firefox") {
            driver = new FirefoxDriver();
        } else if (browser == "ie") {
            System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
    }
    public static void jenkinsDriverAndGo(String browser, int width, int height, String link) {
        jenkinsDriver(browser);
        setResolution(width, height);
        get(link);
    }
    public static void jenkinsDriverFullscreenAndGo(String browser, String link) {
        jenkinsDriver(browser);
        setFullscreen();
        get(link);
    }

    public static void setResolution(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }
    public static void setFullscreen() {
        driver.manage().window().maximize();
    }
    public static void get(String url) {
        driver.get(url);
    }
    public static void quit() {
        driver.quit();
    }

//Basic actions on page
    public static void scrollPage(int coord) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0," + coord + ")");
    }
    public static void waitSec(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void waitMsec(long msec) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static WebElement waitIdElement(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.id(selector)).size() > 0) {
                for (int c = 0; c < 200; c++) {
                    if (driver.findElement(By.id(selector)).isDisplayed() &&
                            driver.findElement(By.id(selector)).isEnabled()) {
                        break;
                    }
                    waitMsec(100);
                }
                break;
            }
            waitMsec(100);
        }
        return driver.findElement(By.id(selector));
    }
    public static WebElement waitXpathElement(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.xpath(selector)).size() > 0) {
                for (int c = 0; c < 200; c++) {
                    if (driver.findElement(By.xpath(selector)).isDisplayed() &&
                            driver.findElement(By.xpath(selector)).isEnabled()) {
                        break;
                    }
                    waitMsec(100);
                }
                break;
            }
            waitMsec(100);
        }
        return driver.findElement(By.xpath(selector));
    }
    public static WebElement waitCssElement(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.cssSelector(selector)).size() > 0) {
                for (int c = 0; c < 200; c++) {
                    if (driver.findElement(By.cssSelector(selector)).isDisplayed() &&
                            driver.findElement(By.cssSelector(selector)).isEnabled()) {
                        break;
                    }
                    waitMsec(100);
                }
                break;
            }
            waitMsec(100);
        }
        return driver.findElement(By.cssSelector(selector));
    }
    public static WebElement waitIdElementNotExist(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.id(selector)).size() == 0) {
                break;
            }
            waitMsec(100);
        }
        return null;
    }
    public static boolean waitXpathElementNotExist(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.xpath(selector)).size() == 0) {
                break;
            }
            waitMsec(100);
        }
        return true;
    }
    public static WebElement waitCssElementNotExist(String selector) {
        for (int i = 0; i < 200; i++) {
            if (driver.findElements(By.cssSelector(selector)).size() == 0) {
                break;
            }
            waitMsec(100);
        }
        return null;
    }
    public static void moveToIdElement(String selector) {
        new Actions(driver).moveToElement(driver.findElement(By.id(selector)));
    }
    public static void moveXpathElement(String selector) {
        new Actions(driver).moveToElement(driver.findElement(By.xpath(selector)));
    }
    public static void moveToCssElement(String selector) {
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector(selector)));
    }

//Alert handler and windows switcher
    public static boolean checkIsAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }
    public static void handleAlert() {
        if (checkIsAlertPresent()) {
            waitMsec(800);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }
    public void getNewWindow(String xpath) {
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        waitXpathElement(xpath).click();
        String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindow);
        System.out.println("New window title: " + driver.getTitle());
        driver.close();
        driver.switchTo().window(originalWindow);
        System.out.println("Old window title: " + driver.getTitle());
    }

//Output files generation
    public static void takeScreenshot(String commonname) {
        try {
            File scrFile =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new
                    File("C:\\Selenium\\Screenshots\\" + commonname + generateActualDateString("yyyyMMdd")
                    + "\\" + generateActualDateString("yyyyMMddHHmmss") + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Date format examples: "yyyyMMdd", "MM.dd.yyyy", "yyyyMMddHHmmss"
    public static String generateActualDateString(String dateformat) {
        DateFormat dateFormat = new SimpleDateFormat(dateformat);
        Date date = new Date();
        String FinalDate = dateFormat.format(date);
        return FinalDate;
    }

    //Attaching files
    public static void setClipboardData(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    public void attachFile(String path) {
        setClipboardData(path);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

//Actions with dropdown
    public static void selectIndexInDropdownXpath(String selector, int number) {
        waitXpathElement(selector);
        WebElement select = TestHelper.waitXpathElement(selector);
        Select sel = new Select(select);
        sel.selectByIndex(number);
    }
    public static void selectVisibleTextInDropdownXpath(String selector, String text) {
        waitXpathElement(selector);
        WebElement select = TestHelper.waitXpathElement(selector);
        Select sel = new Select(select);
        sel.selectByVisibleText(text);
    }

}
