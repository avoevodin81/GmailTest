import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.StartPage;
import utils.browser.Browser;

public class StartPageTest {
    private WebDriver driver;
    private Browser browser;
    private StartPage startPage;

    @BeforeClass
    public void setUp(ITestContext testContext) {
        browser = new Browser();
        driver = browser.getDriver();
        testContext.setAttribute("WebDriver", this.driver);
    }

    @Test
    public void openStartPageTest() {
        startPage = new StartPage(browser);
        Assert.assertEquals(driver.getTitle(), "Google", "The title is not correct");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
