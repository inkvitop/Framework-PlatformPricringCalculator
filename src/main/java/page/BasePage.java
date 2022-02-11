package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.WebDriverProvider;

import java.time.Duration;
import java.util.ArrayList;

public class BasePage {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    ArrayList<String> tabs;
    public final int TIMEOUT = 10;

    public BasePage() {
        driver = WebDriverProvider.webDriverInitialization();
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public BasePage openNewTab(int index) {
        (js).executeScript("window.open();");
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return this;
    }

    public BasePage switchToTab(int index) {
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return this;
    }

    public BasePage closeTab() {
        driver.close();
        return this;
    }

    public BasePage clickIn(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public BasePage pressEnter(WebElement element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(element))
                .sendKeys(Keys.ENTER);
        return this;
    }

    public BasePage enterText(WebElement element, String text) {
        WebElement typeIn = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(element));
        clickIn(element);
        typeIn.sendKeys(text);
        return this;
    }

    public BasePage scrollPage(WebElement element) {
        (js).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void refreshPage() {
        driver.get(driver.getCurrentUrl());
    }

    public BasePage closeCookiePolice(WebElement acceptButton) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(acceptButton));
        clickIn(acceptButton);
        return this;
    }
}
