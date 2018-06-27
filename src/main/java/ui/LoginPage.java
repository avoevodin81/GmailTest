package ui;

import org.openqa.selenium.By;
import utils.browser.Browser;

public class LoginPage {
    private Browser browser;

    private By emailTextField = By.xpath("//input[@type='email']");
    private By passwordTextField = By.xpath("//input[@type='password']");
    private By nextEmailButton = By.xpath("//div[@id='identifierNext']//span");
    private By nextPasswordButton = By.xpath("//div[@id='passwordNext']//span");

    public LoginPage(Browser browser) {
        this.browser = browser;
    }

    public void fillInEmailTextField(String email) {
        browser.fillInText("Fill in email text field with: " + email, emailTextField, email);
    }

    public void clickOnNextEmailButton() {
        browser.clickOnElement("Click on email next button", nextEmailButton);
    }

    public void fillInPasswordTextField(String password) {
        browser.fillInText("Fill in password text field with: " + password, passwordTextField, password);
    }

    public void clickOnNextPasswordButton() {
        browser.clickOnElement("Click on password next button", nextPasswordButton);
    }

    public MainPage logInToGmailAccount(String email, String password) {
        fillInEmailTextField(email);
        clickOnNextEmailButton();
        fillInPasswordTextField(password);
        clickOnNextPasswordButton();
        return new MainPage(browser);
    }
}
