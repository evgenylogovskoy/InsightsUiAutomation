package helpers;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static helpers.Tools.getDriver;

/**
 * Created by logovskoy
 */
public class Waiters {


    /**
     * Wait appearance of WebElement
     * with tiomeOut in seconds
     *
     * @param limit   in seconds
     * @param element Web Element
     */
    public static void waitAppearanceOf(int limit, WebElement element) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(limit, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until((Function) o -> ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Element was not appeared in " + limit + " sec");
        }
    }

    /**
     * Wait appearance of list of WebElements
     * with tiomeOut in seconds
     *
     * @param limit    in seconds
     * @param elements Web Element
     */
    public static void waitAppearanceOfAll(int limit, List<WebElement> elements) {
        try {
            FluentWait<org.openqa.selenium.WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(limit, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until((Function) -> ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Elements are not appeared in " + limit + " sec");
        }
    }

    /**
     * Wait disapperance of WebElement
     * with tiomeOut in seconds
     *
     * @param limit                in seconds
     * @param element              Web Element
     * @param implicityWaitTimeOut
     */
    public static void waitDisappearsOf(int limit, WebElement element, int implicityWaitTimeOut) {
        try {
            waitConstant(getDriver());

            if (element != null)
                if (element.isDisplayed()) {
                    FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                            .withTimeout(limit, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
                    wait.until((Function) -> ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
                }
            waitSeconds(getDriver(), implicityWaitTimeOut);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Element is not dissappeared in " + limit + " sec");
        }
    }

    /**
     * Wait while some text disappear from WebElement,
     * with timeOut in seconds
     *
     * @param limit   in seconds
     * @param element WebElement
     * @param text    text, that must disappear
     */
    public static void waitForTextDisappear(int limit, WebElement element, String text) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(limit, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
            wait.until((Function) -> ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Text is not dissappeared in " + limit + " sec");
        }
    }

    /**
     * Wait for special condition
     * with tiomeOut in seconds
     *
     * @param limit             in seconds
     * @param expectedCondition ExpectedCondition
     */
    public static void waitForCustomCondition(int limit, ExpectedCondition<Boolean> expectedCondition) {
        FluentWait<org.openqa.selenium.WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(limit, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .ignoring(TimeoutException.class);
        wait.until((Function) -> expectedCondition);
    }

    /**
     * Wait 1 second
     *
     * @param driver WebDriver
     */
    public static void waitConstant(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    /**
     * TimeOut in seconds
     *
     * @param driver
     * @param timeOut time in seconds
     */
    public static void waitSeconds(WebDriver driver, int timeOut) {
        getDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }


    /**
     * Executing until condition is true.
     *
     * @param times     int 1 = around 1 second
     * @param condition boolean
     */
    public static void waitWhileConditionIsTrue(int times, boolean condition) {
        for (int i = 0; i < times; i++) {
            if (condition) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else return;
        }
    }

    public static void waitForElement(int limit, WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), limit);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
        }
    }

}