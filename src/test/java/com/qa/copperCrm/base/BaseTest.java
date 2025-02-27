package com.qa.copperCrm.base;

import com.qa.copperCrm.factory.DriverFactory;
import com.qa.copperCrm.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    protected DriverFactory driverFactory;
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected Properties properties;
    protected DashboardPage dashboardPage;
    protected AddPersonPage addPersonPage;
    protected PeoplePage peoplePage;
    protected TasksPage tasksPage;
    protected SoftAssert softAssert;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional String browserName) {
        driverFactory = new DriverFactory();
        softAssert = new SoftAssert();


        properties = driverFactory.initProp();
        if (browserName != null) {
            properties.setProperty("browser", browserName);
        }
        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
