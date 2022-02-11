package test;

import org.testng.annotations.*;
import page.BasePage;
import utils.TestListener;
import webDriver.WebDriverProvider;

@Listeners({TestListener.class, TestListener.class})
public class BaseTest extends BasePage {

    @BeforeSuite(alwaysRun = true)
    public void onStartLog() {

    }

    @BeforeMethod(alwaysRun = true)
    public void setupBrowser() {

    }

    @AfterClass
    public void closeDriver() {
        WebDriverProvider.webDriverQuit();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        WebDriverProvider.webDriverQuit();
    }
}
