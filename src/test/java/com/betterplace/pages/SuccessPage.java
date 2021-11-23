package com.betterplace.pages;

import com.betterplace.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage extends BasePage{

    public SuccessPage(){ PageFactory.initElements(Driver.getDriver(), this);}


    @FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div/div/div/h1")
    public WebElement successMessage;

}
