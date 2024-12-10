package org.example;


import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

import java.util.Map;

public class SetEnv {


    public static void main(String[] args) {

        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(Map.of
                ("PLAYWRIGHT_JAVA_SRC", "C:\\Users\\USER\\IdeaProjects\\PlaywrightTest\\src\\main\\java"));

        Browser browser;
        Page page;

        try(Playwright playwright = Playwright.create(options)) {
            browser = playwright.chromium().launch
                    (new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            page = browser.newPage();
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
            page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
            PlaywrightAssertions.assertThat(page).hasTitle("Login - My Shop");

            page.locator("#email_create").click();
            page.locator("#email_create").fill("rare.bear.mpkh+23@instantletter.net");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Create an account")).click();
            page.getByText("Mr.").click();
            page.getByLabel("First name *").click();
            page.getByLabel("First name *").fill("Max");
            page.getByLabel("First name *").press("Tab");
            page.getByLabel("Last name *").fill("Müller");
            page.getByLabel("Last name *").press("Tab");
            page.getByLabel("Email *").press("Tab");
            page.getByLabel("Password *").fill("12345U");
            page.locator("#days").selectOption("14");
            page.locator("#months").selectOption("4");
            page.locator("#years").selectOption("2001");
            page.getByLabel("Sign up for our newsletter!").check();
            page.pause();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register ")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));




            /*page.locator("#email_create").fill("rare.bear.mpkh+15@instantletter.net");


            page.locator("#SubmitCreate").click();
            page.locator("#id_gender1").click();
            page.locator("#customer_firstname").fill("Max");
            page.locator("#customer_lastname").fill("Müller");
            page.locator("#passwd").fill("Hallo1235");
            page.locator("#days").selectOption("2");
            page.locator("#months").selectOption("10");
            page.locator("#years").selectOption("1995");
            page.locator("#newsletter").click();
            page.locator("#submitAccount").click();

            PlaywrightAssertions.assertThat(page.getByText("Your account has been created."))
                    .containsText("Your account has been created.");
        }
        finally {
            page.close();
            browser.close();*/
        }
    }
}