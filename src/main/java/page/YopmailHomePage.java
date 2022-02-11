package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailHomePage extends BasePage {
    public final static String PAGE_URL = "https://yopmail.com/ru/";

    @FindBy(xpath = "//*[@id='accept']")
    private WebElement cookiesPoliceAgreeButton;

    @FindBy(xpath = "//a[@href='email-generator']")
    private WebElement generateRandomEmailButton;

    public YopmailHomePage openYopmailInNeuTab() {
        openNewTab(1);
        openPage(PAGE_URL);
        closeCookiePolice(cookiesPoliceAgreeButton);
        return this;
    }

    public YopmailHomePage generateRandomEmail() {
        clickIn(generateRandomEmailButton);
        return this;
    }
}
