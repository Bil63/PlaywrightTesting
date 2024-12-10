package AccViolations;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

public class SetUp {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    protected static Page page;

    @BeforeAll
    static void setUpPlaywright() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = context.newPage();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterEach
    void tearDownContext() {
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("RecordTest.zip")));

        context.close();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }


}
