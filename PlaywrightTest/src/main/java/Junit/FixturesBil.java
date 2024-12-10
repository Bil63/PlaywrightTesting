package Junit;


import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(FixturesBil.CustomOptions.class)
public class FixturesBil {

    public static class CustomOptions implements OptionsFactory {
        @Override
        public Options getOptions() {
            return new Options().setHeadless(false)
                    .setContextOptions(new Browser.NewContextOptions()
                            .setBaseURL("http://www.automationpractice" +
                                    ".pl/index.php?controller=authentication&back=my-account"));
        }
    }


     @Test

     void basicTest(Page page, BrowserContext context) {

         Faker faker = new Faker();
         String firstName = faker.name().firstName();
         String lastName = faker.name().lastName();
         String email = faker.internet().emailAddress();
         String password = faker.internet().password();

         //Start tracing

         context.tracing().start(new Tracing.StartOptions()
                 .setScreenshots(true)
                 .setSnapshots(true)
                 .setSources(true));

         page.navigate("/");
         page.pause();
         assertThat(page).hasTitle(Pattern.compile("My Shop"));

         page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();

         page.locator("#email_create").click();
         page.locator("#email_create").fill(email);
         System.out.println(email);
         page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                 .setName(" Create an account")).click();
         page.getByText("Mr.").click();
         page.getByLabel("First name *").click();
         page.getByLabel("First name *").fill(firstName);
         System.out.println(firstName);
         page.getByLabel("First name *").press("Tab");
         page.getByLabel("Last name *").fill(lastName);
         System.out.println(lastName);
         page.getByLabel("Last name *").press("Tab");
         page.getByLabel("Email *").press("Tab");
         page.getByLabel("Password *").fill(password);
         System.out.println(password);
         page.locator("#days").selectOption("14");
         page.locator("#months").selectOption("4");
         page.locator("#years").selectOption("2001");
         page.getByLabel("Sign up for our newsletter!").check();
         page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register ")).click();
         page.screenshot(new Page.ScreenshotOptions()
                 .setPath(Paths.get("Screenshot.png")).setFullPage(true));

         context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
     }
}

