package com.qa.copperCrm.tests;
import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashboardPageTest extends BaseTest {

    @BeforeClass
    public void dashboardSetup() {
        dashboardPage = loginPage.doLogin(properties.getProperty("email"), properties.getProperty("password"));
    }

    @Test
    public void dashboardPageTitleTest() {
        String actualTitle = dashboardPage.getDashboardPageTitle();
        Assert.assertTrue(actualTitle.contains(AppConstants.DASHBOARD_PAGE_TITLE));
    }

    @Test
    public void isProfileIconExistTest() {
        Assert.assertTrue(dashboardPage.isProfileIconVisible());
    }

    @Test
    public void dashboardPageMenuCountTest() {
        Assert.assertEquals(dashboardPage.getTotalMenuOptions(), AppConstants.LEFT_MENU_OPTIONS_COUNT);
    }

    @AfterTest
    public void logout() {
        dashboardPage.doLogout();
    }

}
