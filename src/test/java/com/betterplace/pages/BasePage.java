package com.betterplace.pages;

import com.betterplace.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    //public BasePage(){ PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "//*[@id=\"mount-donate-form\"]/div/div/div/div/div/form/div[1]/div[2]/div/div[1]/input")
    public WebElement amountField;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-large flex-grow mb-3']")
    public WebElement acceptButton;

    @FindBy(xpath = "//*[@id=\"mount-donate-form\"]/div/div/div/div/div/form/div[1]/div[3]/div[1]/div[1]/label[5]/span[2]")
    public WebElement uberweisungButton;

    @FindBy(xpath = "//*[@id=\"mount-donate-form\"]/div/div/div/div/div/form/div[1]/div[3]/div[1]/div/label[4]/span[2]")
    public WebElement payDirektButton;

    @FindBy(xpath = "//*[@id=\"mount-donate-form\"]/div/div/div/div/div/form/div[1]/div[3]/button")
    public WebElement submitButton;

    @FindBy(id = "first_name")
    public WebElement name;

    @FindBy(id = "last_name")
    public WebElement surname;

    @FindBy(id = "email")
    public WebElement email;

    public void acceptCookies(){
        acceptButton.click();

    }


}
