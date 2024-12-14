package AccViolations;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessibilityViolationsTest extends SetUp {

    @Test
            void accViolationsTest() {

        page.navigate
                ("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");



        AxeResults accessibilityScanResults = new AxeBuilder(page).analyze();
        System.out.println("EmptyList " + Collections.emptyList());
        System.out.println("Violations " + accessibilityScanResults.getViolations());
        assertEquals(Collections.emptyList()
                , accessibilityScanResults.getViolations(), "Error!! Accessibility Violations detected");
    }

}
