package ui;

import org.openqa.selenium.By;
import ui.inbox.InboxPage;
import utils.browser.Browser;

public class MainPage {
    private Browser browser;

    private By emailTextLink = By.xpath("//a[@href='https://mail.google.com/mail/?tab=wm']");

    public MainPage(Browser browser) {
        this.browser = browser;
    }

    public InboxPage clickOnEmailTextLink() {
        browser.clickOnElement("Click on Email text link", emailTextLink);
        return new InboxPage(browser);
    }
}
