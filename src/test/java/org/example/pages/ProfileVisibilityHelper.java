package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileVisibilityHelper extends PageBase {
    @FindBy(xpath = "//input[@name='username']")
    WebElement userNameField;

    @FindBy(xpath = "//a[@data-tab='profile']")
    WebElement profileTab;

    @FindBy(xpath = "//button[@aria-label = 'Open Member Menu']")
    WebElement memberMenuIcon;

    public ProfileVisibilityHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(userNameField,10);
        waitUntilElementIsVisible(profileTab,10);
    }

    public String getProfileVisibilityTabName(){
        return profileTab.getText();
    }

    public String getTitleMenuIcon() {
        return memberMenuIcon.getAttribute("title");
    }

    public String getUserName() {
        return userNameField.getAttribute("value");
    }
}
