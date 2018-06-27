import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.inbox.InboxPage;
import ui.inbox.NewMessageDialogBox;
import user.User;
import utils.browser.Browser;
import utils.helpers.PropertyReader;

public class WriteLetterTest {
    private String randomValue = String.valueOf(Math.random());

    private WebDriver driver;
    private Browser browser;
    private InboxPage inboxPage;
    private NewMessageDialogBox newMessageDialogBox;
    private User user;

    @BeforeClass
    public void setUp(ITestContext testContext) {
        browser = new Browser();
        driver = browser.getDriver();
        testContext.setAttribute("WebDriver", this.driver);

        String email = PropertyReader.getPropertyValue("email");
        String password = PropertyReader.getPropertyValue("password");
        user = new User(browser);
        inboxPage = user.openInboxPage(email, password);
    }

    @Test(dataProvider = "email")
    public void writeLetterTest(String email, String subject, String text) {
        newMessageDialogBox = inboxPage.clickOnComposeButton();
        newMessageDialogBox.sendMessage(email, subject, text);
        Assert.assertTrue(browser.areElementsPresent(By.id("link_vsm")), "The email is not sent");
    }

    @Test(dataProvider = "email", dependsOnMethods = "writeLetterTest")
    public void receiveLetterTest(String email, String subject, String text) {
        inboxPage.clickOnInboxTextLink();
        Assert.assertTrue(browser.areElementsPresent(By.xpath(String.format("//span/b[contains(text(), '%s')]", subject))),
                "The email is not received");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "email")
    public Object[][] getDataForEmail() {
        String email = PropertyReader.getPropertyValue("email");
        String subject = "Subject " + randomValue;
        String text = "Some letter " + randomValue;
        return new Object[][]{
                {email, subject, text}
        };
    }
}
