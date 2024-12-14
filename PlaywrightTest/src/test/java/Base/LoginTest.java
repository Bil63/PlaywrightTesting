package Base;


import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class LoginTest extends Main {


    @Test
    public void testValidLogin() {
        //Navigate zur Seite

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        //String password = ("automation_"+System.currentTimeMillis());
        startTracing();
        page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");


        PlaywrightAssertions.assertThat(page).hasTitle("Login - My Shop");

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

        stopTracing();






        //Random E-Mail ["automation-"+System.currentTimeMillis()+"@gmail.com"]
    }
}
