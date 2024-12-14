package EnvironementVariable;



import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;



public class RecordTest extends SetUp {
    @Test
    void test() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(true).toUpperCase();

        int days = faker.number().numberBetween(1,28);
        int months = faker.number().numberBetween(1,12);
        int years = faker.number().numberBetween(1970, 2005);



        page.navigate
                ("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");


        page.pause();

        page.locator("#email_create").fill(email);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName(" Create an account")).click();
        page.getByText("Mr.").click();
        page.getByLabel("First name *").fill(firstName);
        page.getByLabel("Last name *").fill(lastName);
        page.getByLabel("Password *").click();
        page.getByLabel("Password *").fill(password);
        page.locator("#days").selectOption(String.valueOf(days));
        page.locator("#months").selectOption(String.valueOf(months));
        page.locator("#years").selectOption(String.valueOf(years));
        page.getByLabel("Sign up for our newsletter!").check();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Register ")).click();
        PlaywrightAssertions.assertThat(page.getByText("Your account has been created.")).isVisible();
    }

}