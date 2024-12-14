package FrameworkBuildingJunit;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlaywrightTest {
    static Playwright playwright;
    static Browser browser;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch
                (new BrowserType.LaunchOptions
                        ().setHeadless(false).setSlowMo(10000));
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    void testRegistration() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        System.out.println(page.title());
        Assertions.assertEquals("Login - My Shop", "Login - My Shop");
    }
}
