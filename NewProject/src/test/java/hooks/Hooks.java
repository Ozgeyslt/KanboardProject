package hooks;

import com.codeborne.selenide.Configuration;
import common.PageFactory;
import common.PageManager;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.asserts.SoftAssert;
import pages.Login;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static driver.DriverFactory.maximize;

public class Hooks {
    private static PageManager pageManager;
    private static SoftAssert softAssert;

    @Before
    public void setUp() {
        pageManager = new PageManager();
        softAssert = new SoftAssert();
        Login login = pageManager.getLogin();

        PageFactory.buildLogin();
        login.fillTextBoxes("admin", "admin");
        maximize();
        Configuration.timeout = 10000;
    }

    @After
    public void tearDown() {
        DriverFactory.clearCookies();
        closeWebDriver();
    }

}
