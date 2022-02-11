package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webDriver.WebDriverProvider;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SCREENSHOTS_PATH = PropsReader.getProperty("SCREENSHOTS_PATH");
    private static final int DAYS_TO_PURGE_SCREENSHOTS = PropsReader.getPropertyNumber("DAYS_TO_PURGE_SCREENSHOTS");

    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("                                           *   *   *");
        LOGGER.info(iTestResult.getName() + " start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info(iTestResult.getName() + " passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.error(iTestResult.getName() + " failed");
        saveScreenshot(iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    private void saveScreenshot(String testName) {
        File screenCapture = ((TakesScreenshot) WebDriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        File screenshotsFolder = new File(SCREENSHOTS_PATH);
        try {
            if (screenshotsFolder.exists()) {
                removeOutdatedScreenshots();
            }
            FileUtils.copyFile(screenCapture, new File(
                    screenshotsFolder.getPath() + '/'
                            + getCurrentTimeAsString() +
                            ".png"));
            LOGGER.info("RP_MESSAGE#FILE#{}#{}",
                    screenCapture.getAbsolutePath(),
                    testName + " - screenshot is attached");
        } catch (IOException e) {
            LOGGER.error(testName + " - unable to make screenshot");
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    private void removeOutdatedScreenshots() {
        File screenshotsFolder = new File(SCREENSHOTS_PATH);
        for (File screenshot : Objects.requireNonNull(screenshotsFolder.listFiles())) {
            if (FileUtils.isFileOlder(screenshot, getBoundaryLifespanMillis())) {
                FileUtils.deleteQuietly(screenshot);
            }
        }
    }

    private long getBoundaryLifespanMillis() {
        long plannedLifespanMillis = TimeUnit.DAYS.toMillis(DAYS_TO_PURGE_SCREENSHOTS);
        return new Date().getTime() - plannedLifespanMillis;
    }
}
