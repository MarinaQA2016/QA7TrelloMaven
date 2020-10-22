package org.example.tests;

import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {
LoginPageHelper loginPage;
BoardsPageHelper boardsPage;
HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        log4j.info("LoginTest:@BeforeMethod initTests()");
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        //loginPage = new LoginPageHelper(driver);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginEmpty()  {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        loginPage.loginNotAttlassian("",PASSWORD)
                  .pressLoginButton();
        log4j.info("-----Test case was finished------");
        Assert.assertEquals(loginPage.getErrorMessage(),"Missing email",
                "The text of the error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "incorrectLogin")
    public void loginNegativeLoginIncorrect(String login, String password, String errorMessage)  {
        log4j.startTestCase("loginNegativeLoginIncorrect()");
        log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        log4j.info("Parameter errorMessage - " + errorMessage);
        loginPage.loginNotAttlassian(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),errorMessage,
                "The error message is not '" + errorMessage + "'");
        log4j.info("-----Test case was finished------");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginNegativeLoginIncorrect2(String login, String password)  {
        loginPage.loginNotAttlassian(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this email",
                "The error message is not '" + "There isn't an account for this email" + "'");
    }

    @Test
    public void loginNegativePasswordIncorrect()  {
        loginPage.loginAsAttlassian(LOGIN,PASSWORD+"1");
        Assert.assertTrue(loginPage.getAttlassianErrorMessage().contains("Incorrect email address and"),
                "The error message is not contains the text 'Incorrect email address and'");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void loginPositive(String login, String password)  {
        loginPage.loginAsAttlassian(login,password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.getBoadsIconName().equals("Boards"),"The text on the button is not 'Board'");
    }



}
