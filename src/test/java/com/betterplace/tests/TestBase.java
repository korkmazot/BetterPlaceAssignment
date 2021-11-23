package com.betterplace.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.betterplace.utilities.ConfigurationReader;
import com.betterplace.utilities.Driver;
import com.betterplace.utilities.ReusableMethods;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String url;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;
    protected FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
    protected Faker faker=new Faker(new Random(24));

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report = new ExtentReports();
        //create report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath +"/test-output/report.html";
        //initialize the html reporter with the report path
        htmlReporter= new ExtentHtmlReporter(path);
        //attach the html report to the report object
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Better Place Test");
        //set environment information
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));
    }

    @AfterTest
    public void tearDownTest(){
        //this is when the report is actually created
        report.flush();
    }

    @BeforeMethod
    public void setupMethod(){
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);

        Dimension dimension = new Dimension(399,825);
        driver.manage().window().setSize(dimension);

    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        //If test failed
        if(result.getStatus()==ITestResult.FAILURE){
            //record the name of the failed test case
            extentLogger.fail(result.getName());
            //take the screenshot and return location of screenshot
            String screenshotPath = ReusableMethods.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);
            //capture the exception
            extentLogger.fail(result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentLogger.skip("Test Skipped: "+result.getName());
        }
        //Close the driver
        Thread.sleep(1000);
        Driver.closeDriver();
    }

}
