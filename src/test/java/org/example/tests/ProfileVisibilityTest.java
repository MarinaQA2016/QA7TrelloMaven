package org.example.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.*;

public class ProfileVisibilityTest extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHafa7currentBoard;
    HomePageHelper homePage;
    MenuPageHelper menuPage;
    ProfileVisibilityHelper profileVisibility;

    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHafa7currentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        profileVisibility = PageFactory.initElements(driver, ProfileVisibilityHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                 .loginAsAttlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded()
                  .openCurrentBoardPage("QA Haifa7");
        qaHafa7currentBoard.waitUntilPageIsLoaded();
        qaHafa7currentBoard.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openProfileVisibility();
        profileVisibility.waitUntilPageIsLoaded();
    }


    @Test
    public void isProfileVisabilityPage(){
        Assert.assertEquals(profileVisibility.getProfileVisibilityTabName(), "Profile and Visibility");
    }

    @Test
    public void userNameVerification(){
        String titleMenu = profileVisibility.getTitleMenuIcon();
        String userNameInTitle = titleMenu.substring(titleMenu.indexOf("(")+1,titleMenu.length()-1);
        String userName = profileVisibility.getUserName();

        Assert.assertEquals(userNameInTitle, userName);
    }
}
