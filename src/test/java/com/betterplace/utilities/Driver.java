package com.betterplace.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

public class Driver {

    static WebDriver driver;

    public static WebDriver getDriver(){

        if(driver==null){

            String browser = ConfigurationReader.getProperty("browser");

            switch(browser){
                case "chrome":
                    Map<String, Object> deviceMetrics = new HashMap<>();

                    deviceMetrics.put("width", 399);
                    deviceMetrics.put("height", 825);
                    deviceMetrics.put("pixelRatio", 3.0);
                    Map<String, Object> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceMetrics", deviceMetrics);
                    mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "safari":
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;

            }
        }
        return driver;
    }
    public static void closeDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;

        }
    }
    }

