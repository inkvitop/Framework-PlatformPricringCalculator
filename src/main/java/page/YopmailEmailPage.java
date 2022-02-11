package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class YopmailEmailPage extends BasePage {

    @FindBy(xpath = "//*[@id='nbmail']")
    private WebElement mailCount;

    @FindBy(xpath = "//*[@id='ifmail']")
    private WebElement mailFrame;

    @FindBy(xpath = "//td[contains(text(), 'USD')]")
    private WebElement totalEstimatedMonthlyCost;

    public YopmailEmailPage waitNewMail() {
        boolean mail = false;
        while (!mail) {
            refreshPage();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Objects.equals(mailCount.getText(), "1 mail")) {
                mail = true;
            }
        }
        return this;
    }

    public String getTotalEstimatedMonthlyCost() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mailFrame));
        return totalEstimatedMonthlyCost.getText();
    }
}
