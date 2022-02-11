package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudHomePage;
import page.GoogleCloudPricingCalculatorPage;
import page.GoogleCloudSearchResultsPage;


public class ComparisonOfTheTotalAmountTest extends BaseTest {
    private final String SEARCH_TEXT = "Google Cloud Platform Pricing Calculator";
    private final String NUMBER_OF_INSTANCES = "4";
    private final String MACHINE_CLASS = "Regular";
    private final String SERIES = "n1";
    private final String INSTANCE_TYPE = "n1-standard-8";
    private final String LOCAL_SSD = "2x375";
    private final String NUMBER_OF_GPU = "1";
    private final String GPU_TYPE = "NVIDIA_Tesla_V100";
    private final String DATACENTER_LOCATION = "Frankfurt";
    private final String COMMITMENT_TERM = "1 Year";

    // 18/01/2022
    private final String TOTAL_COST = "Total Estimated Cost: USD 1,082.77 per 1 month";

    @Test (groups = {"e2e"})
    public void comparisonOfTheTotalAmountTest() {
        new GoogleCloudHomePage()
                .showSearchResults(SEARCH_TEXT);
        new GoogleCloudSearchResultsPage()
                .openLink(SEARCH_TEXT);
        GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage()
                .chooseCategory()
                .fillNumberOfInstances(NUMBER_OF_INSTANCES)
                .chooseSeries(SERIES)
                .chooseMachineType()
                .addGPU(NUMBER_OF_GPU, GPU_TYPE)
                .chooseLocalSSD(LOCAL_SSD)
                .chooseLocation(DATACENTER_LOCATION)
                .chooseCommittedUsageTerm()
                .pressEstimateButton();

        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultRegion().contains(DATACENTER_LOCATION), "WRONG REGION!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultSSD().contains(LOCAL_SSD), "WRONG SSD!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getInstanceType().contains(INSTANCE_TYPE), "WRONG INSTANCE TYPE!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getVmClass().contains(MACHINE_CLASS.toLowerCase()), "WRONG VM CLASS");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getCommitmentTerm().contains(COMMITMENT_TERM), "WRONG COMMITMENT TERM");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getTotalCost().contains(TOTAL_COST), "WRONG TOTAL COST");
    }
}

