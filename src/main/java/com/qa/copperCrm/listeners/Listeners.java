package com.qa.copperCrm.listeners;

import com.qa.copperCrm.factory.DriverFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    public void onTestFailure(ITestResult result) {
        DriverFactory.screenshot();
    }
}
