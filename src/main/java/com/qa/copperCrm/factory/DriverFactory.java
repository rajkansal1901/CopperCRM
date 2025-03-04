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
                driver = new ChromeDriver(optionManager.getChromeOptions());
                break;
            case ("edge"):
                driver = new EdgeDriver(optionManager.getEdgeOptions());
                break;
            case ("firefox"):
                driver = new FirefoxDriver(optionManager.getFirefoxOptions());
                break;
            case ("safari"):
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    driver = new SafariDriver();
                    break;
                }
            default:
                System.out.println(AppError.INVALID_BROWSER_MESSAGE + browserName);
                throw new BrowserException(AppError.INVALID_BROWSER_MESSAGE + browserName);
        }
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));

        return driver;
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
    public String screenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileHandler.copy(srcfile, destination);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        return path;
    }
}
