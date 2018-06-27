package utils.listeners;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.helpers.GetScreenshot;

import java.io.File;

import static utils.helpers.Reporter.log;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver =
                (WebDriver) iTestResult.getTestContext().getAttribute("WebDriver");
        String fileName = GetScreenshot.getScreenshot(driver);
        log("<font size=\"3\" color=\"red\">Test is not passed!</font>");
        String filePath = System.getProperty("user.dir")
                + File.separator
                + "src" + File.separator
                + "screenshots" + File.separator + fileName;
        log("<a href='" + filePath + "'> <img src='" + filePath + "' height='100' width='100'/> </a>");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
