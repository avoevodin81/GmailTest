package utils.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.helpers.UnavailableParameterException;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteBrowser {
    private WebDriver driver;

    public WebDriver getDriver(String hub, String browser) throws UnavailableParameterException {
        URL url = null;
        try {
            url = new URL(hub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (browser.equals("chrome")) {
            driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
        } else if (browser.equals("firefox")) {
            driver = new RemoteWebDriver(url, DesiredCapabilities.firefox());
        } else throw new UnavailableParameterException("browser has invalid name: " + browser);
        return driver;
    }
}
