package com.qa.copperCrm.pages;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    @FindBy(name = "user[email]")
    private WebElement email;

    @FindBy(name = "commit")
    private WebElement nextBtn;

    @FindBy(name = "user[password]")
    private WebElement password;

    @FindBy(className = "box_title")
    private WebElement welcomeMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * this method is used to get Login page title
     *
     * @return String (login Page title)
     */
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Login Page Title : " + title);
        return title;
    }

    /**
     * this method is used to get Login Page url
     *
     * @return String (Login Page Url)
     */
    public String getUrl() {
        String url = elementUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Login Page url : " + url);
        return url;
    }

    /**
     * this method is used to log in into WebApplication
     *
     * @param username
     * @param passwd
     * @return DashBoard Page
     */
    public DashboardPage doLogin(String username, String passwd) {
        elementUtil.waitAndClickElementAndSendKeys(email, username);
        elementUtil.click(nextBtn);
        elementUtil.waitAndClickElementAndSendKeys(password, passwd);
        elementUtil.click(nextBtn);
        return new DashboardPage(driver);
    }

    /**
     * this method is used to check is Welcome Message displayed on Login Page
     *
     * @return
     */
    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessage.isDisplayed();
    }
}
