package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//*[@class='btn btn-sm btn-link text-white']")
    WebElement loginIcon;

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded() {

        waitUntilElementIsClickable(loginIcon,30);
        return this;
    }

    public HomePageHelper openLoginPage() {
        loginIcon.click();
        return  this;
    }
}
