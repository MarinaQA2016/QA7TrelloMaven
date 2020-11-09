package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpMenuTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHafa7currentBoard;
    HomePageHelper homePage;
    MenuPageHelper menuPage;
    HelpMenuPageHelper helpMenu;

    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHafa7currentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        helpMenu = PageFactory.initElements(driver, HelpMenuPageHelper.class);


        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAsAttlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHafa7currentBoard.waitUntilPageIsLoaded();
        qaHafa7currentBoard.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openHelpMenuPage();

    }

    @Test
    public void helpMenuTest(){
        helpMenu.waitUntilPageIsLoaded();
        Assert.assertTrue(helpMenu.existsGettingStartedGuideMenu());
        Assert.assertTrue(helpMenu.existsHowToUseTrelloLikeAProMenu());
    }

}
