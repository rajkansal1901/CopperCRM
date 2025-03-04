package com.qa.copperCrm.pages;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    @FindBy(id = "ember35")
    private WebElement profileIcon;

    @FindBy(css = "[data-test=\"leftNav\"] > div:nth-child(2) > div > a")
    private List<WebElement> leftMenuOptions;

    @FindBy(css = "[data-test='dropdownIcon']")
    private WebElement leftBarDropDownMenuBtn;

    @FindBy(css = ".LeftNav_footerMenu li[data-test='logOutOptionListItem']")
    private WebElement logoutBtn;

    @FindBy(css = "[href='#/browse/list/people/default']")
    private WebElement leftBarPeopleMenu;

    @FindBy(css = "[href='#/browse/list/tasks/default']")
    private WebElement leftBarTaskMenu;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * this method is used to get DashBoard Page title
     *
     * @return String (title)
     */
    public String getDashboardPageTitle() {
        String title = elementUtil.waitForTitleContainsAndReturn(AppConstants.DASHBOARD_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Dashboard Page Title : " + title);
        return title;
    }

    /**
     * this method is used to get current Page url
     *
     * @return String (current page url)
     */
    public String getUrl() {
        String url = elementUtil.waitForURLContainsAndReturn(AppConstants.DASHBOARD_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Dashboard Page url : " + url);
        return url;
    }

    /**
     * this method is used to validate profile icon is visible on DashBoard page or not
     *
     * @return boolean
     */
    public boolean isProfileIconVisible() {
        elementUtil.waitUntilElementVisibility(profileIcon, AppConstants.DEFAULT_SHORT_TIMEOUT);
        elementUtil.screenshot();
        return profileIcon.isDisplayed();
    }

    /**
     * this method is used to get the total menu available on left bar
     *
     * @return int (total menu count)
     */
    public int getTotalMenuOptions() {
        elementUtil.waitUntilAllElementsVisible(leftMenuOptions);
        return leftMenuOptions.size();
    }

    /**
     * this method is used for logout from WebApplication
     * @return LoginPage
     */
    public LoginPage doLogout() {
        elementUtil.waitAndClick(leftBarDropDownMenuBtn);
        elementUtil.waitAndClick(logoutBtn);
        return new LoginPage(driver);
    }

    /**
     * this method is used to navigate to People Page
     *
     * @return PeoplePage
     */
    public PeoplePage navigateToPeoplePage() {
        elementUtil.waitAndClick(leftBarPeopleMenu);
        return new PeoplePage(driver);
    }

    /**
     * this method is used to navigate to Tasks Page
     *
     * @return TasksPage
     */
    public TasksPage navigateToTasksPage() {
        elementUtil.waitAndClick(leftBarTaskMenu);
        return new TasksPage(driver);
    }
}
