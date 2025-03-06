package com.qa.copperCrm.factory;

import com.qa.copperCrm.errors.AppError;
import com.qa.copperCrm.exceptions.BrowserException;
import com.qa.copperCrm.exceptions.FrameWorkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    protected WebDriver driver;
    private Properties properties;
    public static String isHighlight;
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * this method is used to initialize browser based on config.properties file
     *
     * @param prop it takes property file object
     * @return it returns driver
     */
    public WebDriver initDriver(Properties prop) {
        isHighlight = prop.getProperty("highlight");
        String browserName = prop.getProperty("browser");
        System.out.println("Browser name is : " + browserName);

        OptionManager optionManager = new OptionManager(prop);
        switch (browserName.toLowerCase().trim()) {
            case ("chrome"):
                driverThreadLocal.set(new ChromeDriver(optionManager.getChromeOptions()));
                break;
            case ("edge"):
                driverThreadLocal.set(new EdgeDriver(optionManager.getEdgeOptions()));
                break;
            case ("firefox"):
                driverThreadLocal.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
                break;
            case ("safari"):
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    driverThreadLocal.set(new SafariDriver());
                    break;
                }
            default:
                System.out.println(AppError.INVALID_BROWSER_MESSAGE + browserName);
                throw new BrowserException(AppError.INVALID_BROWSER_MESSAGE + browserName);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * this method is used to init the properties from config file
     *
     * @return Property
     */
    public Properties initProp() {
        properties = new Properties();
        FileInputStream fis;

        String env = System.getProperty("env");
        try {
            if (env == null) {
                System.out.println("env not mentioned.... hence running on QA env");
                fis = new FileInputStream("./src/main/resources/config/qa.config.properties");
            } else {
                switch (env.toLowerCase().trim()) {
                    case ("qa"):
                        System.out.println("Running in Qa env");
                        fis = new FileInputStream("./src/main/resources/config/qa.config.properties");
                        break;
                    case ("production"):
                        System.out.println("Running in Production env");
                        fis = new FileInputStream("./src/main/resources/config/config.properties");
                        break;
                    default:
                        System.out.println("Please pass right env name :" + env);
                        throw new FrameWorkException("Invalid env name");
                }
            }
            properties.load(fis);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return properties;
    }

    /**
     * this method is used to take screenshot
     * @return String(File path)
     */
    public static String screenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileHandler.copy(srcfile, destination);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        return path;
    }
}
