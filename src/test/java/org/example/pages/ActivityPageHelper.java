package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {
    @FindBy (xpath = "//div[@class = 'phenom-desc']")
    List<WebElement> recordsList;

    public ActivityPageHelper(WebDriver driver) {
        super(driver);
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementsAreVisible(recordsList,15);
    }

    public String getFirstRecordText() {
        return  recordsList.get(0).getText();
    }
}
