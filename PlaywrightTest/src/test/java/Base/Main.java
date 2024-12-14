package Base;


import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;
import java.util.Map;

public class Main {


    static Playwright playwright;
    static Browser browser;

    static BrowserContext context;
    static Page page;


    static void startTracing () {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    void stopTracing() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

     @BeforeAll
    static void launchBrowser() {
         Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(Map.of
                 ("PLAYWRIGHT_JAVA_SRC", "C:\\Users\\USER\\IdeaProjects\\PlaywrightTest\\src\\main\\java"));

         playwright = Playwright.create(options);
         browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                 .setHeadless(false));
     }



    @AfterAll
    static void closeBrowser() {
         playwright.close();
     }



     @BeforeEach
    void createContextAndPage() {
         context = browser.newContext();
         page = context.newPage();
     }


     @AfterEach

    void closeContext() {
        context.close();
    }

}
