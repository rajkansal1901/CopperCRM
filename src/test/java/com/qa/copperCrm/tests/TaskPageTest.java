package com.qa.copperCrm.tests;

import com.qa.copperCrm.base.BaseTest;
import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.dataProvider.TestData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TaskPageTest extends BaseTest {

    @BeforeClass
    public void navigateToTaskPage() {
        dashboardPage = loginPage.doLogin(properties.getProperty("email"), properties.getProperty("password"));
        tasksPage = dashboardPage.navigateToTasksPage();
    }

    @Test
    public void urlTest() {
        String actualUrl = tasksPage.getTaskPageUrl();
        Assert.assertTrue(actualUrl.contains(AppConstants.TASKS_PAGE_FRACTION_URL));
    }

    @Test
    public void titleTest() {
        String actualTitle = tasksPage.getTaskPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.TASKS_PAGE_TITLE);
    }


    @Test(dataProvider = "addTask", dataProviderClass = TestData.class)
    public void addNewTaskTest(String name, String monthYear, String date) {
        tasksPage.addNewTask(name, monthYear, date);
    }

    @Test(priority = Integer.MAX_VALUE)
    public void markTaskAsCompleteTest() {
        tasksPage.markTaskAsComplete();
        Assert.assertTrue(tasksPage.isTaskUpdated());
    }

    @AfterClass
    public void logOut() {
        loginPage = dashboardPage.doLogout();
    }
}
