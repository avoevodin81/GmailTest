package utils.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.helpers.UnavailableParameterException;

import java.io.File;

public class LocalBrowser {
    private WebDriver driver;
    private final String driverPath = System.getProperty("user.dir")
            + File.separator
            + "src" + File.separator
            + "main" + File.separator + "drivers" + File.separator;
    private final String CHROME_DRIVER = "chromedriver";
    private final String FIREFOX_DRIVER = "geckodriver.exe";

    public WebDriver getDriver(String browser) throws UnavailableParameterException {
        if (driver == null) {
            if (browser.equals("chrome")) {
                //set driver path property
                System.setProperty("webdriver.chrome.driver", driverPath + CHROME_DRIVER);
                //initialise driver with new ChromeDriver
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")) {
                //get driver path property
                System.setProperty("webdriver.gecko.driver", driverPath + FIREFOX_DRIVER);
                //initialise driver with new GeckoDriver
                driver = new FirefoxDriver();
            } else throw new UnavailableParameterException("browser has invalid name: " + browser);
        }
        return driver;
    }
}
