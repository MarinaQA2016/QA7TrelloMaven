package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpMenuPageHelper extends PageBase {
    @FindBy(xpath = "//iframe[@sandbox='allow-scripts allow-same-origin allow-popups allow-forms']")
    WebElement menuFrame;
    @FindBy(xpath = "//a[contains(text(), 'Getting Started Guide')]")
    WebElement menuGettingStartedGuide;
    @FindBy(xpath = "//a[contains(text(), 'How to use Trello like a pro')]")
    WebElement menuHowToUseTrelloLikeAPro;

    public HelpMenuPageHelper(WebDriver driver) {
        super(driver);
    }


    public void waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(menuFrame,10);
    }

    public boolean existsGettingStartedGuideMenu() {
        this.frameToBeAvailableAndSwitchToIt(menuFrame,15);
        this.waitUntilElementIsClickable(menuGettingStartedGuide,10);
        return menuGettingStartedGuide.isDisplayed();
    }

    public boolean existsHowToUseTrelloLikeAProMenu() {
        this.frameToBeAvailableAndSwitchToIt(menuFrame,15);
        this.waitUntilElementIsClickable(menuHowToUseTrelloLikeAPro,10);
        return menuHowToUseTrelloLikeAPro.isDisplayed();
    }
}
