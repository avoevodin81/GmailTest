package ui.inbox;

import org.openqa.selenium.By;
import utils.browser.Browser;

public class InboxPage {
    private Browser browser;

    private By composeButton = By.xpath("//div[@role='button' and @gh='cm']");
    private By inboxTextLink = By.xpath("//a[@href='https://mail.google.com/mail/u/0/#inbox']");
    private By deleteButton = By.xpath("//div[@act='10']/div/div");

    private String checkBoxLetter = "//span/b[contains(text(), '%s')]/ancestor::tr//div[@role='checkbox']/div";

    public InboxPage(Browser browser) {
        this.browser = browser;
    }

    public NewMessageDialogBox clickOnComposeButton() {
        browser.clickOnElement("Click on Compose button", composeButton);
        return new NewMessageDialogBox(browser);
    }

    public void clickOnInboxTextLink() {
        browser.clickOnElement("Click on Inbox text link", inboxTextLink);
    }

    public void selectTheLetter(String subject) {
        By checkBoxItem = By.xpath(String.format(checkBoxLetter, subject));
        browser.clickOnElement("Click on checkbox with subject: " + subject, checkBoxItem);
    }

    public void clickOnDeleteButton() {
        browser.clickOnElement("Click on Delete button", deleteButton);
    }

    public String getCheckBoxLetter(String subject) {
        return String.format(checkBoxLetter, subject);
    }
}
