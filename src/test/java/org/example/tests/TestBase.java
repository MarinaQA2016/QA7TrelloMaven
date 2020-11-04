package org.example.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {
  public static final String LOGIN = "marinaqatest2019@gmail.com";
  public static final String PASSWORD = "marinaqa";
  public static LogLog4j log4j = new LogLog4j();

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected EventFiringWebDriver driver;

  public static class MyListener extends AbstractWebDriverEventListener{
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Find element: " + by);
    }
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Element " + by + " was found");
    }
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      String screenName = "screen-" + System.currentTimeMillis()+ ".png";
      //getScreenShot((TakesScreenshot) driver, screenName);
      log4j.info("Exception: " + throwable ); //+ " see file " + screenName);
    }
  }

  private static void getScreenShot(TakesScreenshot driver, String screenName) {
    File tmp = driver.getScreenshotAs(OutputType.FILE);
    File screen = new File(screenName);
    try {
      Files.copy(tmp,screen);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeSuite(alwaysRun = true)
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver() {

    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    // Driver initialization. Open Trello application
    //ChromeOptions options = new ChromeOptions();
    //options.addArguments("--lang=" + "rus");
    //driver = new EventFiringWebDriver(new ChromeDriver(options));
    driver.get(baseUrl);
  }
  @AfterMethod(alwaysRun = true)
  public void finishTest(ITestResult result){
    if(result.getStatus() == ITestResult.FAILURE){
      String screenName = "screen-" + System.currentTimeMillis()+ ".png";
      getScreenShot((TakesScreenshot) driver, screenName);
      log4j.info("Test failure, see screen " + screenName);
    }


    driver.quit();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
