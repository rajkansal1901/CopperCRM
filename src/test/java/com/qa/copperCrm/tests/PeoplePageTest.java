package com.qa.copperCrm.tests;

import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.dataProvider.TestData;
import org.testng.Assert;
import org.testng.annotations.*;

public class PeoplePageTest extends BaseTest {

    @BeforeClass
    public void dashboardSetup() {
        dashboardPage = loginPage.doLogin(properties.getProperty("email"), properties.getProperty("password"));
    }

    @BeforeMethod
    public void navigateToPeoplePage() {
        peoplePage = dashboardPage.navigateToPeoplePage();
    }

    @Test
    public void peoplePageTitleTest() {
        String actualTitle = peoplePage.getPeoplePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.PEOPLE_PAGE_TITLE);
    }

    @Test
    public void peoplePageUrlTest() {
        String actualUrl = peoplePage.getUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.PEOPLE_PAGE_FRACTION_URL));
    }

    public String uniqueEmail() {
        return "abc" + System.currentTimeMillis() + "@gmail.com";
    }


    @Test(dataProvider = "addPersonData", dataProviderClass = TestData.class)
    public void addPersonTest(String firstname, String lastname, String prefix, String companyName, String title
            , String workPhone, String street, String city, String state, String zip,
                              String country, String tags) {

        addPersonPage = peoplePage.clickAddPersonBtn()
                                    .clickNewPersonBtn();


        softAssert.assertEquals(addPersonPage.getAddPersonPageHeader(), AppConstants.ADD_PERSON_PAGE_HEADER_TEXT);

        peoplePage = addPersonPage.addNewPerson(firstname, lastname, prefix, companyName, title, uniqueEmail(),
                workPhone, street, city, state, zip, country, tags);

        softAssert.assertTrue(peoplePage.isConfirmationMessageDisplayed());
        softAssert.assertAll();
    }


    @Test(dataProvider = "deletePeopleRecordData", dataProviderClass = TestData.class)
    public void deleteAllPeopleTest(String firstname, String lastname, String prefix, String companyName, String title
            , String workPhone, String street, String city, String state, String zip,
                                    String country, String tags) {
        addPersonPage = peoplePage.clickAddPersonBtn()
                .clickNewPersonBtn();
        softAssert.assertEquals(addPersonPage.getAddPersonPageHeader(), AppConstants.ADD_PERSON_PAGE_HEADER_TEXT);

        peoplePage = addPersonPage.addNewPerson(firstname, lastname, prefix, companyName, title, uniqueEmail(),
                workPhone, street, city, state, zip, country, tags);
        softAssert.assertTrue(peoplePage.isConfirmationMessageDisplayed());

        peoplePage.selectAllPeople();
        softAssert.assertTrue(peoplePage.isSelectStatusVisible());

        peoplePage.deleteAllItem();
        softAssert.assertTrue(peoplePage.isPageEmpty());
        softAssert.assertAll();
    }

    @AfterClass
    public void logout() {
        dashboardPage.doLogout();
    }
}
