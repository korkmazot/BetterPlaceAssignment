package com.betterplace.tests;

import com.betterplace.pages.BasePage;
import com.betterplace.pages.SuccessPage;
import com.betterplace.utilities.ConfigurationReader;
import com.betterplace.utilities.Driver;
import com.betterplace.utilities.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class TestChallenge extends TestBase{

    protected WebDriver driver;


    @DataProvider
    public Object[][] testData(){
        String[][] data={
                {"Success Url",ConfigurationReader.getProperty("url1")},
                {"Bug Url",ConfigurationReader.getProperty("url2")}
        };
        return data;
    }

    @Test(dataProvider = "testData")
    public void Test1(String name, String url) {
        extentLogger=report.createTest("Better Place Donation Test");

        extentLogger.info("Running Tests for: "+url);

        driver = Driver.getDriver();
        driver.get(url);
        SuccessPage successPage = new SuccessPage();

        //Accept Cookies
        extentLogger.info("Accept Cookies");
        successPage.acceptCookies();

        //Select existing text and type 5
        extentLogger.info("Select existing text and type 5");
        successPage.amountField.sendKeys(Keys.chord(Keys.CONTROL,"a"),"5");

        //Select uberweisung
        extentLogger.info("Select uberweisung");
        successPage.uberweisungButton.click();

        //here verify that elements are not overlapping!!!
        extentLogger.info("verify that elements are not overlapping!");
        Assert.assertFalse(ReusableMethods.areElementsOverlapping(successPage.payDirektButton,successPage.uberweisungButton),"Elements are overlapping");
        extentLogger.pass("Elements are not overlapping");

        //fill the rest of the form with random data from java faker
        ReusableMethods.scrollToElement(successPage.name);
        extentLogger.info("Fill name and surname fields");
        successPage.name.sendKeys(faker.name().firstName());
        successPage.surname.sendKeys(faker.name().lastName());

        //email adress should be @betterplace.org
        extentLogger.info("Fill email adress field");
        successPage.email.sendKeys( fakeValuesService.bothify("????##@betterplace.org"));
        ReusableMethods.scrollToElement(successPage.submitButton);
        extentLogger.info("Click on 'Zu den Zahlung Informationen ");
        successPage.submitButton.click();

        //wait until next page is loaded
        wait.until(ExpectedConditions.visibilityOf(successPage.successMessage));

        //Verify donation is successfull
        Assert.assertEquals(successPage.successMessage.getText(),"Bitte überweise deinen Spendenbetrag an unten stehende Bankverbindung");
        extentLogger.pass("Test passed!");
   }


}
