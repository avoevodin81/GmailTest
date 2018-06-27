package utils.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.helpers.PropertyReader;
import utils.helpers.UnavailableParameterException;
import utils.listeners.DriverListener;

import java.util.concurrent.TimeUnit;

import static utils.helpers.Reporter.log;

public class Browser {
    private static final int IMPLICITLY_WAIT = 2;
    private static final int DRIVER_WAIT = 10;
    private final String BROWSER_TYPE = "browserType";
    private final String BROWSER = "browser";
    private EventFiringWebDriver driver;
    private WebDriverWait wait;

    public Browser() {
        initDriver();
    }

    private void initDriver() {
        String browserType = PropertyReader.getPropertyValue(BROWSER_TYPE);
        String browser = PropertyReader.getPropertyValue(BROWSER);
        WebDriver myDriver = null;

        // initialize WebDriver
        try {
            if (browserType.equals("local")) {
                myDriver = new LocalBrowser().getDriver(browser);
            } else if (browserType.startsWith("http")) {
                myDriver = new RemoteBrowser().getDriver(browserType, browser);
            }
        } catch (UnavailableParameterException e) {
            e.printStackTrace();
        }

        //add EventFiringWebDriver
        driver = new EventFiringWebDriver(myDriver);
        driver.register(new DriverListener());
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void waitForElement(final By element) {
        if (wait == null) {
            wait = new WebDriverWait(driver, DRIVER_WAIT);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void clickOnElement(String log, By element) {
        log(log);
        waitForElement(element);
        driver.findElement(element).click();
    }

    public void fillInText(String log, By element, String text) {
        log(log);
        waitForElement(element);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }
}
