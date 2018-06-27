package utils.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import utils.helpers.GetScreenshot;

import java.io.File;

import static utils.helpers.Reporter.log;

public class DriverListener extends AbstractWebDriverEventListener {
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        log("Check the screenshot: " + GetScreenshot.getScreenshot(driver) + ".png" + " - " + throwable + " found.");
        String fileName = GetScreenshot.getScreenshot(driver);
        log("<font size=\"3\" color=\"red\">" + throwable + " found.</font>");
        String filePath = System.getProperty("user.dir")
                + File.separator
                + "src" + File.separator
                + "screenshots" + File.separator + fileName;
        log("<a href='" + filePath + "'> <img src='" + filePath + "' height='100' width='100'/> </a>");
    }
}
