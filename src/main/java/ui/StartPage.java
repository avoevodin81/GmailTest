package ui;

import org.openqa.selenium.By;
import utils.browser.Browser;
import utils.helpers.PropertyReader;

import static utils.helpers.Reporter.log;

public class StartPage {
    private Browser browser;

    By loginButton = By.id("gb_70");

    public StartPage(Browser browser) {
        this.browser = browser;
        log("Navigate to the main page");
        browser.getDriver().navigate().to(PropertyReader.getPropertyValue("instance"));
    }

    public LoginPage clickOnLoginButton(){
        browser.clickOnElement("Click on Login button", loginButton);
        return new LoginPage(browser);
    }
}
