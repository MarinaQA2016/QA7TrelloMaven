package org.example.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.*;

public class ActivityTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHafa7currentBoard;
    HomePageHelper homePage;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHafa7currentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);


        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAsAttlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHafa7currentBoard.waitUntilPageIsLoaded();
    }
    @Test
    public void addingCardActionInActivity(){
        qaHafa7currentBoard.addNewCardInFirstList("New Card");
        qaHafa7currentBoard.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openActivityPage();
        activityPage.waitUntilPageIsLoaded();
        Assert.assertTrue(activityPage.getFirstRecordText().contains("New Card"));

    }

}
