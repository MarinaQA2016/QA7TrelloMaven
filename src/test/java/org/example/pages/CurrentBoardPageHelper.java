package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase{
    @FindBy(id = "workspaces-preamble-board-header-button")
    WebElement boardsButton;

    @FindBy(tagName = "h1")
    WebElement header;

    @FindBy(xpath = "//div[@class = 'list js-list-content']")
    List<WebElement> listElementsList;

    @FindBy(css = "a.icon-close.dark-hover")
    WebElement xButton;

    @FindBy(xpath = "//input[@name='name']")
    WebElement addNamelistField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitNewList;

    @FindBy(xpath = "//span[@class='placeholder']")
    WebElement addListButton;

    @FindBy(css = "a.js-close-list")
    WebElement closeExtraMenu;

    @FindBy(css = "a.list-header-extras-menu")
    WebElement extraMenu;

    @FindBy(xpath = "//button[@aria-label = 'Open Member Menu']")
    WebElement menuPageIcon;

    @FindBy(xpath = "//a[@class ='open-card-composer js-open-card-composer']")
    List<WebElement> addCardButtonList;

    @FindBy(css = "textarea.js-card-title")
    WebElement cardTitleArea;

    @FindBy(xpath = "//input[@type='submit'][@value = 'Add Card']")
    WebElement submitCardButton;

    @FindBy(xpath = "//a[@class = 'icon-lg icon-close dark-hover js-cancel']")
    WebElement xCardButton;

    String boardName;


    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardsButton,15);
        waitUntilElementIsVisible(header,10);
        waitUntilElementsAreVisible(addCardButtonList,20);
        System.out.println("size of AddCardButtons:" + addCardButtonList.size());
    }

    public String getCurrentBoardHeader(){
        return header.getText();
    }

    public boolean isCorrectCurrentBoard() {
        return header.getText().equals(this.boardName);
    }

    public int getListsQuantity(){
        waitUntilElementsAreVisible(listElementsList,10);
        return listElementsList.size();
    }
    public void cancelTheNewAddingList() {
        xButton.click();
        waitUntilElementIsInvisible(xButton,5);
    }

    public void fillTheNameAndSubmit(String name) {
        editField(addNamelistField,name);
        submitNewList.click();
    }

    public void initiateAddList() {
        addListButton.click();
    }

    public String getNameOfAddListButton(){
        return addListButton.getText();
    }

    public void createNewList(String name) {
        this.initiateAddList();
        this.fillTheNameAndSubmit(name);
        this.cancelTheNewAddingList();
    }
    public void putTheListToArchive() {
        waitUntilElementIsClickable(closeExtraMenu,15);
        closeExtraMenu.click();
        waitUntilElementIsInvisible(closeExtraMenu,5);
    }

    public void openExtraMenuForFirstList() {
        extraMenu.click();
    }

    public void openMenuPage() {
        waitUntilElementIsClickable(menuPageIcon,10);
        menuPageIcon.click();
    }


    public void addNewCardInFirstList(String title) {
        addCardButtonList.get(0).click();
        editField(cardTitleArea,title);
        submitCardButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        xCardButton.click();

    }
}
