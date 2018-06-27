package user;

import ui.LoginPage;
import ui.MainPage;
import ui.StartPage;
import ui.inbox.InboxPage;
import ui.inbox.NewMessageDialogBox;
import utils.browser.Browser;

public class User {

    private Browser browser;
    private StartPage startPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private NewMessageDialogBox newMessageDialogBox;

    public User(Browser browser) {
        this.browser = browser;
    }

    public InboxPage openInboxPage(String email, String password) {
        startPage = new StartPage(browser);
        loginPage = startPage.clickOnLoginButton();
        mainPage = loginPage.logInToGmailAccount(email, password);
        return mainPage.clickOnEmailTextLink();
    }

    public void writeEmail(InboxPage inboxPage, String email, String subject, String text) {
        newMessageDialogBox = inboxPage.clickOnComposeButton();
        newMessageDialogBox.sendMessage(email, subject, text);
    }
}
