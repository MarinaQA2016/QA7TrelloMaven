package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {
    @FindBy(xpath = "//button[@data-test-id ='header-boards-menu-button']")
    WebElement boardsButton;

    public BoardsPageHelper(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardsButton,45);
        waitUntilElementsAreVisible(By.xpath("//li[@class = 'boards-page-board-section-list-item']"),20);
        return this;
    }

    public BoardsPageHelper openCurrentBoardPage(String boardName) {
        String xpath = "//li[@class='boards-page-board-section-list-item'][.//div[@title ='" + boardName+"']]";
        waitUntilElementIsVisible(driver.findElement(By.xpath(xpath)),15);
        WebElement board = driver.findElement(By
                .xpath(xpath));
        board.click();
        return this;
    }
    public String getBoadsIconName(){
        return boardsButton.getText();
    }
}
