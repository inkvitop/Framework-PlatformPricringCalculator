package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudSearchResultsPage extends BasePage {

    public GoogleCloudSearchResultsPage openLink(String linkText) {
        String xpathForLink = "//div[@class='gs-title']//b[text()='" + linkText + "']/..";
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']/..")));
        WebElement resultLink = driver.findElement(By.xpath(xpathForLink));
        clickIn(resultLink);
        return this;
    }
}