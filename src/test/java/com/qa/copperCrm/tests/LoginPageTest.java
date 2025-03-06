package com.qa.copperCrm.tests;

import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertTrue(actualTitle.contains(AppConstants.LOGIN_PAGE_TITLE));
    }

    @Test
    public void loginPageUrlTest() {
        String actualUrl = loginPage.getUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
    }

    @Test
    public void isLoginMessageExistTest() {
        Assert.assertTrue(loginPage.isWelcomeMessageDisplayed());
    }

    @Test(priority = Integer.MAX_VALUE)
    public void loginTest() {
        dashboardPage = loginPage.doLogin(properties.getProperty("email"), properties.getProperty("password"));
        Assert.assertEquals(dashboardPage.getDashboardPageTitle(), AppConstants.DASHBOARD_PAGE_TITLE);
    }

    @AfterClass
    public void logout() {
        dashboardPage.doLogout();
    }
}
