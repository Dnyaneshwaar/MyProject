package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    Playwright playwright;
    Browser browser;
    Page page;
    String base_url;

    @BeforeTest
    public void setUp() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/Config.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        playwright = Playwright.create();

        if("firefox".equals(browserName))
        {
            browser = playwright.chromium().launch();
        } else if ("safari".equals(browserName)) {

            browser = playwright.chromium().launch();
        }
        else {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }


        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage(); //context = browser.newContext();
        page.setDefaultTimeout(5000);
        base_url = prop.getProperty("qa.base_url");

    }
}
