package org.example.tests;

import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CurrentBoardTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHafa7currentBoard;
    HomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHafa7currentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAsAttlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHafa7currentBoard.waitUntilPageIsLoaded();
    }


    @Test(groups = {"smoke"})
    public void isCorrectCurrentBoard(){
        Assert.assertEquals(qaHafa7currentBoard.getCurrentBoardHeader(),"QA Haifa7",
                "The header of the screen is not 'QA Haifa7'");
    }

    /*
    @Test
    public void isCorrectCurrentBoard2(){
        Assert.assertTrue(qaHafa7currentBoard.isCorrectCurrentBoard(),
                "The header of the screen is not 'QA Haifa7'");
    }
*/
/*
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderCreateList")
    public void createListPositive(String name)  {
        int listsBeforeAdding = qaHafa7currentBoard.getListsQuantity();
        qaHafa7currentBoard.createNewList(name);
        int listsAfterAdding = qaHafa7currentBoard.getListsQuantity();
        Assert.assertEquals(listsBeforeAdding +1, listsAfterAdding,
                "The quantity of lists is not equal to expected quantity");

    }
*/

    @Test(groups = {"smoke","regression"},dataProviderClass = DataProviders.class,dataProvider = "dataProviderRandomName")
    public void createListPositive2(String name)  {
        int listsBeforeAdding = qaHafa7currentBoard.getListsQuantity();
        qaHafa7currentBoard.createNewList(name);
        int listsAfterAdding = qaHafa7currentBoard.getListsQuantity();
        Assert.assertEquals(listsBeforeAdding +1, listsAfterAdding,
                "The quantity of lists is not equal to expected quantitty");

    }

    @Test
    public void putAnyListToArchive()  {
         if (qaHafa7currentBoard.getNameOfAddListButton().equals("Add a list"))
         {
             qaHafa7currentBoard.createNewList("tets");
         }
         int quantityListsInTheBeginning = qaHafa7currentBoard.getListsQuantity();
         qaHafa7currentBoard.openExtraMenuForFirstList();
         qaHafa7currentBoard.putTheListToArchive();
         int quantityListsAtTheEnd = qaHafa7currentBoard.getListsQuantity();
         Assert.assertEquals(quantityListsAtTheEnd,quantityListsInTheBeginning-1,
                "The quantityListsAtTheEnd is not quantityListsInTheBeginning-1");
    }




}
