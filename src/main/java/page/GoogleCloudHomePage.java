package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends BasePage {
    private final static String PAGE_URL = "https://cloud.google.com/";

    public  GoogleCloudHomePage() {
        openPage(PAGE_URL);
    }

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchInput;

    public GoogleCloudHomePage showSearchResults(String text) {
        clickIn(searchInput);
        enterText(searchInput, text);
        pressEnter(searchInput);
        return this;
    }
}
