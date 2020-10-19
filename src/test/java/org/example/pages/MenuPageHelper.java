package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPageHelper extends PageBase {
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profileVisabiltyMenu;
    @FindBy(xpath = "//span[contains(text(),'Activity')]/..")
    List<WebElement> activityMenuLocatorList;

    public MenuPageHelper(WebDriver driver) {
        super(driver);
    }
    public void waitUntilPageIsLoaded() {

        waitUntilElementIsClickable(profileVisabiltyMenu,10);
        //waitUntilElementsArePresent(By.xpath("//span[contains(text(),'Activity')]/.."),10);
        waitUntilNumberOfElementsToBe(By.xpath("//span[contains(text(),'Activity')]/.."),2,15);
        System.out.println("Size: " + activityMenuLocatorList.size());
        //waitUntilElementIsClickable(activityMenuLocatorList.get(1),10);
    }

    public void openProfileVisibility() {
        profileVisabiltyMenu.click();
    }

    public void openActivityPage() {
        activityMenuLocatorList.get(1).click();
    }
}
