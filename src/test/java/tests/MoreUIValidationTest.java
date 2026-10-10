package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class MoreUIValidationTest {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/#");
    }

    @AfterTest
    public void tearDown() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    @Test
    public void childWindowHandling() {

        Locator blinkTexts = page.locator(".blinkingText");
        Page newPage = context.waitForPage(() -> blinkTexts.first().click());
        newPage.waitForLoadState();

        String childText = newPage.locator(".red").textContent();
        String emailId = childText.split("at")[1].split(" ")[1];
        //System.out.println(emailId);

        page.getByLabel("username").fill(emailId);
        System.out.println(page.getByLabel("username").inputValue());
        // Assert.assertEquals(innerText,emailId);

    }

    @Test
    public void uiControls()
    {
        Locator userRdBtn = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName( "User"));
        userRdBtn.click();;
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Okay")).click();;
        Assert.assertTrue(userRdBtn.isChecked());

        page.getByRole(AriaRole.COMBOBOX).selectOption("Teacher");
        page.waitForTimeout(3000);
    }



}

