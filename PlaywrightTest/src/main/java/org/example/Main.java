package org.example;


import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;


public class Main {
    public static void main(String[] args) {

        Browser browser = null;
        Page page = null;
        try (Playwright playwright = Playwright.create()){
            browser = Playwright.create().firefox().launch(new BrowserType.LaunchOptions
                    ().setHeadless(false));
            BrowserContext context = browser.newContext();
            page = browser.newPage();
            page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");

            PlaywrightAssertions.assertThat(page).hasTitle("Login - My Shop");
            page.locator("#email_create").fill("rare.bear.mpkh@instantletter.net");
            //page.getByLabel("email_create").fill("rare.bear.mpkh@instantletter.net");
            page.locator("#SubmitCreate").click();
            page.locator("#id_gender1").click();
            page.locator("#customer_firstname").fill("Max");
            page.locator("#customer_lastname").fill("Müller");
            page.locator("#passwd").fill("Hey12345/");
            page.locator("#days").selectOption("12");
            page.locator("#months").selectText("2");
            page.locator("#years").selectOption();
            page.locator(".pull-right required").click();C:\Users\USER\IdeaProjectsC:\Users\USER\IdeaProjects

            page.waitForTimeout(30000);
        }
        finally {
            page.close();
            browser.close();
        }
    }
}