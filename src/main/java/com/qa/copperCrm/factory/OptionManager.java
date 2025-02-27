package com.qa.copperCrm.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OptionManager {

    private Properties properties;
    private ChromeOptions chromeOptions;
    private EdgeOptions edgeOptions;
    private FirefoxOptions firefoxOptions;

    public OptionManager(Properties prop) {
        this.properties = prop;
    }

    public ChromeOptions getChromeOptions() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized", "disable-popup-blocking");
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            chromeOptions.addArguments("--incognito");
            System.out.println("Running chrome in incognito......");
        }

        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            chromeOptions.addArguments("--headless");
            System.out.println("Running chrome in headless....");
        }
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("autofill.profile_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("start-maximized", "disable-popup-blocking");
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            firefoxOptions.addArguments("--incognito");
            System.out.println("Running firefox in incognito......");
        }

        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            firefoxOptions.addArguments("--headless");
            System.out.println("Running firefox in headless....");
        }
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized", "disable-popup-blocking");
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            edgeOptions.addArguments("--inPrivate");
            System.out.println("Running edge in incognito......");
        }

        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            edgeOptions.addArguments("--headless");
            System.out.println("Running edge in headless....");
        }
        return edgeOptions;
    }
}
