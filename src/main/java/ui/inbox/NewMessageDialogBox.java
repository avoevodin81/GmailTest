package ui.inbox;

import org.openqa.selenium.By;
import utils.browser.Browser;

public class NewMessageDialogBox {
    private Browser browser;

    private By recipientsTextField = By.name("to");
    private By subjectTextField = By.name("subjectbox");
    private By messageBodyTextField = By.xpath("//div[@role='textbox']");
    private By sendButton = By.xpath("//div[@role='button'and contains(@aria-label, 'Enter')]");

    public NewMessageDialogBox(Browser browser) {
        this.browser = browser;
    }

    public void fillInRecipientsTextField(String email) {
        browser.fillInText("Fill in Recipients text field with: " + email, recipientsTextField, email);
    }

    public void fillInSubjectTextField(String subject) {
        browser.fillInText("Fill in Subject text field with: " + subject, subjectTextField, subject);
    }

    public void fillInMessageBodyTextField(String text) {
        browser.fillInText("Fill in Message Body text field", messageBodyTextField, text);
    }

    public void clickOnSendButton() {
        browser.clickOnElement("Click on Send button", sendButton);
    }

    public void sendMessage(String email, String subject, String text) {
        fillInRecipientsTextField(email);
        fillInSubjectTextField(subject);
        fillInMessageBodyTextField(text);
        clickOnSendButton();
    }
}
