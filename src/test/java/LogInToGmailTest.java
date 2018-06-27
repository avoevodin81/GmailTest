import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.LoginPage;
import ui.MainPage;
import ui.StartPage;
import ui.inbox.InboxPage;
import utils.browser.Browser;
import utils.helpers.PropertyReader;

public class LogInToGmailTest {
    private WebDriver driver;
    private Browser browser;
    private StartPage startPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private InboxPage inboxPage;

    @BeforeClass
    public void setUp(ITestContext testContext) {
        browser = new Browser();
        driver = browser.getDriver();
        testContext.setAttribute("WebDriver", this.driver);
        startPage = new StartPage(browser);
    }

    @Test(dataProvider = "user")
    public void logInToGmailTest(String email, String password) {
        loginPage = startPage.clickOnLoginButton();
        mainPage = loginPage.logInToGmailAccount(email, password);

        Assert.assertTrue(browser.areElementsPresent(By.xpath("//a[@href='https://mail.google.com/mail/?tab=wm']")),
                "The email text link is not presented");
    }

    @Test(dataProvider = "user")
    public void openEmailTest(String email, String password) {
        inboxPage = mainPage.clickOnEmailTextLink();
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@href, 'https://accounts.google.com/S')]"))
                .getAttribute("title").contains(email), "The email is not match");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "user")
    public Object[][] getUserData() {
        String email = PropertyReader.getPropertyValue("email");
        String password = PropertyReader.getPropertyValue("password");
        return new Object[][]{
                {email, password}
        };
    }
}
