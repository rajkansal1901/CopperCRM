package com.qa.copperCrm.utils;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.factory.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtil {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;
    protected JavaScriptExecutorUtils jsUtils;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_MEDIUM_TIMEOUT));
        actions = new Actions(driver);
        jsUtils = new JavaScriptExecutorUtils(driver);
    }

    public void waitAndClickElementAndSendKeys(WebElement element, String key) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        flashElement(element);
        element.sendKeys(key);
    }

    public void click(WebElement element) {
        flashElement(element);
        element.click();
    }

    public void doSendKeys(WebElement element, String value) {
        flashElement(element);
        element.sendKeys(value);
    }

    public void waitAndClick(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            click(element);
        } catch (Exception e) {
            moveToElementAndClick(element);
        }
    }

    public String waitForTitleContainsAndReturn(String fractionTitle, int timeOut) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            webDriverWait.until(ExpectedConditions.titleContains(fractionTitle));
            return driver.getTitle();
        } catch (TimeoutException e) {
            System.out.println("title is not matched");
            return driver.getTitle();
        }
    }

    public String waitForURLContainsAndReturn(String fractionURL, int timeOut) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            webDriverWait.until(ExpectedConditions.urlContains(fractionURL));// true
            return driver.getCurrentUrl();
        } catch (TimeoutException e) {
            System.out.println("URL is not matched");
            return driver.getCurrentUrl();
        }
        }

    public void waitUntilElementVisibility(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementVisibility(WebElement element, int seconds) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilAllElementsVisible(List<WebElement> elements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    public void switchToIframe(String idOrName) {
        driver.switchTo().frame(idOrName);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }


    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void pressKey(Keys key) {
        actions.keyDown(key).release().perform();
    }

    public void moveToElementAndClick(WebElement element) {
        moveToElement(element);
        flashElement(element);
        actions.click(element).perform();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void enterData(WebElement element, String data) {
        actions.moveToElement(element).perform();
        flashElement(element);
        actions.click().perform();
        char[] chars = data.toCharArray();
        for (char ch : chars) {
            actions.sendKeys(String.valueOf(ch)).build().perform();
            actions.pause(Duration.ofMillis(30)).perform();
        }
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dragElement(WebElement elementToDrag, WebElement destination) {
        actions.clickAndHold(elementToDrag).build().perform();
        actions.moveToElement(destination).build().perform();
        actions.release().build().perform();
    }

    private void flashElement(WebElement element) {
        if (Boolean.parseBoolean(DriverFactory.isHighlight)) {
            jsUtils.flash(element);
        }
    }

    public void selectDate(WebElement monthYearElement,WebElement nextBtnElement, List<WebElement> dateElement, String expectedMonthYear, String expectedDate) {
        String actualMonthYear = monthYearElement.getText();
        while (!actualMonthYear.equalsIgnoreCase(expectedMonthYear)) {
            click(nextBtnElement);
            actualMonthYear = monthYearElement.getText();
        }
        for ( WebElement element : dateElement) {
            if (element.getText().equalsIgnoreCase(expectedDate)) {
                element.click();
                break;
            }
        }
    }
}
