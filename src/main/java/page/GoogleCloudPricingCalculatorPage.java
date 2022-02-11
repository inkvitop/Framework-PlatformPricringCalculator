package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// https://cloud.google.com/products/calculator?hl=en#tab=identity-platform

public class GoogleCloudPricingCalculatorPage extends BasePage {
    // iframes
    @FindBy(xpath = "//iframe[@allow='clipboard-write https://cloud-dot-devsite-v2-prod.appspot.com']")
    private WebElement outerIFrame;
    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement innerIFrame;

    @FindBy(xpath = "//*[@class='devsite-snackbar-action' and text()='OK']")
    private WebElement cookiesPoliceAgreeButton;

    @FindBy(xpath = "//*[@id='mainForm']//md-tab-item//div[@title='Compute Engine' and @class='tab-holder compute']")
    private WebElement computeEngineCategoryButton;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement inputNumberOfInstances;

    @FindBy(xpath = "//md-select[@placeholder='Series' and @name='series']")
    private WebElement seriesInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineTypeInput;
    @FindBy(xpath = "//*[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement vCPUs8RAM30;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUCheckbox;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuTypeInput;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement gpuCountInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement ssdInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    private  WebElement locationInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageInput;

    @FindBy(xpath = "//div[@aria-hidden='false']//*[contains(text(), '1 Year')]/..")
    private WebElement oneYearCommittedUsage;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement estimateButton;

    @FindBy(xpath = "//*[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//*[@id='compute']/md-list//*[contains(text(), 'Region')]")
    private WebElement resultRegion;
    @FindBy(xpath = "//*[@id='compute']/md-list//*[contains(text(), 'Local SSD')]")
    private WebElement resultSSD;
    @FindBy(xpath = "//*[@id='compute']/md-list//*[contains(text(), 'Instance type')]")
    private WebElement resultInstanceType;
    @FindBy(xpath = "//*[@id='compute']/md-list//*[contains(text(), 'VM class')]")
    private WebElement resultVmClass;
    @FindBy(xpath = "//*[@id='compute']/md-list//*[contains(text(), 'Commitment term')]")
    private WebElement resultCommitmentTerm;
    @FindBy(xpath = "//*[contains(text(), 'Total Estimated Cost')]")
    private WebElement resultTotalCost;


    public GoogleCloudPricingCalculatorPage chooseCategory() {
        closeCookiePolice(cookiesPoliceAgreeButton);
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerIFrame));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerIFrame));
        clickIn(computeEngineCategoryButton);
        return this;
    }

    public GoogleCloudPricingCalculatorPage fillNumberOfInstances(String numberOfInstances) {
        enterText(inputNumberOfInstances, numberOfInstances);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseSeries(String series) {
        clickIn(seriesInput);
        String optionXpath = "//*[@value='" + series + "']";
        WebElement resultOption = driver.findElement(By.xpath(optionXpath));
        clickIn(resultOption);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseMachineType() {
        scrollPage(inputNumberOfInstances);
        clickIn(machineTypeInput);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(vCPUs8RAM30));
        clickIn(vCPUs8RAM30);
        return this;
    }

    public GoogleCloudPricingCalculatorPage addGPU(String gpuCount, String gpuName) {
        clickIn(addGPUCheckbox);

        clickIn(gpuTypeInput);
        String optionGPUNameXpath = "//md-option[@value='" + gpuName.toUpperCase() + "']";
        WebElement gpuNameResultOption = driver.findElement(By.xpath(optionGPUNameXpath));
        clickIn(gpuNameResultOption);

        clickIn(gpuCountInput);
        String optionGPUCountXpath = "//md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]' and @value='" + gpuCount + "']";
        WebElement gpuCountResultOption = driver.findElement(By.xpath(optionGPUCountXpath));
        clickIn(gpuCountResultOption);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseLocalSSD(String localSSD) {
        scrollPage(addGPUCheckbox);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(ssdInput));
        clickIn(ssdInput);

        String optionXpath = "//*[contains(text(), '" + localSSD + "')]";
        WebElement resultOption = driver.findElement(By.xpath(optionXpath));
        clickIn(resultOption);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseLocation(String location) {
        clickIn(locationInput);

        WebElement searchLocation = driver.findElement(By.xpath("//input[@ng-model='listingCtrl.inputRegionText.computeServer']"));
        clickIn(searchLocation);
        enterText(searchLocation, location);

        WebElement locationResult = driver.findElement(By.xpath("//input[@ng-model='listingCtrl.inputRegionText.computeServer']/../../..//md-option"));
        clickIn(locationResult);
        return this;
    }

    public GoogleCloudPricingCalculatorPage chooseCommittedUsageTerm() {

        clickIn(committedUsageInput);
        clickIn(oneYearCommittedUsage);
        return this;
    }

    public GoogleCloudPricingCalculatorPage pressEstimateButton() {
        clickIn(estimateButton);
        return this;
    }

    public GoogleCloudPricingCalculatorPage pressEmailEstimateButton() {
        clickIn(emailEstimateButton);
        return this;
    }

    public GoogleCloudPricingCalculatorPage sendEstimateEmail(String emailAddress) {
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerIFrame));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerIFrame));

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(emailInput));
        clickIn(emailInput);
        enterText(emailInput, emailAddress);
        clickIn(sendEmailButton);
        return this;
    }

    public String getResultSSD() {
        return resultSSD.getText();
    }

    public String getResultRegion() {
        return resultRegion.getText();
    }

    public String getInstanceType() {
        return resultInstanceType.getText();
    }

    public String getVmClass() {
        return resultVmClass.getText();
    }

    public String getCommitmentTerm() {
        return resultCommitmentTerm.getText();
    }

    public String getTotalCost() {
        return resultTotalCost.getText();
    }
}

