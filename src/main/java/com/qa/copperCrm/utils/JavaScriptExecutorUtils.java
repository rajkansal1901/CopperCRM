package com.qa.copperCrm.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorUtils {
    private WebDriver driver;
    private JavascriptExecutor jse;

    public JavaScriptExecutorUtils(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
    }

    public String getPageTitle() {
        return jse.executeScript("return document.title;").toString();
    }

    public String getPageUrl() {
        return jse.executeScript("return document.URL;").toString();
    }

    public void generateJsAlert(String msg) {
        jse.executeScript("alert('"+msg+"')");
    }

    public String getPageText() {
        return jse.executeScript("return document.documentElement.innerText;").toString();
    }

    public void goBackWithJs() {
        jse.executeScript("history.go(-1)");
    }

    public void goForwardWithJs() {
        jse.executeScript("history.go(1)");
    }

    public void refreshPageWithJs() {
        jse.executeScript("history.go(0)");
    }

    public void scrollToBottomWithJs() {
        jse.executeScript("window.scroll(0, document.body.scrollHeight);");
    }

    public void scrollToTopWithJs() {
        jse.executeScript("window.scroll(document.body.scrollHeight, 0);");
    }

    public void scrollPageDownWithJs(String height) {
        jse.executeScript("window.scroll(0, '"+height+"');");
    }

    public void scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void drawBorder(WebElement element) {
        jse.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundColor");//white
        for (int i = 0; i < 4; i++) {
            changeColor("rgb(0,200,0)", element);// Green
            changeColor(bgColor, element);// White
        }
    }

    private void changeColor(String color, WebElement element) {
        jse.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
    }

}
