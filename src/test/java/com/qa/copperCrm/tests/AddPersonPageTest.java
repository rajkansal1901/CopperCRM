package com.qa.copperCrm.tests;

import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddPersonPageTest extends BaseTest {

    @BeforeClass
    public void loginSetup() {

        dashboardPage = loginPage.doLogin(properties.getProperty("email"), properties.getProperty("password"));
    }

    @BeforeMethod
    public void navigateToAddPersonPage() {
        peoplePage = dashboardPage.navigateToPeoplePage();
        addPersonPage = peoplePage.clickAddPersonBtn().clickNewPersonBtn();
    }

    @AfterMethod
    public void navigateToPeoplePage() {
        addPersonPage.navigateToPeoplePage();
    }

    @Test
    public void addPersonPageHeaderTest() {
        Assert.assertEquals(addPersonPage.getAddPersonPageHeader(), AppConstants.ADD_PERSON_PAGE_HEADER_TEXT);
    }

    @Test
    public void addPersonPageProfileIconTest() {
        Assert.assertTrue(addPersonPage.isProfileIconExists());
    }
}
