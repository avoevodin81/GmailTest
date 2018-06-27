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

public class RemoveEmailFromInboxListTest {
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
    public void removeEmailFromInboxListTest(String email, String subject, String text){
        user.writeEmail(inboxPage, email, subject, text);
        inboxPage.clickOnInboxTextLink();
        inboxPage.selectTheLetter(subject);
        inboxPage.clickOnDeleteButton();
        Assert.assertFalse(browser.areElementsPresent(By.xpath(inboxPage.getCheckBoxLetter(subject))),
                "The email is not deleted");
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
