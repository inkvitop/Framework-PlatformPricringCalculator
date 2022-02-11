package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailGeneratedEmailPage extends BasePage {
    @FindBy(xpath = "//*[@id='egen']")
    private WebElement generatedEmailField;

    @FindBy(xpath = "//*[@onclick='egengo();']")
    private WebElement goToEmailPageButton;

    @FindBy(xpath = "//*[@id='accept']")
    private WebElement cookieAcceptButton;

    public String takeNewEmail() {
        return generatedEmailField.getText();
    }

    public YopmailGeneratedEmailPage goToEmailPage() {
        clickIn(goToEmailPageButton);
        return this;
    }
}
