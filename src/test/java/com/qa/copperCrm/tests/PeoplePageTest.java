package com.qa.copperCrm.tests;

import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
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

    @DataProvider
    public Object[][] addPersonData() {
        return new Object[][]{
                {"Stephen", "Martinez", "Mr", "Stephen Ltd", "ABC", "4544333", "4444 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Carl", "Marx", "Mr", "Carl Ltd", "ABC", "5434635", "5675 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Rohan", "Kumar", "Mr", "Rohan Ltd", "ABC", "454445433", "565 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"John", "Mid", "Mr", "John Ltd", "ABC", "6353543", "666 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Carry", "Anderson", "Mr", "Carry Ltd", "ABC", "6344", "322 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
        };
    }

    @Test(dataProvider = "addPersonData")
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

    @DataProvider
    public Object[][] deletePeopleRecordData() {
        return new Object[][]{
                {"Stephen", "Martinez", "Mr", "Stephen Ltd", "ABC", "4544333", "4444 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
        };
    }


    @Test(dataProvider = "deletePeopleRecordData")
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
