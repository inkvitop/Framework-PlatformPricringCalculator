package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ComparisonOfTheTotalAmountOnMailTest extends BaseTest {
    private final String SEARCH_TEXT = "Google Cloud Platform Pricing Calculator";
    private final String NUMBER_OF_INSTANCES = "4";
    private final String SERIES = "n1";
    private final String LOCAL_SSD = "2x375";
    private final String NUMBER_OF_GPU = "1";
    private final String GPU_TYPE = "NVIDIA_Tesla_V100";
    private final String DATACENTER_LOCATION = "Frankfurt";

    private String yopmailEmail;

    @Test (groups = {"e2e"})
    public void comparisonOfTheTotalAmountOnMailTest() {
        YopmailEmailPage yopmailEmailPage = new YopmailEmailPage();
        GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage();
        YopmailGeneratedEmailPage yopmailGeneratedEmailPage = new YopmailGeneratedEmailPage();

        new GoogleCloudHomePage()
                .showSearchResults(SEARCH_TEXT);

        new GoogleCloudSearchResultsPage()
                .openLink(SEARCH_TEXT);

        googleCloudPricingCalculatorPage
                .chooseCategory()
                .fillNumberOfInstances(NUMBER_OF_INSTANCES)
                .chooseSeries(SERIES)
                .chooseMachineType()
                .addGPU(NUMBER_OF_GPU, GPU_TYPE)
                .chooseLocalSSD(LOCAL_SSD)
                .chooseLocation(DATACENTER_LOCATION)
                .chooseCommittedUsageTerm()
                .pressEstimateButton();
        String calculateTotalEstimatedMonthlyCost = googleCloudPricingCalculatorPage.getTotalCost();
        googleCloudPricingCalculatorPage
                .pressEmailEstimateButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new YopmailHomePage()
                .openYopmailInNeuTab()
                .generateRandomEmail();
        yopmailEmail = yopmailGeneratedEmailPage.takeNewEmail();
        yopmailGeneratedEmailPage
                .goToEmailPage()
                .switchToTab(0);

        googleCloudPricingCalculatorPage
                .sendEstimateEmail(yopmailEmail)
                .switchToTab(1);

        yopmailEmailPage.waitNewMail();
        String mailTotalEstimatedMonthlyCost = yopmailEmailPage.getTotalEstimatedMonthlyCost();

        Assert.assertTrue(calculateTotalEstimatedMonthlyCost.contains(mailTotalEstimatedMonthlyCost), "WRONG TOTAL COST");
    }
}
